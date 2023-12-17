package com.ubi.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FileUtil {
	//파일 존재여부 확인 메서드
	public boolean fileExists(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		flag = file.exists(); //파일 존재 확인
		return flag;
	};

	//properties -> ini파일과 같은 역활
	public Properties getProperties(String filePath) {
		Properties info = new Properties();
		try {
			info.load(new FileInputStream(filePath)); //ini 파일의 정보를 가져옴
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	};

	public void fileCreateAct(String dir, String fileName, String comport, String speed, String databits, String stopbits, String parityEven, String manufacturer) {
		File uploadDir = new File(dir);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(comport); // 문자 배열의 일부를 기입
			out.newLine();  // 개행
			out.write(speed);
			out.newLine();
			out.write(databits);
			out.newLine();
			out.write(stopbits);
			out.newLine();
			out.write(parityEven);
			out.newLine();
			out.write(manufacturer);
			out.newLine();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
