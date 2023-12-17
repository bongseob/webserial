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

public class MwAction extends Thread implements SerialPortEventListener {

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

	public String getSerialValue() {
		String getMessage = "available";
		byte buf[] = new byte[60];
		String[] array = new String[60];
		String[] array2 = new String[60];
		double dataValue = 0;
		String str2 = null;
		String match = "[!^(,)(g)(+)(-)(T)(S)(:)(-)(%)*$]";
		String mvStr = null;
		try {
			if (bufferedReader.read() > 0 && buf.toString().length() > 0) {
				String str = bufferedReader.readLine();
				if (!str.equals("")) {
					array = str.split("/D");
					for (int i = 0; i < array.length; i++) {
						int idxValue = array[0].indexOf("%");
						System.out.println(array[i]);
						if (idxValue > -1) {
							mvStr = array[i];
							break;
						}
					}
					if (mvStr != null) {
						str2 = mvStr.replaceAll(match, "");
						str2 = str2.trim();
						array2 = str2.split("-");
						if (array2.length == 1) {
							str2 = array2[0].trim();
						} else {
							str2 = array2[1].trim();
						}
						dataValue = Double.valueOf(str2.toString());
						String scale = String.valueOf(dataValue);
						for (int i = 0; i < scale.length(); i++) {
							robot.keyPress(scale.charAt(i));
						}
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
					}
				} else {
					dataValue = 0;
				}
			}
		} catch (IOException e) {
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
