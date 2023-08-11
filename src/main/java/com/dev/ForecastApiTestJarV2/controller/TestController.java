package com.dev.ForecastApiTestJarV2.controller;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.ForecastApiTestJarV2.service.S3UploadService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/image")
public class TestController {

	@Autowired
	S3UploadService s3UploadService;
	
	@RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String updload(MultipartFile file) throws IOException, IllegalArgumentException, FileNotFoundException, java.io.IOException {
		
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String now = format.format(today);
		s3UploadService.upload(file, "imageUpload/"+now);
		
		return "success";
	}
}
