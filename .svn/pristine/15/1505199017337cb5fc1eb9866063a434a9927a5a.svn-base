package com.ubi.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.ubi.rs232.KwConnect;
import com.ubi.util.Component;
import com.ubi.util.LayoutFrame;

public class KwLayout {

	private JFrame mainFrame;

	private String waterFilePath = "C:/serial/waterSerial.ini";

	public void prepareGUI() {
		final LayoutFrame lfe = new LayoutFrame();
		final KwConnect con = new KwConnect();

		mainFrame = new JFrame("씨리얼포트 연결 프로그램");
		mainFrame.setSize(500, 230);
		mainFrame.setLayout(null);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				con.systemClose();
				System.exit(0);
			}
		});
		final Component wcpt = new Component(waterFilePath, "C:/serial/", "port=COM5");
		// 관웅수분측정
		lfe.LayoutComponetSetting(mainFrame, wcpt, "수분측정", 0);
		// 기본 component
		mainFrame.add(wcpt.getBottomLabelCpt(390, 70, 30, 130));
		mainFrame.setVisible(true);

		lfe.KwInitStart(con, wcpt, wcpt.getStartBtn(), wcpt.getConnectLabel(), waterFilePath);

		wcpt.getStartBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfe.KwConnectStatus(wcpt, con, wcpt.getConnectLabel(), wcpt.getStartBtn(), con.PortConnect(waterFilePath, wcpt.getConnectLabel()));
			}
		});

		wcpt.getStopBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfe.KwCoonectExit(wcpt, con, wcpt.getConnectLabel(), wcpt.getStartBtn());
			}
		});
	}
}
