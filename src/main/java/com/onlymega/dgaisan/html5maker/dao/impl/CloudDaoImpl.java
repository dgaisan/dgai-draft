package com.onlymega.dgaisan.html5maker.dao.impl;

import java.io.File;
import java.io.IOException;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.onlymega.dgaisan.html5maker.dao.CloudDao;

/**
 * DAO for saving files on S3.
 * 
 * @author Dmitri Gaisan
 *
 */
public class CloudDaoImpl implements CloudDao {

	/**
	 * {@inheritDoc}
	 */
	public String save(String bucketName, String path, String filename,
			File inputFile) throws AmazonServiceException, IOException {

		AmazonS3 s3 = new AmazonS3Client(new PropertiesCredentials(
				CloudDaoImpl.class.getResourceAsStream("AwsCredentials.properties"))); // TODO inject from here
				
		PutObjectResult res = s3.putObject(new PutObjectRequest(bucketName, path + File.separator + filename, inputFile));
        
		
		return res.getExpirationTime().toString(); // TODO return ??
	}

	
}
