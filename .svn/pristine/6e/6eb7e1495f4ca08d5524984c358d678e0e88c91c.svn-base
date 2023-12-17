package com.ubi.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.ubi.rs232.MwConnect;
import com.ubi.util.Component;
import com.ubi.util.LayoutFrame;

public class WaterLayout {

	private JFrame mainFrame;

	private String waterFilePath = "C:/serial/waterSerial.ini";

	public void prepareGUI() {
		final LayoutFrame lfe = new LayoutFrame();
		final MwConnect mwCon = new MwConnect();

		mainFrame = new JFrame("씨리얼포트 연결 프로그램");
		mainFrame.setSize(500, 230);
		mainFrame.setLayout(null);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				mwCon.systemClose();
				System.exit(0);
			}
		});
		final Component scpt = new Component(waterFilePath, "C:/serial/", "port=COM6");
		// 중량 저울
		lfe.LayoutComponetSetting(mainFrame, scpt, "수분측정", 0);

		mainFrame.add(scpt.getBottomLabelCpt(390, 70, 30, 130));
		mainFrame.setVisible(true);
		lfe.InitMwStart(mwCon, scpt, scpt.getStartBtn(), scpt.getConnectLabel(), waterFilePath);

		scpt.getStartBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfe.connectMwStatus(scpt, mwCon, scpt.getConnectLabel(), scpt.getStartBtn(), mwCon.PortConnect(waterFilePath, scpt.getConnectLabel()));
			}
		});

		scpt.getStopBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfe.coonectMwExit(scpt, mwCon, scpt.getConnectLabel(), scpt.getStartBtn());
			}
		});
	}
}
