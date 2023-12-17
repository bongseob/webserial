package com.ubi.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.ubi.rs232.MwConnect;
import com.ubi.rs232.Rs232Connect;
import com.ubi.util.Component;
import com.ubi.util.LayoutFrame;

public class MultiLayout {

	private JFrame mainFrame;

	private String weightFilePath = "C:/serial/weightSerial.ini";

	private String waterFilePath = "C:/serial/waterSerial.ini";

	public void prepareGUI() {
		final LayoutFrame lfe = new LayoutFrame();
		final Rs232Connect con = new Rs232Connect();
		final MwConnect mwCon = new MwConnect();

		mainFrame = new JFrame("씨리얼포트 연결 프로그램");
		mainFrame.setSize(500, 400);
		mainFrame.setLayout(null);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				con.systemClose();
				mwCon.systemClose();
				System.exit(0);
			}
		});
		final Component wcpt = new Component(weightFilePath, "C:/serial/", "port=COM5");
		final Component scpt = new Component(waterFilePath, "C:/serial/", "port=COM6");
		// 중량 저울
		lfe.LayoutComponetSetting(mainFrame, wcpt, "전자저울", 0);
		// 수분측정
		lfe.LayoutComponetSetting(mainFrame, scpt, "수분측정", 132);
		// 기본 component
		mainFrame.add(wcpt.getBottomLabelCpt(390, 100, 30, 280));
		mainFrame.setVisible(true);

		lfe.InitStart(con, wcpt, wcpt.getStartBtn(), wcpt.getConnectLabel(), weightFilePath);
		lfe.InitMwStart(mwCon, scpt, scpt.getStartBtn(), scpt.getConnectLabel(), waterFilePath);

		wcpt.getStartBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfe.connectStatus(wcpt, con, wcpt.getConnectLabel(), wcpt.getStartBtn(), con.PortConnect(weightFilePath, wcpt.getConnectLabel()));
			}
		});

		wcpt.getStopBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfe.coonectExit(wcpt, con, wcpt.getConnectLabel(), wcpt.getStartBtn());
			}
		});

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
