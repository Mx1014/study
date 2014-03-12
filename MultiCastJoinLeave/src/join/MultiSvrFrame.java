package join;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
public class MultiSvrFrame extends JFrame {
  private JPanel contentPane;                          //��ƴ��岼��
  private Button button1 = new Button();
  private Button button2 = new Button();
  private Label label1 = new Label();
  private TextField textField1 = new TextField();
  private TextField textField2 = new TextField();
  private Label label2 = new Label();
  private Label label3 = new Label();
  int port;                                       //�����鲥ʹ�õĶ˿�
  MulticastSocket socket;                          //���������鲥��ʹ�õ�MulticastSocket��
  MulticastSocket soc;                             //����������뿪�鲥��ʹ�õ�MulticastSocket��
  InetAddress group;                               //���������鲥��ʹ�õ��鲥���ַ
  InetAddress addr;                                //����������뿪�鲥���õ��鲥���ַ
  public MultiSvrFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    button1.setLabel("�����鲥��");
    button1.setBounds(new Rectangle(77, 231, 99, 37));
    button1.addActionListener(new java.awt.event.ActionListener() {
     public void actionPerformed(ActionEvent e) {
        button1_actionPerformed(e);
      }
    });
    contentPane.setLayout(null);
    this.setSize(new Dimension(400, 317));
    this.setTitle("�鲥��Ա");
    button2.setLabel("�뿪�鲥��");
    button2.setBounds(new Rectangle(220, 231, 99, 36));
    button2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button2_actionPerformed(e);
      }
    });
    label1.setBounds(new Rectangle(75, 53, 248, 33));
    textField1.setBounds(new Rectangle(118, 112, 178, 32));
    textField2.setBounds(new Rectangle(117, 158, 87, 33));
    label2.setText("�鲥���ַ");
    label2.setBounds(new Rectangle(30, 112, 67, 29));
    label3.setText("�˿ں�");
    label3.setBounds(new Rectangle(32, 159, 75, 30));
    contentPane.add(button1, null);
    contentPane.add(button2, null);
    contentPane.add(label2, null);
    contentPane.add(textField1, null);
    contentPane.add(textField2, null);
    contentPane.add(label3, null);
    contentPane.add(label1, null);
    CreateMulticastGroup();
  }
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }
  void button1_actionPerformed(ActionEvent e) {
    try
     {
       soc = new MulticastSocket(Integer.parseInt(textField2.getText()));         //��ʼ��Ҫ�����鲥���MulticastSocket����֮���û�Ҫ��ʹ�õĶ˿ںŹ���
       addr = InetAddress.getByName(textField1.getText());                        //�����û�Ҫ������鲥��ĵ�ַ
       soc.joinGroup(addr);                                                       //�����û�ָ����ַ���鲥��
       label1.setText("�Ѽ����ַΪ" + addr.toString() + "���鲥��");                //��ʾ����ɹ���Ϣ
     }
    catch(Exception e1)
     {
       System.out.println("Error: " + e1);                                        //��׽�쳣���
     }
   }
  void button2_actionPerformed(ActionEvent e) {
    try
     {
       soc.leaveGroup(addr);                                                     //�뿪�鲥��
       label1.setText("���뿪��ַΪ" + addr.toString() + "���鲥��");               //��ʾ�뿪�ɹ���Ϣ
     }
    catch(Exception e1)
     {
       System.out.println("Error: " + e1);                                       //��׽�쳣���
     }
   }
  void CreateMulticastGroup()
   {
    try
     {
       port = 5000;                                           //�����鲥��ļ����˿�Ϊ5000
       group = InetAddress.getByName("239.0.0.0");            //�����鲥��ĵ�ַΪ239.0.0.0
       socket = new MulticastSocket(port);                    //��ʼ��MulticastSocket�ಢ���˿ں���֮����
     }
    catch(Exception e1)
     {
       System.out.println("Error " + e1);                      //��׽�쳣���
     }
   }
}
