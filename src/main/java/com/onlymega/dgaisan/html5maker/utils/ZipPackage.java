package com.onlymega.dgaisan.html5maker.utils;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.model.TempBanner;


public class ZipPackage {
	public static final String HTML_TEMPLATE = 
		"<!DOCTYPE html><html lang=\"en\"><head> <meta charset=\"utf-8\"> " +
	"<title></title></head><body>{0}</body></html>";
	
	private String html;
	private String outputDirName;

	private final String dataToken;
	private String tempDirName;
	//context.getRealPath("/") + CommonData.TEMP_FOLDER;
	private String assetsDirName;
	private String zipFolderName;

	private String outputEmbedFileName;
	private String outputBannerFileName;
	private String outputAssetsDirName;

	private File outputEmbedFile;
	private File outputBannerFile;
	private File outputAssetsDir;

	private TempBanner data;
	private List<String> imageNames;

	private ZipOutputStream zop;
	private String outputZipName = "";

	public ZipPackage(TempBanner data, String tempDir, String dataToken) throws Exception {
		this.data = data;
		this.tempDirName = tempDir;
		this.dataToken = dataToken;
		
		zipFolderName = "html5maker" + getDataToken();
		assetsDirName = CommonData.ASSETS_PREFIX + getDataToken();

		html = 
			data.getHtmlCode()
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
			MessageFormat.format(HTML_TEMPLATE, html);
		
		System.out.println("outputHtml: " + outputHtml); // XXX remove me
		
		BufferedWriter bannerWriter = 
			new BufferedWriter(new FileWriter(outputBannerFile));
		BufferedWriter embedWriter = 
			new BufferedWriter(new FileWriter(outputEmbedFile));
		
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
			
			FileUtils.copyFile(src, dest);
		}
	}
	
	public static String getZipName(String token) {
		return CommonData.TEMP_FOLDER + "/" + 
			"html5maker" + token + ".zip"; 
	}
	
	private String getDataToken() {
		return dataToken;
	}

	/**
	 * Creates a zip file on the file system.
	 * 
	 * @return the absolute web path to the zip file
	 * @throws Exception
	 */
	public String create() throws Exception {
		this.zop = 
			new ZipOutputStream(new FileOutputStream(new File(outputZipName)));

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
		
		JSONObject json = new JSONObject(data.getBannerConfig());
		JSONArray items = (JSONArray) json.get("items");
		
		for (int index = 0; index < items.length(); index++) {
			JSONObject x = (JSONObject) items.get(index);
			String url = x.getString("uploadURL");
			
			ret.add(url);
		}
		
		return ret;
	}
}