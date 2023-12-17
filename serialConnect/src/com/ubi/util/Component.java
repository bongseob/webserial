package com.ubi.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.swing.JButton;

public class Component {
	private Label subjectLabel;

	private Label manufactureLabel;

	private Label connectLabel;

	private Label bottomLabel;

	private Label underLineLabel;

	private JButton startBtn;

	private JButton stopBtn;

	private String manufacturer;

	public Component(String filePathVal, String dirVal, String comportVal) {
		Properties properties = new Properties();
		FileUtil fc = new FileUtil();
		boolean isExists = fc.fileExists(filePathVal);	//FileUtil.java 에서 파일 존재여부 확인
		if (isExists) {	//파일이 있을경우
			properties = fc.getProperties(filePathVal);
			String textValue = properties.getProperty("manufacturer"); //지정된 키의 값을 가지는 프로퍼티를 찾음
			if (textValue == null || textValue.equals("")) {
				manufacturer = "정보없음";
			} else {
				manufacturer = textValue;
			}
		} else { //파일이 없을경우
			//ini파일 생성
			fc.fileCreateAct(dirVal, filePathVal, comportVal, "speed=2400", "databits=7", "stopbits=1", "parityEven=even", "manufacturer=");
			manufacturer = "기본정보없음";
		}
	}

	public Label getSubjectLabelCpt(String subjectName, int width, int height, int posX, int posY) {
		subjectLabel = new Label();
		String textValue = subjectName;
		try {
			textValue = new String(textValue.getBytes(), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		subjectLabel.setText(textValue);
		subjectLabel.setFont(new Font("serif", Font.BOLD, 15));
		subjectLabel.setAlignment(Label.CENTER);
		subjectLabel.setSize(width, height);
		subjectLabel.setLocation(posX, posY);
		return subjectLabel;
	}

	public Label getManufactureLabelCpt(int width, int height, int posX, int posY) {
		manufactureLabel = new Label();
		String manuText = manufacturer;
		try {
			manuText = new String(manuText.getBytes(), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		manufactureLabel.setText(manuText);
		manufactureLabel.setFont(new Font("serif", Font.BOLD, 15));
		manufactureLabel.setAlignment(Label.CENTER);
		manufactureLabel.setSize(width, height);
		manufactureLabel.setLocation(posX, posY);
		return manufactureLabel;
	}

	public Label getConnectLabelCpt(int width, int height, int posX, int posY) {
		connectLabel = new Label();
		String context = "연결안됨";
		try {
			context = new String(context.getBytes(), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		connectLabel.setText(context);
		connectLabel.setFont(new Font("serif", Font.BOLD, 15));
		connectLabel.setAlignment(Label.CENTER);
		connectLabel.setSize(width, height);
		connectLabel.setLocation(posX, posY);
		return connectLabel;
	}

	public Label getBottomLabelCpt(int width, int height, int posX, int posY) {
		bottomLabel = new Label();
		String labelText = "실행이 안될시 종료 후 파일을 재시작 하시기 바립니다.";
		try {
			labelText = new String(labelText.getBytes(), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		bottomLabel.setText(labelText);
		bottomLabel.setForeground(Color.RED);
		bottomLabel.setFont(new Font("serif", Font.BOLD, 15));
		bottomLabel.setAlignment(Label.CENTER);
		bottomLabel.setSize(width, height);
		bottomLabel.setLocation(posX, posY);
		return bottomLabel;
	}

	public Label getUnderLineLabelCpt(int width, int height, int posX, int posY) {
		underLineLabel = new Label();
		underLineLabel.setBackground(Color.black);
		underLineLabel.setSize(width, height);
		underLineLabel.setLocation(posX, posY);
		return underLineLabel;
	}

	//시작버튼 레이아웃
	public JButton StartBtnCpt(int width, int height, int posX, int posY) {
		startBtn = new JButton();
		startBtn.setText("시작");
		startBtn.setSize(width, height);
		startBtn.setLocation(posX, posY);
		return startBtn;
	}

	//종료버튼 레이아웃
	public JButton CloseBtnCpt(int width, int height, int posX, int posY) {
		stopBtn = new JButton();
		stopBtn.setText("종료");
		stopBtn.setSize(width, height);
		stopBtn.setLocation(posX, posY);
		return stopBtn;
	}

	//연결 유무 라벨 변경 연결실패 -> 연결성공 등 
	public void HeaderValueChange(Label label, String text, Color color) {
		label.setText(text);
		label.setForeground(color);
	}

	public Label getSubjectLabel() {
		return subjectLabel;
	}

	public void setSubjectLabel(Label subjectLabel) {
		this.subjectLabel = subjectLabel;
	}

	public Label getManufactureLabel() {
		return manufactureLabel;
	}

	public void setManufactureLabel(Label manufactureLabel) {
		this.manufactureLabel = manufactureLabel;
	}

	public Label getConnectLabel() {
		return connectLabel;
	}

	public void setConnectLabel(Label connectLabel) {
		this.connectLabel = connectLabel;
	}

	public Label getBottomLabel() {
		return bottomLabel;
	}

	public void setBottomLabel(Label bottomLabel) {
		this.bottomLabel = bottomLabel;
	}

	public JButton getStartBtn() {
		return startBtn;
	}

	public void setStartBtn(JButton startBtn) {
		this.startBtn = startBtn;
	}

	public JButton getStopBtn() {
		return stopBtn;
	}

	public void setStopBtn(JButton stopBtn) {
		this.stopBtn = stopBtn;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
