package time;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class timeClientFrame extends JFrame {
  JPanel contentPane;
  Label label1 = new Label();
  Button button1 = new Button();
  int port;                              //�����鲥ʹ�õĶ˿�
  InetAddress group;                      //���������鲥��ʹ�õ��鲥���ַ
  MulticastSocket socket;                 //���������鲥��ʹ�õ�MulticastSocket��
  DatagramPacket packet;                  //�������ͺͽ���������ʹ�õ�DatagramPacket��

  public timeClientFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();            //��ƴ��岼��
    label1.setBounds(new Rectangle(70, 63, 266, 39));
    contentPane.setLayout(null);
    this.setSize(new Dimension(378, 235));
    this.setTitle("Time Client");
    button1.setLabel("����ʱ��");
    button1.setBounds(new Rectangle(132, 144, 126, 38));
    button1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button1_actionPerformed(e);
      }
    });
    contentPane.add(label1, null);
    contentPane.add(button1, null);
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void button1_actionPerformed(ActionEvent e) {           //�û����������ʱ�䡱��ťʱ�����Ķ���
    try{
      port = 5000;                                        //�����鲥��ļ����˿�Ϊ5000
      group = InetAddress.getByName("239.255.0.0");       //�����鲥��ĵ�ַΪ239.0.0.0
      socket = new MulticastSocket(port);                //��ʼ��MulticastSocket�ಢ���˿ں���֮����
      socket.joinGroup(group);                            //�����鲥��
      byte[] data = new byte[50];
      packet = new DatagramPacket(data,data.length,group,port);  //����һ��DatagramPacketʵ��
      socket.receive(packet);                             //�����鲥����ʱ�������������ʱ��
      String message = new String(packet.getData(),0,packet.getLength());
      label1.setText("����ʱ�䣺 " + message);              //�ڴ�������ʾʱ��
    }
    catch(Exception e1)
    {
      System.out.println("Error: " + e1);                 //��׽�쳣���
    }
  }
}