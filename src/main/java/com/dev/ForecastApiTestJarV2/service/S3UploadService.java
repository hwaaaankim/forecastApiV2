package com.dev.ForecastApiTestJarV2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Component
@Service
public class S3UploadService {

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	// MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
	public String upload(MultipartFile file, String dirName)
			throws IOException, IllegalArgumentException, FileNotFoundException, java.io.IOException {
		
		File uploadFile = convert(file)
				.orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환 실패"));
		
		return upload(uploadFile, dirName);
	}

	private String upload(File uploadFile, String dirName) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1)
		        .limit(targetStringLength)
		        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		        .toString();
		String fileName = dirName + "/" + generatedString + "_" + uploadFile.getName();
		String uploadImageUrl = putS3(uploadFile, fileName);

		removeNewFile(uploadFile); // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)

		return uploadImageUrl; // 업로드된 파일의 S3 URL 주소 반환
	}

	private String putS3(File uploadFile, String fileName) {
		amazonS3Client.putObject(
				new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead) // PublicRead
																														// 권한으로
																														// 업로드
																														// 됨
		);
		return amazonS3Client.getUrl(bucket, fileName).toString();
	}

	private void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			log.info("파일이 삭제되었습니다.");
		} else {
			log.info("파일이 삭제되지 못했습니다.");
		}
	}

	private Optional<File> convert(MultipartFile file) throws IOException, FileNotFoundException, java.io.IOException {
		File convertFile = new File(file.getOriginalFilename());
		if (convertFile.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(convertFile)) {
				fos.write(file.getBytes());
			}
			System.out.println("123");
			return Optional.of(convertFile);
		}
		System.out.println("456");
		return Optional.empty();
	}
}
