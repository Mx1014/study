package com.gshine.rmitalker.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import com.gshine.rmitalker.server.TalkerServer;

public class Conversation extends JFrame implements ActionListener {

	private JPanel msgPane;

	private JPanel buttonPane;

	private JTextArea txtMsg;

	private JButton btnOK;

	private JButton btnQuit;

	private String from;

	private String to;

	private String msg;

	private TalkerServer server;

	private boolean send = false;

	public Conversation(String from, String to, String msg, TalkerServer server) {
		this.from = from;
		this.to = to;
		this.server = server;
		this.msg = msg;

		if (msg == null)
			send = true;
		Font font = new Font("��", Font.PLAIN, 12);
		Color fcolor=new Color(13, 55, 85);
		Color c1=new Color(241, 250, 255);
		Container container = getContentPane();
		container.setLayout(null);
		Color bgc = new Color(119, 202, 250);
		container.setBackground(bgc);
		if(Login.icon!=null){
			setIconImage(Login.icon);
		}
		msgPane = new JPanel();
		msgPane.setBackground(bgc);
		msgPane.setBounds(10, 10, 390, 210);

		buttonPane = new JPanel();
		buttonPane.setBackground(bgc);
		buttonPane.setBounds(10, 220, 390, 290);
		buttonPane.setLayout(null);

		container.add(msgPane);
		container.add(buttonPane);

		txtMsg = new JTextArea(25, 33);
		msgPane.add(txtMsg);
		if (send) {
			btnOK = new JButton("����");
		} else {
			btnOK = new JButton("�ظ�");
		}
		btnOK.setBounds(200, 15, 70, 25);
		btnOK.setFont(font);
		btnOK.setForeground(fcolor);
		btnOK.setBackground(c1);
		btnOK.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btnQuit = new JButton("�ر�");
		btnQuit.setFont(font);
		btnQuit.setForeground(fcolor);
		btnQuit.setBackground(c1);
		btnQuit.setBounds(280, 15, 70, 25);
		btnQuit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btnOK.addActionListener(this);
		btnQuit.addActionListener(this);
		buttonPane.add(btnOK);
		buttonPane.add(btnQuit);
		if (!send) {
			txtMsg.setEditable(false);
			this.setTitle("��("+to+") �յ����� " + from+" ����Ϣ");
			this.txtMsg.setText(this.msg);
		} else {
			this.setTitle("��("+from+") ������ " + to+"����");
		}
		
		this.setResizable(false);
		this.setSize(410, 300);
		setCenter();
	}
	private void setCenter() {
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisSize = getSize();
		setLocation((scrSize.width - thisSize.width) / 2,
				(scrSize.height - thisSize.height) / 2);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conversation c = new Conversation(null, null, null, null);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.equals(btnQuit)) {
			this.dispose();
		} else if (btn.equals(btnOK)) {
			String txt = btn.getText();
			if (txt.equals("����")) {
				String msg = this.txtMsg.getText();
				if (msg.trim() == "") {
					JOptionPane.showMessageDialog(this, "���ܷ��Ϳ���Ϣ");
					return;
				} else {
					try {
						if (send) {
							this.server.sendMessage(from, to, msg);
						} else {
							server.sendMessage(to, from, msg);
						}
						this.dispose();

					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(this, "��������ʱ������");
						this.dispose();
					}
				}
			} else if (txt.equals("�ظ�")) {
				this.txtMsg.setEditable(true);
				this.btnOK.setText("����");
				this.setTitle("��("+to+") ���ں�" + from+"�Ի�");
				this.txtMsg.setText("");
			}
		}
	}

}
