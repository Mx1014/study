package sample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class MainFrame2 extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -347003037896182059L;


	private JPanel contentPane = new JPanel(new MigLayout("","[grow,15%][grow]","[grow]"));
	
	private boolean isDisplay = true;
	
	public MainFrame2(){
		
		init();

		this.setContentPane(contentPane);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {logout();}
		});
		
	}
	
	private void init(){
		
		//leftPanel
		createLeftPanel();
		contentPane.add(leftTabPane, "spany,grow");
		
		//rightPanel
		createRightPanel();
		contentPane.add(rightPanel,"spany,grow");
		
	}

	private JPanel rightPanel;
	
	private JTextField tf_LogicAddr;
	private JTextField tf_LastHeartBeat;
	private JTextField tf_LastGprs;
	private JButton b_displaySwitch;
	
	private void createRightPanel() {
		
		rightPanel = new JPanel(new MigLayout("wrap","[][][grow][]","[][][grow][][]"));
		
		rightPanel.add(Constant.createLabel("�ն��߼���ַ:"),"l");
		
		tf_LogicAddr=Constant.createTextField("",20,false);
		rightPanel.add(tf_LogicAddr,"wrap");
		
		rightPanel.add(Constant.createLabel("���һ������ʱ��:"),"l");
		
		tf_LastHeartBeat=Constant.createTextField("",20,false);
		rightPanel.add(tf_LastHeartBeat,"split");
		
		rightPanel.add(Constant.createLabel("���һ��GPRSʱ��:"),"l");
		
		tf_LastGprs=Constant.createTextField("",20,false);
		rightPanel.add(tf_LastGprs,"wrap");
		
		rightPanel.add(Constant.createLabel("������Ϣ:"),"t");
		rightPanel.add(Constant.createTextAreaScroll("", 20, 60, true),"span 3 3,grow");
		b_displaySwitch=Constant.createButton("��ֹ��ʾ", false);
		rightPanel.add(b_displaySwitch,"t");
		rightPanel.add(Constant.createButton("���", false),"t");
		
		
		
		b_displaySwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isDisplay=!isDisplay;
				String buttonText;
				buttonText=isDisplay?"��ֹ��ʾ":"��ʼ��ʾ";
				b_displaySwitch.setText(buttonText);
			}
		});
	}

	private JTree leftTree;
	private JScrollPane leftTreePane;
	
	private JTabbedPane leftTabPane;
	private JPanel leftQueryPane;
	
	
	
	private void createLeftPanel() {
		leftTabPane = new JTabbedPane(JTabbedPane.LEFT);
		createLeftTreePane();
		
		createLeftQueryPane();
		
		
		leftTabPane.addTab("<html>\n<br>��<br>\t<br>��<br>\n<br></html>", leftTreePane);
		leftTabPane.addTab("<html>\n<br>��<br>\t<br>Ѱ<br>\n<br></html>", leftQueryPane);
	}

	private void createLeftQueryPane() {


		leftQueryPane = new JPanel(new MigLayout("","[][grow]","[][][grow]"));
		
		
		//label  text
		leftQueryPane.add(Constant.createLabel("�豸��:"),"split");
		leftQueryPane.add(Constant.createTextField(20),"wrap");
		//label
		leftQueryPane.add(Constant.createLabel("��ѯ���:"),"split,wrap");
		//list
		JScrollPane list2 = new JScrollPane(new JList());
		leftQueryPane.add(list2, "span,grow");
	}

	private void createLeftTreePane() {
		leftTree=createLeftTree();
		leftTreePane  = new JScrollPane(leftTree);
	}
	
	
	

	private JTree createLeftTree() {
		
		//getRoot;
		JTree tree = new JTree();
		//1.���ظ����� 
		
		//2.���ص�һ������
		
		return tree;
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				new MainFrame2();
			}
		});
	}
	
	public void logout(){
		
		System.out.println("logout...");
		
	}
	
}
