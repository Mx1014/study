import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import net.miginfocom.swing.MigLayout;


public class Test2 extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1914154991551691383L;
	private JPanel contentPanel = new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref]"));

	
	public Test2(){
		
		//���һ����
		MyTree t = new MyTree();
		
		JScrollPane scroll = new JScrollPane(t);
		contentPanel.add(scroll,"spany,grow");

		//�ұ�һ��panel
		this.setContentPane(contentPanel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				new Test2();
			}
		});
	}
	
	
	private class MyTree extends JTree{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 2921959263179986603L;

		public MyTree(){
			
		    DefaultMutableTreeNode root = new DefaultMutableTreeNode("�й�");   
		    DefaultMutableTreeNode guangdong = new DefaultMutableTreeNode("�㶫");  
		    DefaultMutableTreeNode guangxi = new DefaultMutableTreeNode("����");  
		    DefaultMutableTreeNode foshan = new DefaultMutableTreeNode("��ɽ");  
		    DefaultMutableTreeNode shantou = new DefaultMutableTreeNode("��ͷ");  
		    DefaultMutableTreeNode guilin = new DefaultMutableTreeNode("����");  
		    DefaultMutableTreeNode nanning = new DefaultMutableTreeNode("����");  
		    //ͨ��add�����������ڵ�֮��ĸ��ӹ�ϵ  
	        guangdong.add(foshan);  
	        guangdong.add(shantou);  
	        guangxi.add(guilin);  
	        guangxi.add(nanning);  
	        root.add(guangdong); 
	        root.add(guangxi);  
	        this.setModel(new DefaultTreeModel(root, false));
	        //�Ը��ڵ㴴����  
	        //Ĭ������  
	        //tree.putClientProperty("JTree.lineStyle" , "Angeled");  
	        //û������  
	        this.putClientProperty("JTree.lineStyle" , "None");  
	        //ˮƽ�ָ���  
	        //tree.putClientProperty("JTree.lineStyle" , "Horizontal");   
	 
	        //�����Ƿ���ʾ���ڵ�ġ�չ��/�۵���ͼ��,Ĭ����false  
	        this.setShowsRootHandles(true);  
	        //���ýڵ��Ƿ�ɼ�,Ĭ����true  
	        this.setRootVisible(true);  
			
		}
		
		
		
	}
	
}
