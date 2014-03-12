import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TestJLayeredPane
{
	JFrame jf = new JFrame("����JLayeredPane");
	JLayeredPane layeredPane = new JLayeredPane();
	public void init()
	{
		//��layeredPane�����3�����
		layeredPane.add(new ContentPanel(10 , 20 , "Struts2Ȩ��ָ��" , 
			"ico/struts2.jpg"), JLayeredPane.MODAL_LAYER);
		layeredPane.add(new ContentPanel(100 , 60 , "RoR���ݿ������ʵ��", 
			"ico/ror.jpg"), JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(new ContentPanel(190 , 100 , "������J2EE��ҵӦ��ʵս", 
			"ico/j2ee.jpg"), 4);
		layeredPane.setPreferredSize(new Dimension(400, 300));
		layeredPane.setVisible(true);
		jf.add(layeredPane);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	public static void main(String[] args) 
	{
		new TestJLayeredPane().init();
	}
}
//��չ��JPanel�࣬����ֱ�Ӵ���һ������ָ��λ�ã�
//����ָ�����⡢����ָ��ͼ���JPanel����
class ContentPanel extends JPanel
{
	public ContentPanel(int xPos , int yPos , String title , String ico)
	{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
		JLabel label = new JLabel(new ImageIcon(ico));
		add(label);
		setBounds(xPos , yPos , 160, 200);
	}
}
