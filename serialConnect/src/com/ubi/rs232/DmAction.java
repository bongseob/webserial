package com.ubi.rs232;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TooManyListenersException;

public class DmAction extends Thread implements SerialPortEventListener {

	private Robot robot = null;

	private SerialPort port = null;

	private CommPortIdentifier port_id = null;

	private BufferedInputStream in = null;

	private BufferedReader bufferedReader = null;

	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		getSerialValue();
	}

	public boolean connection(String portNameValue, int speed, int databits, int stopbits, String parityEven) {
		boolean flag = false;
		try {
			flag = connect(portNameValue, speed, databits, stopbits, parityEven);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void portStart() {
		this.port.notifyOnDataAvailable(true);
		addEventListener(this);
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	};

	public boolean connect(String port_name, int spd, int databits, int stopbits, String parityEven) throws IOException {
		boolean flag = true;
		try {
			this.port_id = CommPortIdentifier.getPortIdentifier(port_name);
		} catch (NoSuchPortException e) {
			flag = false;
			e.getMessage();
		}
		if (flag) {
			try {
				//open() 메소드는 포트소유자 이름, 밀리세컨드 단위의 타임아웃시간 이 필요함, 포트를 열면 소켓통신과 같이 읽기/쓰기가 가능하다.
				this.port = (SerialPort) this.port_id.open(this.getClass().getName(), 2000);
			} catch (PortInUseException e) {
				flag = false;
				port.close();
			} catch (NullPointerException ne) {
				flag = false;
				port.close();
			}

			try {
				this.in = new BufferedInputStream(this.port.getInputStream());
				bufferedReader = new BufferedReader(new InputStreamReader(this.port.getInputStream()));
			} catch (IOException e) {
				flag = false;
				port.close();
				in.close();
			}

			try {
				int parityEvenValue = 0;
				if (parityEven.equals("even")) {
					parityEvenValue = SerialPort.PARITY_EVEN;
				} else if (parityEven.equals("mark")) {
					parityEvenValue = SerialPort.PARITY_MARK;
				} else if (parityEven.equals("none")) {
					parityEvenValue = SerialPort.PARITY_NONE;
				} else if (parityEven.equals("odd")) {
					parityEvenValue = SerialPort.PARITY_ODD;
				} else if (parityEven.equals("space")) {
					parityEvenValue = SerialPort.PARITY_SPACE;
				}
				this.port.setSerialPortParams(spd, databits, stopbits, parityEvenValue);

			} catch (UnsupportedCommOperationException e) {
				flag = false;
				port.close();
			}
		}

		return flag;
	}

	public void addEventListener(SerialPortEventListener e) {
		// 이벤트 리스너 등록
		try {
			this.port.addEventListener(e);
			this.port.notifyOnDataAvailable(true);
		} catch (TooManyListenersException e1) {

		}
	}
	
	//시리얼 통신 확인 후 메시지 리턴
	public String getSerialValue() {
		String getMessage = "available";
		double dataValue = 0;
		try {
			String str = bufferedReader.readLine();	
			if (!str.equals("")) {	//str에 값이 없을경우
				dataValue = Double.valueOf(str.toString()); //문자열을 double 로 형변환
				String scale = String.valueOf(dataValue); //double 를 String 로 형변환
				for (int i = 0; i < scale.length(); i++) {
					robot.keyPress(scale.charAt(i));//i위치의 char입력
				}
				robot.keyPress(KeyEvent.VK_ENTER);	//엔터누른 순간 이벤트
				robot.keyRelease(KeyEvent.VK_ENTER); //엔터떼는 순간 이벤트
			} else {
				dataValue = 0;
			}
		} catch (IOException e) {	//에러 메시지 입력
			if (e.getMessage().equals("Underlying input stream returned zero bytes")) {
				getMessage = "zeroByte";
			} else {
				getMessage = "notAvailable";
			}
		}
		return getMessage;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public SerialPort getPort() {
		return port;
	}

	public void setPort(SerialPort port) {
		this.port = port;
	}

	public CommPortIdentifier getPort_id() {
		return port_id;
	}

	public void setPort_id(CommPortIdentifier port_id) {
		this.port_id = port_id;
	}

	public BufferedInputStream getIn() {
		return in;
	}

	public void setIn(BufferedInputStream in) {
		this.in = in;
	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	};

}
