package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.AttachVo;
import com.javaex.vo.AttachVo2;

@Service
public class AttachService {

	public String upload2(AttachVo2 attachVo2) {

		String saveDir = "";

		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("linux")) {
			System.out.println("리눅스");
			/*saveDir = "/home/ec2-user/upload";*/
			saveDir="/app/upload";
		} else {
			System.out.println("윈도우");
			saveDir = "C:\\javaStudy\\upload";
		}

		MultipartFile file = attachVo2.getImg();

		// 오리지널 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName :" + orgName);

		// 확장자

		String exeName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exeName);

		// 파일사이즈

		long fileSize = file.getSize();
		System.out.println(fileSize);

		// 저장파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exeName;

		// 파일전체 경로 + 파일명
		String filePath = saveDir + File.separator + saveName;
		System.out.println(filePath);

		// (1) db 저장
		// (1-1) 데이터 묶기
//		AttachVo attachVo = new AttachVo(orgName, saveName, filePath, fileSize);
		attachVo2.setOrgName(orgName);
		attachVo2.setSaveName(saveName);
		attachVo2.setFilePath(filePath);
		attachVo2.setFileSize(fileSize);
		// (1-2) dao를 통해서 db에 저장

		// 사진을 서버(강남)에 하드디스크에 복사해야된다
		// 파일저장
		try {

			byte[] fileData = file.getBytes();

			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(fileData);
			bos.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return saveName;
	}

	public String upload(MultipartFile file) {

		System.out.println(file.getOriginalFilename());
	
		String saveDir = "";

		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("linux")) {
			System.out.println("리눅스");
			/*saveDir = "/home/ec2-user/upload";*/
			saveDir="/app/upload/";
			
		} else {
			System.out.println("윈도우");
			saveDir = "C:\\javaStudy\\upload";
		}

		// 오리지널 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName :" + orgName);

		// 확장자

		String exeName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exeName);

		// 파일사이즈

		long fileSize = file.getSize();
		System.out.println(fileSize);

		// 저장파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exeName;

		// 파일전체 경로 + 파일명
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);

		// (1) db 저장
		// (1-1) 데이터 묶기
		AttachVo attachVo = new AttachVo(orgName, saveName, filePath, fileSize);
		System.out.println(attachVo);

		// (1-2) dao를 통해서 db에 저장

		// 사진을 서버(강남)에 하드디스크에 복사해야된다
		// 파일저장
		try {

			byte[] fileData = file.getBytes();

			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(fileData);
			bos.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return saveName;
	}
}