package com.ubi.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.ubi.rs232.DmConnect;
import com.ubi.util.Component;
import com.ubi.util.LayoutFrame;

public class DmLayout {

	private JFrame mainFrame;

	//ini파일 가져오기 (없으면 생성)
	private String dmFilePath = "C:/serial/domackSerial.ini";

	public void prepareGUI() {
		final LayoutFrame lfe = new LayoutFrame();	//LayoutFrame 객체 생성
		final DmConnect con = new DmConnect();		//DmConnect 객체 생성

		//실행파일 UI
		mainFrame = new JFrame("씨리얼포트 연결 프로그램");
		mainFrame.setSize(500, 230);	//화면 크기 
		mainFrame.setLayout(null);		//레이아웃 null로 초기화
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {	//실행창 X누를때 이벤트
				con.systemClose();	//실행창 닫기 전 자원반납
				System.exit(0);		//실행창 종료
			}
		});
		//Component 객체에 매개변수 값을 넘겨주고 ini 파일 유무 확인 후 파일 생성
		final Component wcpt = new Component(dmFilePath, "C:/serial/", "port=COM5");
		// 중량 저울
		lfe.LayoutComponetSetting(mainFrame, wcpt, "도막측정", 0);		//연결 유무, 시작, 종료 버튼, 밑줄 등 전체 디자인 생성
		// 기본 component
		mainFrame.add(wcpt.getBottomLabelCpt(390, 70, 30, 130));	//실행이 안될시 종료 후 파일을 재시작 하시기 바랍니다 라벨 추가
		mainFrame.setVisible(true);	//프레임 보이기

		
		lfe.InitDwStart(con, wcpt, wcpt.getStartBtn(), wcpt.getConnectLabel(), dmFilePath);

		//시작버튼 클릭시 이벤트 실행
		wcpt.getStartBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lfe.connectDwStatus(wcpt, con, wcpt.getConnectLabel(), wcpt.getStartBtn(), con.PortConnect(dmFilePath, wcpt.getConnectLabel()));
			}
		});

		//종료버튼 클릭시 이벤트 실행
		wcpt.getStopBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfe.coonectDwExit(wcpt, con, wcpt.getConnectLabel(), wcpt.getStartBtn());
			}
		});
	}
}
