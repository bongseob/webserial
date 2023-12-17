package com.ubi.rs232;

import gnu.io.SerialPort;

import java.awt.Label;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class DmConnect {

	private DmAction dmact;

	private Timer timer;	//스레드 클래스(스레드 보다 사용하기 편리하도록 편의성 제공)

	private Timer reTimer;	

	private String initFilePath;

	private Label connectLabel;	//jFrame 클래스

	public DmConnect() {
		dmact = new DmAction();
	}

	//연결
	public boolean PortConnect(String filePath, Label label) {
		initFilePath = filePath;
		connectLabel = label;
		boolean connectFlag = false;
		String portNameValue = null;
		int databits = 0;
		int stopbits = 0;
		String parityEven = null;
		int speed = 0;
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(filePath9));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//ini파일에서 장비 정보를 가져온다
		//각 키값에 맞는 정보를 가져옴
		portNameValue = properties.getProperty("port");
		databits = Integer.parseInt(properties.getProperty("databits"));
		stopbits = Integer.parseInt(properties.getProperty("stopbits"));
		parityEven = properties.getProperty("parityEven");
		speed = Integer.parseInt(properties.getProperty("speed"));
		connectFlag = dmact.connection(portNameValue, speed, databits, stopbits, parityEven);
		return connectFlag;
	}

	//프로그램 종료(종료시 자원반납)
	public void systemClose() {
		SerialPort port = null;
		BufferedInputStream in = null;
		BufferedReader bufferedReader = null;
		port = dmact.getPort();
		in = dmact.getIn();
		bufferedReader = dmact.getBufferedReader();
		try {
			if (in != null) {
				in.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (port != null) {
				port.close();
			}
			if (timer != null) {
				timer.cancel();
				timer.purge();
			}
			if (reTimer != null) {
				reTimer.cancel();
				reTimer.purge();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	public void portGetValue() {
		dmact.portStart();
		timer = new Timer();	//실제 타이머기능
		TimerTask m_task = new TimerTask() {	//timer클래스가 실행되어야 할 내용
			@Override
			public void run() {
				String message = dmact.getSerialValue(); //사용 유,무 메시지를 가져옴
				if (message.equals("notAvailable")) { //메시지 확인
					reConnect(); //if조건에 따라 재연결 확인
				}
			}
		};
		long delay = 1 * 700;	//타이머가 시작되기전 대기시간
		long intevalPeriod = 1 * 700;	//입력된 시간마다 run 실행
		timer.scheduleAtFixedRate(m_task, delay, intevalPeriod);	//delay 시간이 지난 후 period 시간 간격으로 run실행
	}

	//연결이 안되면 다시 연결시작
	public void reConnect() {
		SerialPort port = null;
		BufferedInputStream in = null;
		BufferedReader bufferedReader = null;
		port = dmact.getPort();
		in = dmact.getIn();
		bufferedReader = dmact.getBufferedReader();
		try {
			if (in != null) {
				in.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (port != null) {
				port.close();
			}
			if (timer != null) {
				timer.cancel();
				timer.purge();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		reTimer = new Timer();
		TimerTask re_task = new TimerTask() {
			@Override
			public void run() {
				boolean reFlag = PortConnect(initFilePath, connectLabel);
				if (reFlag) {
					connectLabel.setText("연결성공");
					portGetValue();
					reTimer.cancel();
					reTimer.purge();
				} else {
					connectLabel.setText("연결 재시도중....");
				}
			}
		};
		long delay = 1 * 700;
		long intevalPeriod = 1 * 700;
		reTimer.scheduleAtFixedRate(re_task, delay, intevalPeriod);	//delay 만큼 대기 후 intevalPeriod 간격으로 실행
	};
}
