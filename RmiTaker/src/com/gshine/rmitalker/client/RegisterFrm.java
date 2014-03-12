package com.gshine.rmitalker.client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.gshine.rmitalker.common.User;
import com.gshine.rmitalker.server.TalkerServer;

public class RegisterFrm extends JFrame implements ActionListener{
	JTextField txtid;
	JTextField txtname;
	JTextField txtrealname;
	JComboBox cmbSex;
	JPasswordField txtpwd;
	JPasswordField txtrepwd;
	TalkerServer server;
	public RegisterFrm(TalkerServer server){
		setTitle("ע�����û�");
		if(Login.icon!=null){
			setIconImage(Login.icon);
		}
		Font font = new Font("��", Font.PLAIN, 12);
		Color fcolor=new Color(13, 55, 85);
		Container container=getContentPane();
		container.setLayout(null);
		this.server=server;
		JLabel lblid=new JLabel("ID");
		lblid.setForeground(fcolor);
		lblid.setFont(font);
		lblid.setBounds(20,40,60,25);
		JLabel lblname=new JLabel("�ǳ�");
		lblname.setForeground(fcolor);
		lblname.setFont(font);
		lblname.setBounds(20,80,60,25);
		JLabel lblrealname=new JLabel("��ʵ����");
		lblrealname.setBounds(190,80,80,25);
		lblrealname.setForeground(fcolor);
		lblrealname.setFont(font);
		JLabel lblsex=new JLabel("�Ա�");
		lblsex.setForeground(fcolor);
		lblsex.setFont(font);
		lblsex.setBounds(20,120,60,25);
		JLabel lblpwd=new JLabel("����");
		lblpwd.setForeground(fcolor);
		lblpwd.setFont(font);
		lblpwd.setBounds(20,160,60,25);
		JLabel lblrepwd=new JLabel("ȷ������");
		lblrepwd.setForeground(fcolor);
		lblrepwd.setFont(font);
		lblrepwd.setBounds(190,160,80,25);
		
		txtid=new JTextField();
		txtid.setEditable(false);
		txtid.setText("�ȴ�ϵͳ����");
		txtid.setBackground(Color.white);
		txtid.setBounds(60,40,100,25);
		txtname=new JTextField();
		txtname.setBounds(60,80,100,25);
		txtrealname=new JTextField();
		txtrealname.setBounds(250,80,100,25);
		cmbSex=new JComboBox();
		cmbSex.addItem("��");
		cmbSex.addItem("Ů");
		cmbSex.setBackground(Color.white);
		cmbSex.setBounds(60,120,100,25);
		txtpwd=new JPasswordField();
		txtpwd.setBounds(60,160,100,25);
		txtrepwd=new JPasswordField();
		txtrepwd.setBounds(250,160,100,25);
		
		JPanel p1=new JPanel(null);
		p1.setBorder(BorderFactory.createLineBorder(new Color(125, 220, 245)));
		p1.setBounds(20,30,360,210);
		Color c1=new Color(241, 250, 255);
		p1.setBackground(c1);
		
		p1.add(lblid);
		p1.add(lblname);
		p1.add(lblpwd);
		p1.add(lblrealname);
		p1.add(lblrepwd);
		p1.add(lblsex);
		p1.add(txtid);
		p1.add(txtname);
		p1.add(txtrealname);	
		p1.add(txtpwd);	
		p1.add(txtrepwd);
		p1.add(cmbSex);
		container.add(p1);
		this.setSize(405,342);
		JButton btnOK=new JButton("ע��");
		btnOK.setForeground(fcolor);
		btnOK.setFont(font);
		JButton btnQuit=new JButton("�ر�");
		btnQuit.setForeground(fcolor);
		btnQuit.setFont(font);
		btnOK.setBackground(c1);
		btnQuit.setBackground(c1);
		btnOK.setBounds(150,260,60,25);
		btnQuit.setBounds(220,260,60,25);
		btnOK.addActionListener(this);
		btnQuit.addActionListener(this);
		container.add(btnOK);
		container.add(btnQuit);
		container.setBackground(new Color(126,194,213));
		setResizable(false);
		setCenter();
	}
	
	public void actionPerformed(ActionEvent e){
		String cmd=e.getActionCommand();
		if(cmd.equals("�ر�")){
			this.dispose();
		}else if(cmd.equals("ע��")){
			String name=txtname.getText();
			String realname=txtrealname.getText();
			String sex=(String)cmbSex.getSelectedItem();
			String pwd=new String(txtpwd.getPassword());
			String repwd=new String(txtrepwd.getPassword());
			//System.out.println(name.trim().length());
			if(name.trim().equals("")||realname.trim().equals("")||pwd.trim().equals("")||
					repwd.trim().equals("")){
				JOptionPane.showMessageDialog(this, "����Ϊ��");
				return;
			}
			if(pwd.length()<6){
				JOptionPane.showMessageDialog(this, "��������6λ");
				return;
			}
			if(!pwd.equals(repwd)){
				JOptionPane.showMessageDialog(this, "������ȷ�����벻һ��");
				return;
			}
			User user=new User();
			user.setName(name);
			user.setRealname(realname);
			user.setPwd(pwd);
			user.setSex(sex);
			try {
				user=server.register(user);
				JOptionPane.showMessageDialog(this,"��ϲ�㣬ע��ɹ������ID��Ϊ��"+user.getId()+",����Ϊ��"+
						user.getPwd()+",�뱣�ܺ��������롣","ע��ɹ�",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				return;
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "ע��ʧ�ܣ����Ժ�����");
				this.dispose();
				return;
			}
			
		}
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
		new RegisterFrm(null).setVisible(true);
	}

}
