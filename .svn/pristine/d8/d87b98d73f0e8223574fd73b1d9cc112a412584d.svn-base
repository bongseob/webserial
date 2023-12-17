package com.ubi.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.ubi.rs232.Rs232Connect;
import com.ubi.rs232.VrConnect;
import com.ubi.util.Component;
import com.ubi.util.LayoutFrame;

public class InjectLayout {

	private JFrame mainFrame;

	private String weightFilePath = "C:/serial/weightSerial.ini";

	private String vernierFilePath = "C:/serial/vernierSerial.ini";

	public void prepareGUI() {
		final LayoutFrame lfe = new LayoutFrame();
		final Rs232Connect con = new Rs232Connect();
		final VrConnect vrCon = new VrConnect();

		mainFrame = new JFrame("씨리얼포트 연결 프로그램");
		mainFrame.setSize(500, 400);
		mainFrame.setLayout(null);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				con.systemClose();
				vrCon.systemClose();
				System.exit(0);
			}
		});
		final Component wcpt = new Component(weightFilePath, "C:/serial/", "port=COM5");
		final Component vcpt = new Component(vernierFilePath, "C:/serial/", "port=COM6");
		// 중량 저울
		lfe.LayoutComponetSetting(mainFrame, wcpt, "전자저울", 0);
		// 수분측정
		lfe.LayoutComponetSetting(mainFrame, vcpt, "버니어캘리퍼스", 132);
		// 기본 component
		mainFrame.add(wcpt.getBottomLabelCpt(390, 100, 30, 280));
		mainFrame.setVisible(true);

		lfe.InitStart(con, wcpt, wcpt.getStartBtn(), wcpt.getConnectLabel(), weightFilePath);
		lfe.InitVrStart(vrCon, vcpt, vcpt.getStartBtn(), vcpt.getConnectLabel(), vernierFilePath);

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

		vcpt.getStartBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfe.connectVrStatus(vcpt, vrCon, vcpt.getConnectLabel(), vcpt.getStartBtn(), vrCon.PortConnect(vernierFilePath, vcpt.getConnectLabel()));
			}
		});

		vcpt.getStopBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfe.coonectVrExit(vcpt, vrCon, vcpt.getConnectLabel(), vcpt.getStartBtn());
			}
		});
	}
}
