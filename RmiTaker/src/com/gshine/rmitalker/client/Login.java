package com.gshine.rmitalker.client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.gshine.rmitalker.server.TalkerServer;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author g.shine
 * @version 1.0
 */

public class Login extends JFrame implements ActionListener {

	public static Image icon;
	
	static{
		try{
		String iconfile = ClassLoader.getSystemResource("resource/rmitalker.JPG").toString();
		iconfile = iconfile.substring(6);
		icon=Toolkit.getDefaultToolkit().getImage(iconfile);}catch(Exception e){
			e.printStackTrace();
		}
	}
	JTextField txtID = new JTextField(25);

	JPasswordField txtPwd = new JPasswordField(25);

	JTextField txtIP = new JTextField(25);

	JTextField txtPort = new JTextField(5);

	String id = "";

	String pwd = "";

	String server = "";

	String port = "";

	ClientEnd client = null;

	public boolean isover = false;

	public Login() {
		
		setTitle("RmiTalker��¼");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		String image = ClassLoader.getSystemResource("resource/banner.jpg").toString();
		image = image.substring(6);
		// System.out.println(image);
		JLabel lblBanner = new JLabel(new ImageIcon(image));
		lblBanner.setBounds(0, 0, 323, 48);
		contentPane.add(lblBanner);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JLabel lblID = new JLabel("RmiTalker��¼");
		JLabel lblPwd = new JLabel("RmiTalker����");
		JLabel lblServer = new JLabel("��������ַ:");
		JLabel lblPort = new JLabel("�˿�:");
		JButton ok = new JButton("��¼");
		JButton setup = new JButton("���á�");
		JButton register=new JButton("ע��...");
		Font font = new Font("��", Font.PLAIN, 12);
		Font f=new Font(null,Font.BOLD,12);
		p1.setBackground(new Color(241, 250, 255));
		p1.setLayout(null);
		p1.setLocation(5, 60);
		p1.setSize(310, 110);
		p1.setBorder(BorderFactory.createLineBorder(new Color(125, 220, 245)));
		lblID.setFont(font);
		lblID.setBounds(10, 20, 100, 20);
		lblID.setForeground(new Color(13, 55, 85));
		lblPwd.setFont(font);
		lblPwd.setForeground(new Color(13, 55, 85));
		lblPwd.setBounds(10, 60, 100, 20);
		txtID.setBounds(100, 20, 150, 20);
		txtPwd.setBounds(100, 60, 150, 20);
		txtID.setFont(f);
		txtPwd.setFont(f);
		p1.add(lblID);
		p1.add(lblPwd);
		p1.add(txtID);
		p1.add(txtPwd);
		contentPane.add(p1);

		ok.setBounds(240, 180, 60, 20);
		ok.setFont(font);
		ok.setBackground(new Color(226, 243, 253));
		ok.addActionListener(this);
		setup.setBounds(130, 180, 80, 20);
		setup.setBackground(new Color(226, 243, 253));
		setup.setFont(font);
		setup.addActionListener(this);
		register.setBounds(10, 180, 80, 20);
		register.setBackground(new Color(226, 243, 253));
		register.setFont(font);
		register.addActionListener(this);
		contentPane.add(ok);
		contentPane.add(setup);
		contentPane.add(register);

		// p2.setVisible(false);
		p2.setLayout(null);
		p2.setBackground(new Color(225, 245, 252));
		p2.setBorder(BorderFactory.createLineBorder(new Color(125, 220, 245)));
		lblServer.setBounds(5, 5, 80, 20);
		lblServer.setFont(font);
		txtIP.setBounds(80, 5, 100, 20);
		lblPort.setBounds(205, 5, 50, 20);
		lblPort.setFont(font);
		txtPort.setBounds(240, 5, 50, 20);
		p2.add(lblServer);
		p2.add(txtIP);
		p2.add(lblPort);
		p2.add(txtPort);
		p2.setBounds(5, 210, 310, 30);
		contentPane.add(p2);

		this.getRootPane().setDefaultButton(ok);
		contentPane.setBackground(new Color(225, 245, 252));
		setSize(330, 240);
		setCenter();
		setServer();
		setResizable(false);
		String iconfile = ClassLoader.getSystemResource("resource/rmitalker.JPG")
				.toString();
		iconfile = iconfile.substring(6);
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconfile));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("��¼")) {
			id = this.txtID.getText();
			pwd = String.valueOf(this.txtPwd.getPassword());
			server = this.txtIP.getText();
			port = this.txtPort.getText();
			boolean valid = checkValid() && checkServer();
			if (!valid) {
				JOptionPane.showMessageDialog(this, "�������ݲ��Ϸ�", "�Ƿ�����",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				try {
					TalkerServer ts = (TalkerServer) Naming.lookup("rmi://"
							+ server + ":" + port + "/server");
					client = new ClientEndImpl();
					if (ts != null) {
						if (ts.login(id, pwd, client)) {
							JQQClient qqClient = new JQQClient(id,ts,client);
							qqClient.setVisible(true);
							qqClient.receiveOfflineMessage();
							this.dispose();
						}else{
							JOptionPane.showMessageDialog(this, "�û������������||���Ѿ���¼�˺ţ�����");
							return;
						}
					}			
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "�޷����ӷ�������������������",
							"��¼ʧ��", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
					//this.dispose();
				}
			}
		} else if (cmd.equals("���á�")) {
			this.setSize(330, 280);
			((JButton) e.getSource()).setText("���á�");
			this.validateTree();
		} else if (cmd.equals("���á�")) {
			this.setSize(330, 240);
			((JButton) e.getSource()).setText("���á�");
			this.validateTree();
		}else if(cmd.equals("ע��...")){
			try {
				TalkerServer ts = (TalkerServer) Naming.lookup("rmi://"
						+ server + ":" + port + "/server");
				if (ts != null) {
					RegisterFrm register=new RegisterFrm(ts);
					register.setVisible(true);
				}			
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "�޷����ӷ����������Ժ�����",
						"����ʧ��", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
				this.dispose();
			}
		}
	}

	private void setCenter() {
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisSize = getSize();
		setLocation((scrSize.width - thisSize.width) / 2,
				(scrSize.height - thisSize.height) / 2);
	}

	private void setServer() {
		this.txtIP.setText("172.16.241.43");
		this.txtPort.setText("1099");
	}

	// ���������Ƿ�Ϸ�
	// ID���ȴ���5������Ϊ���֣����볤��Ϊ[6,20]��ֻ�ܰ�����ĸ�����֣����ַ�����Ϊ��ĸ
	private boolean checkValid() {
		boolean isValid = true;
		if (id.length() < 5 || pwd.length() < 6 || pwd.length() > 20) {
			isValid = false;
		}
		if (!id.matches("\\d{5,20}") || !pwd.matches("[[a-zA-Z]\\w]{6,20}")) {
			isValid = false;
		}
		return isValid;
	}

	private boolean checkServer() {
		if (!server.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")
				|| !port.matches("\\d{1,5}")) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Login login1 = new Login();
		login1.setVisible(true);
	}
}