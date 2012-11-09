package com.onlymega.dgaisan.html5maker.actions;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.TempDataDao;
import com.onlymega.dgaisan.html5maker.model.TempData;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport implements
	SessionAware,
	ServletContextAware {
	
	private static final long serialVersionUID = 1L;
	private static final Collection<String> invalidTokens;
	
	private String dataToken;
	private String zipFileName;
	private TempDataDao tempDataService;
	
	private Map<String, Object> session;
	private ServletContext context;
	
	static {
		invalidTokens = new ArrayList<String>();
		invalidTokens.add("");
		invalidTokens.add("*");
	}
	
	public void validate() {
		if (dataToken == null || invalidTokens.contains(dataToken.trim())) {
			addActionError(getText("error.download.invalid.token"));
		}
		
		super.validate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		TempData data = null;
		Collection<String> dataIds = null;
		
		try {
			data = getTempDataService().getDataByToken(getDataToken());
			if (data == null) {
				return ERROR;
			}
			
			dataIds = (Collection<String>) session.get(CommonData.DATA_ID);
			if (dataIds == null || dataIds.isEmpty()) {
				dataIds = new ArrayList<String>();
			}
			
			dataIds.add(Integer.toString(data.getDataId()));
			session.put(CommonData.DATA_ID, dataIds);
			
			// create zip
			zipFileName = new ZipPackage(data).create();
			//new ZipPackage(data);
			
			System.out.println("ZipFileName: " + zipFileName);
			
		} catch (Exception ex) {
			getActionErrors().clear();
			addActionError(ex.getMessage());
			
			for (StackTraceElement elem : ex.getStackTrace()) {
				//DEBUG
				addActionError(elem.toString());
			}
			
			// TODO log the error
			return ERROR;
		}
		
		return SUCCESS;
	}
 
	public String getZip() {
		return zipFileName;
	}
	
	public void setDataToken(String dataToken) {
		this.dataToken = dataToken;
	}

	public String getDataToken() {
		return dataToken;
	}

	public void setTempDataService(TempDataDao tempDataService) {
		this.tempDataService = tempDataService;
	}

	public TempDataDao getTempDataService() {
		return tempDataService;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	// TODO move to a separate file
	// and inject using IoC
	// TODO: check if ZIp file exists then return it
	// instead of creating a new one.
	private class ZipPackage {
		private String html;
		private String outputDirName;
		
		private String tempDirName = context.getRealPath("/") + CommonData.TEMP_FOLDER;
		private String assetsDirName = CommonData.ASSETS_PREFIX + getDataToken();
		private String zipFolderName = "html5maker" + getDataToken();
		
		private String outputEmbedFileName;
		private String outputBannerFileName;
		private String outputAssetsDirName;
		
		private File outputEmbedFile;
		private File outputBannerFile;
		private File outputAssetsDir;
		
		private TempData data;
		private List<String> imageNames;
		
		private ZipOutputStream zop;
		private String outputZipName = "";
		
		public ZipPackage(TempData data) throws Exception {
			this.data = data;
			
			
			
			html = 
				data.getEmbedCode()
				.replace("stringtoreplace", 
						CommonData.ASSETS_PREFIX + getDataToken());
			
			outputDirName = tempDirName + File.separator + zipFolderName;
			outputZipName = outputDirName + ".zip";
			
			System.out.println("outputDirName: " + outputDirName);
			
			File outputDir = new File(outputDirName);
			
			if (outputDir.exists()) {
				outputDir.delete();
			}
			outputDir.mkdir();
			
			outputEmbedFileName = outputDirName + File.separator + "embed.html";
			outputBannerFileName = outputDirName + File.separator + "banner.html";
			outputAssetsDirName = outputDirName + File.separator + CommonData.ASSETS_PREFIX + getDataToken();
				
			outputEmbedFile = new File(outputEmbedFileName);
			outputBannerFile = new File (outputBannerFileName);
			outputAssetsDir = new File(outputAssetsDirName);
			
			outputEmbedFile.createNewFile();
			outputBannerFile.createNewFile();
			outputAssetsDir.mkdir();
		
			String outputHtml = 
				MessageFormat.format(getText("html_template"), html);
			
			System.out.println("outputHtml: " + outputHtml);
			
			BufferedWriter bannerWriter = new BufferedWriter(new FileWriter(outputBannerFile));
			BufferedWriter embedWriter = new BufferedWriter(new FileWriter(outputEmbedFile));
			
			bannerWriter.write(outputHtml);
			embedWriter.write(html);
			
			bannerWriter.flush();
			bannerWriter.close();
			embedWriter.flush();
			embedWriter.close();
			
			imageNames = getImageNamesFromJson();
			for (final String imageName : imageNames) {
				File tempDirFile = new File(tempDirName);
				File dest = new File(outputAssetsDirName, imageName);
				File src = new File(tempDirFile, imageName);
				
				//copyFile(src, dest);
				FileUtils.copyFile(src, dest);
			}
		}
				
		/**
		 * Creates a zip file on the file system.
		 * 
		 * @return the absolute web path to the zip file
		 * @throws Exception
		 */
		public String create() throws Exception {
			this.zop = new ZipOutputStream(new FileOutputStream(new File(outputZipName)));
			
			addFileToZip("banner.html", outputBannerFileName, zop);
			addFileToZip("embed.html", outputEmbedFileName, zop);
			
			for (final String imageName : imageNames) {
				addFileToZip(assetsDirName + File.separator + imageName, 
						outputAssetsDirName + File.separator + imageName, zop);
			}

			zop.flush();
			zop.close();

			return CommonData.TEMP_FOLDER + "/" + zipFolderName + ".zip";
		}

		/**
		 * Add a new entity(file) to a zip archive.
		 *
		 * @param fileName Name of the file (including directory)
		 * @param fileDest Relative path of the file
		 * @param zos Zip file containing all the files and folders.
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		private void addFileToZip(String fileName, String fileDest, ZipOutputStream zos)
	    	throws FileNotFoundException, IOException {
			
			zos.putNextEntry(new ZipEntry(fileName));
		 
		    File file = new File(fileDest);
			
			if (!file.isDirectory()) {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
						file));

				long bytesRead = 0;
				byte[] bytesIn = new byte[ CommonData.BUFFER_SIZE];
				int read = 0;

				while ((read = bis.read(bytesIn)) != -1) {
					zos.write(bytesIn, 0, read);
					bytesRead += read;
				}
			}
		 
		    zos.closeEntry();
		}
		
		private List<String> getImageNamesFromJson() throws JSONException {
			List<String> ret = new ArrayList<String>();
			
			JSONObject json = new JSONObject(data.getConfig());
			JSONArray items = (JSONArray) json.get("items");
			
			for (int index = 0; index < items.length(); index++) {
				JSONObject x = (JSONObject) items.get(index);
				String url = x.getString("uploadURL");
				
				ret.add(url);
			}
			
			return ret;
		}
	}
}
