package com.ubi.util;

import java.awt.Color;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.ubi.rs232.DmConnect;
import com.ubi.rs232.KwConnect;
import com.ubi.rs232.MwConnect;
import com.ubi.rs232.Rs232Connect;
import com.ubi.rs232.VrConnect;

public class LayoutFrame {

	public void LayoutComponetSetting(JFrame frame, Component component, String subjectText, int startY) {
		frame.add(component.getSubjectLabelCpt(subjectText, 120, 50, 10, startY));
		/* frame.add(component.getManufactureLabelCpt(100, 50, 100, startY)); */
		frame.add(component.getConnectLabelCpt(150, 70, 172, startY + 5));
		frame.add(component.StartBtnCpt(70, 40, 150, startY + 75));
		frame.add(component.CloseBtnCpt(70, 40, 260, startY + 75));
		frame.add(component.getUnderLineLabelCpt(500, 1, 0, startY + 128));
	}

	// 전자저울
	public void InitStart(Rs232Connect con, Component cpt, JButton button, Label label, String filePath) {
		if (con.PortConnect(filePath, label)) {
			cpt.HeaderValueChange(label, "연결성공", Color.BLACK);
			con.portGetValue();
			button.setEnabled(false);
		} else if (!con.PortConnect(filePath, label)) {
			cpt.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void connectStatus(Component component, Rs232Connect connect, Label label, JButton button, boolean status) {
		if (status) {
			component.HeaderValueChange(label, "연결성공", Color.BLACK);
			connect.portGetValue();
			button.setEnabled(false);
		} else {
			component.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void coonectExit(Component component, Rs232Connect connect, Label label, JButton button) {
		connect.systemClose();
		component.HeaderValueChange(label, "연결안됨", Color.BLACK);
		button.setEnabled(true);
	};

	// 수분측정기
	public void InitMwStart(MwConnect con, Component cpt, JButton button, Label label, String filePath) {
		if (con.PortConnect(filePath, label)) {
			cpt.HeaderValueChange(label, "연결성공", Color.BLACK);
			con.portGetValue();
			button.setEnabled(false);
		} else if (!con.PortConnect(filePath, label)) {
			cpt.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void connectMwStatus(Component component, MwConnect connect, Label label, JButton button, boolean status) {
		if (status) {
			component.HeaderValueChange(label, "연결성공", Color.BLACK);
			connect.portGetValue();
			button.setEnabled(false);
		} else {
			component.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void coonectMwExit(Component component, MwConnect connect, Label label, JButton button) {
		connect.systemClose();
		component.HeaderValueChange(label, "연결안됨", Color.BLACK);
		button.setEnabled(true);
	};

	// 도막두께
	public void InitDwStart(DmConnect con, Component cpt, JButton button, Label label, String filePath) {
		if (con.PortConnect(filePath, label)) {	//장비 연결확인
			cpt.HeaderValueChange(label, "연결성공", Color.BLACK);
			con.portGetValue();
			button.setEnabled(false);
		} else if (!con.PortConnect(filePath, label)) {
			cpt.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void connectDwStatus(Component component, DmConnect connect, Label label, JButton button, boolean status) {
		if (status) {
			component.HeaderValueChange(label, "연결성공", Color.BLACK);
			connect.portGetValue();
			button.setEnabled(false);
		} else {
			component.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void coonectDwExit(Component component, DmConnect connect, Label label, JButton button) {
		connect.systemClose();
		component.HeaderValueChange(label, "연결안됨", Color.BLACK);
		button.setEnabled(true);
	};

	// 버니어캘리퍼스
	public void InitVrStart(VrConnect con, Component cpt, JButton button, Label label, String filePath) {
		if (con.PortConnect(filePath, label)) {
			cpt.HeaderValueChange(label, "연결성공", Color.BLACK);
			con.portGetValue();
			button.setEnabled(false);
		} else if (!con.PortConnect(filePath, label)) {
			cpt.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void connectVrStatus(Component component, VrConnect connect, Label label, JButton button, boolean status) {
		if (status) {
			component.HeaderValueChange(label, "연결성공", Color.BLACK);
			connect.portGetValue();
			button.setEnabled(false);
		} else {
			component.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void coonectVrExit(Component component, VrConnect connect, Label label, JButton button) {
		connect.systemClose();
		component.HeaderValueChange(label, "연결안됨", Color.BLACK);
		button.setEnabled(true);
	};

	// 관웅수분측정
	public void KwInitStart(KwConnect con, Component cpt, JButton button, Label label, String filePath) {
		if (con.PortConnect(filePath, label)) {
			cpt.HeaderValueChange(label, "연결성공", Color.BLACK);
			con.portGetValue();
			button.setEnabled(false);
		} else if (!con.PortConnect(filePath, label)) {
			cpt.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void KwConnectStatus(Component component, KwConnect connect, Label label, JButton button, boolean status) {
		if (status) {
			component.HeaderValueChange(label, "연결성공", Color.BLACK);
			connect.portGetValue();
			button.setEnabled(false);
		} else {
			component.HeaderValueChange(label, "연결실패", Color.RED);
			button.setEnabled(true);
		}
	};

	public void KwCoonectExit(Component component, KwConnect connect, Label label, JButton button) {
		connect.systemClose();
		component.HeaderValueChange(label, "연결안됨", Color.BLACK);
		button.setEnabled(true);
	};
}
