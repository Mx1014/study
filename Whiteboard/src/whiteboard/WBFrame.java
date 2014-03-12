package whiteboard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.net.*;

public class WBFrame extends JFrame
{
  JLabel jLabel1;
  JPanel contentPane;
  JScrollPane jScrollPane1;
  JTextArea jTextArea1;
  JTextField jTextField1;
  JButton jButton1;
  JTextField jTextField2;
  JButton jButton2;
  JButton jButton3;
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;
  int port;                                       //�����鲥ʹ�õĶ˿�
  MulticastSocket socket;                          //���������鲥��ʹ�õ�MulticastSocket��
  InetAddress group;                               //���������鲥��ʹ�õ��鲥���ַ
  DatagramPacket packet;                           //�������ͺͽ���������ʹ�õ�DatagramPacket��
  String username;                                 //�����û���
  Canvas canvas1;                                  //��������
  boolean isMember;                               //�����ж��Ƿ��Ѿ������鲥��ı���
  Color color = new Color(255,0,0);                //��������ʹ�õ���ɫΪ��ɫ
  int startx;                                      //������ͼ����������
  int starty;                                      //������ͼ�����������
  int endx;                                        //������ͼ���յ������
  int endy;                                        //������ͼ���յ�������

  public WBFrame()
  {
    jScrollPane1 = new JScrollPane();
    jTextArea1 = new JTextArea();
    jTextField1 = new JTextField();
    jButton1 = new JButton();
    jTextField2 = new JTextField();
    jButton2 = new JButton();
    jLabel1 = new JLabel();
    canvas1 = new Canvas();
    jButton3 = new JButton();
    isMember = false;
    enableEvents(64L);
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception
  {
    contentPane = (JPanel)getContentPane();              //��Ʋ���
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    contentPane.setLayout(null);
    setResizable(false);
    setSize(new Dimension(620, 372));
    setTitle("�װ����");
    jScrollPane1.setBounds(new Rectangle(8, 5, 335, 211));
    jTextField1.setEnabled(false);
    jTextField1.setBounds(new Rectangle(8, 234, 248, 31));
    jButton1.setBounds(new Rectangle(267, 233, 75, 31));
    jButton1.setEnabled(false);
    jButton1.setBorder(titledBorder1);
    jButton1.setText("����");
    jButton3.setEnabled(false);
    canvas1.setEnabled(false);
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jTextArea1.setEnabled(false);
    jTextField2.setBounds(new Rectangle(98, 289, 158, 31));
    jButton2.setBounds(new Rectangle(265, 288, 79, 35));
    jButton2.setBorder(titledBorder2);
    jButton2.setText("����");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jLabel1.setText("�û���:");
    jLabel1.setBounds(new Rectangle(9, 289, 81, 28));
    contentPane.setBackground(new Color(184, 184, 217));
    canvas1.setBackground(Color.white);
    canvas1.setBounds(new Rectangle(358, 5, 251, 269));
    canvas1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        canvas1_mousePressed(e);
      }
      public void mouseReleased(MouseEvent e) {
        canvas1_mouseReleased(e);
      }
    });
    jButton3.setBounds(new Rectangle(440, 290, 91, 32));
    jButton3.setBorder(titledBorder3);
    jButton3.setText("���");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    contentPane.add(jScrollPane1, null);
    contentPane.add(jTextField1, null);
    contentPane.add(jButton1, null);
    contentPane.add(jTextField2, null);
    contentPane.add(jButton2, null);
    contentPane.add(jLabel1, null);
    contentPane.add(canvas1, null);
    contentPane.add(jButton3, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
  }

  protected void processWindowEvent(WindowEvent e)
  {
    super.processWindowEvent(e);
    if(e.getID() == 201)
       System.exit(0);
  }

  void jButton2_actionPerformed(ActionEvent e)           //���û���������롱�����뿪������ťʱ�����Ķ���
  {
    if (!isMember)                                       //����û������鲥���Ա������鲥��
    {
      try
      {
        port = 5000;                                      //���ö˿ں�
        group = InetAddress.getByName("239.0.0.0");       //�����鲥���ַ
        socket = new MulticastSocket(port);              //��ʼ��MulticastSocketʵ��
        socket.setTimeToLive(1);                          //�����鲥���ݱ��ķ��ͷ�ΧΪ��������
        socket.setSoTimeout(10000);                       //�����׽��ֵĽ������ݱ����ʱ��
        socket.joinGroup(group);                          //������鲥��
        username = jTextField2.getText();                 //�õ��û���ʹ�õ��û���
        String tmp = String.valueOf(String.valueOf(username)).concat(" has joined the group");
        byte[] data = tmp.getBytes();
        packet = new DatagramPacket(data, data.length, group, port);  //��ʼ��DatagramPacketʵ��
        socket.send(packet);                              //���鲥�鷢�ʹ��û��Ѽ����鲥�����Ϣ
        jButton1.setEnabled(true);                        //���湦�ܵĿ��û򲻿��õ�����
        jTextField1.setEnabled(true);
        jTextArea1.setEnabled(true);
        jTextArea1.setEditable(false);
        jButton3.setEnabled(true);
        canvas1.setEnabled(true);
        jTextField2.setText("");
        jButton2.setText("�뿪");
        isMember = true;                                  //���û��ѳ�Ϊ���鲥��ĳ�Ա
      }
      catch(Exception e1)
      {
        System.out.println("Error: " + e1);               //��׽�쳣���
      }
    }
    else
    {
      try
      {
        String tmp = String.valueOf(String.valueOf(username)).concat(" has left the group");
        byte[] data = tmp.getBytes();
        packet = new DatagramPacket(data, data.length, group, port);
        socket.send(packet);                               //���鲥�鷢�����û��뿪����Ϣ
        socket.leaveGroup(group);                          //�뿪���鲥��
        jButton1.setEnabled(false);                        //���湦�ܵĿ��û򲻿��õ�����
        jTextField1.setEnabled(false);
        jTextArea1.setEnabled(false);
        jButton3.setEnabled(false);
        canvas1.setEnabled(false);
        jTextField2.setText("");
        jButton2.setText("����");
        isMember = false;                                  //���û��Ѳ����鲥��ĳ�Ա
      }
     catch(Exception e1)
     {
       System.out.println("Error: " + e1);                  //��׽�쳣���
     }
   }
 }

  void jButton1_actionPerformed(ActionEvent e)              //�û���������͡���ťʱ�����Ķ���
  {
    try
    {
      String tmp = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(username)))).append(" said: ").append(jTextField1.getText())));
      byte[] data = tmp.getBytes();
      packet = new DatagramPacket(data, data.length, group, port);
      socket.send(packet);                                  //�õ��û���Ҫ���͵���Ϣ�����鲥�鷢��
      jTextField1.setText("");
    }
    catch(Exception e1)
    {
      System.out.println("Error: " + e1);                   //��׽�쳣���
    }
  }

  void canvas1_mousePressed(MouseEvent e)                  //���û��ڻ������������ʱ�����Ķ���
  {
    startx = e.getX();                                      //���µ�ĺ�������Ϊ���ݴ��͵���������
    starty = e.getY();                                      //���µ����������Ϊ���ݴ��͵����������
  }

  void canvas1_mouseReleased(MouseEvent e)                 //���û��ڻ�����̧�����ʱ�����Ķ���
  {
    endx = e.getX();                                        //̧���ĺ�������Ϊ���ݴ��͵��յ������
    endy = e.getY();                                        //̧������������Ϊ���ݴ��͵����������
    Graphics g = canvas1.getGraphics();                     //�ڱ��������ϻ���
    g.setColor(color);
    g.drawLine(startx,starty,endx,endy);
    //���������鲥�鴫�ʹ��û��Ļ�ͼ��Ϣ����Ϣ�����û������ߵ������յ�ĺ�����������꣬��Ϣ�ԡ�@��������ͷ���Ա�������Ϣ�ǻ�ͼ��Ϣ��ÿ��������Ϣʹ�á�@�����ŷָ�
    String tmp = "@" + String.valueOf(startx) + "@" + String.valueOf(starty) + "@" + String.valueOf(endx) + "@" + String.valueOf(endy) + "@";
    try
    {
      byte[] data = tmp.getBytes();
      packet = new DatagramPacket(data, data.length, group, port);
      socket.send(packet);
    }
    catch(Exception e1)
    {
      System.out.println("Error: " + e1);                   //��׽�쳣���
    }
  }

  void jButton3_actionPerformed(ActionEvent e)              //�û��������������ťʱ�����Ķ���
  {
    canvas1.setBackground(Color.black);
    canvas1.setBackground(Color.white);
  }

  public boolean waitforpackets()                         //�ȴ��鲥�鷢�͵���Ϣ���ڱ�������ʾ�����ĺ���
  {
    byte[] packetdata = new byte[512];                    //��ʼ���������ݵ�DatagramPacketʹ�õ�����
    try
    {
      packet.setData(packetdata);                           //�趨�������ݵ�DatagramPacketʵ���������С
      packet.setLength(512);                                //�趨�������ݵ�DatagramPacketʵ���ĳ���
      socket.receive(packet);                               //���鲥���������
    }
    catch (Exception e1){
      return true;                                        //���û������Ϣ�򷵻�
    }
    packetdata = packet.getData();
    if (packetdata[0]!='@')                                 //�ж���������Ϣ���ǻ�ͼ��Ϣ
    {
      jTextArea1.append(String.valueOf(String.valueOf(new String(packetdata))).concat("\n"));  //�����������Ϣ����������Ϣ������ʾ
    }
    else                                           //�������Ϣ�ǻ�ͼ��Ϣ�Ļ�
    {
      try                                          //���³����ʵ�ֽ���ͼ�����յ��������Ϣ��ȡ�����Ĺ���
      {
        byte[] data;
        int i = 1;
        int size = 0;
        int startpos = i;
        while(packetdata[i]!='@')
        {
          i++;
          size++;
        }
        i = startpos;
        int j = 0;
        data = new byte[size];
        while(packetdata[i]!='@')
        {
          data[j] = packetdata[i];
          i++;
          j++;
        }
        startx = Integer.parseInt(new String(data));
        i++;
        startpos = i;
        size = 0;
        while(packetdata[i]!='@')
        {
          i++;
          size++;
        }
        i = startpos;
        j = 0;
        data = new byte[size];
        while(packetdata[i]!='@')
        {
          data[j] = packetdata[i];
          i++;
          j++;
        }
        starty = Integer.parseInt(new String(data));
        i++;
        startpos = i;
        size = 0;
        while(packetdata[i]!='@')
        {
          i++;
          size++;
        }
        i = startpos;
        j = 0;
        data = new byte[size];
        while(packetdata[i]!='@')
        {
          data[j] = packetdata[i];
          i++;
          j++;
        }
        while(packetdata[i]!='@')
        {
          data[j] = packetdata[i];
          i++;
          j++;
        }
        endx = Integer.parseInt(new String(data));
        i++;
        startpos = i;
        size = 0;
        while(packetdata[i]!='@')
        {
          i++;
          size++;
        }
        i = startpos;
        j = 0;
        data = new byte[size];
        while(packetdata[i]!='@')
        {
          data[j] = packetdata[i];
          i++;
          j++;
        }
        while(packetdata[i]!='@')
        {
          data[j] = packetdata[i];
          i++;
          j++;
        }
        endy = Integer.parseInt(new String(data));
        Graphics g = canvas1.getGraphics();                    //�ڱ�����ʹ�����紫���Ļ�ͼ��Ϣ��ͼ
        g.setColor(color);
        g.drawLine(startx,starty,endx,endy);
      }
      catch(Exception e1)
      {
        System.out.println("Error" + e1);                      //��׽�쳣���
      }
    }
    return true;                                            //����
  }
}
