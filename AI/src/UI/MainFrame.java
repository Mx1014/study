package UI;


import java.awt.BorderLayout;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;



import javax.swing.JDesktopPane;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import UI.painter.ImagePainter;

import synapesnet.Charaterrecognize;

/** ����� * */

public class MainFrame extends JFrame  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Charaterrecognize rognizer;// �ַ�ʶ��
	ImagePainter painter;
	MainFrame mframe;
	JDesktopPane desktopPane;
	public MainFrame() {
		this.mframe = this;
		rognizer = new Charaterrecognize();
		rognizer.trainning();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("��ʼ");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("ʶ��(ctrl+n)", KeyEvent.VK_N);
		newMenuItem.addActionListener(new ItemHandler());
		fileMenu.add(newMenuItem);
		JMenuItem caseMenuItem = new JMenuItem("ѵ��(ctrl+c)");
		caseMenuItem.setMnemonic(KeyEvent.VK_C);
		caseMenuItem.addActionListener(new ItemHandler());
		fileMenu.add(caseMenuItem);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        desktopPane = new JDesktopPane(); 
        contentPane.add(desktopPane); 
		this.setSize(1024, 600);
		this.setBounds(0, 0, 1024, 768);
		this.setVisible(true);
	}



	public class ItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if("ʶ��(ctrl+n)".equalsIgnoreCase(arg0.getActionCommand()))
			{
			RecFrame recframe = new RecFrame();
			desktopPane.add(recframe);
			}
			else if("ѵ��(ctrl+c)".equalsIgnoreCase(arg0.getActionCommand())){
				TrainFrame trainframe = new TrainFrame();
				desktopPane.add(trainframe);
				
			}

		}
	}
}
