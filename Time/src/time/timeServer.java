package time;

import java.io.*;
import java.net.*;
import java.util.Date;

class timeServer {

  public timeServer()
  {
    int port;
    InetAddress group;
    MulticastSocket socket;
    try {
      port = 5000;                                   //�����鲥��ļ����˿�Ϊ5000
      group = InetAddress.getByName("239.255.0.0");  //�����鲥��ĵ�ַΪ239.0.0.0
      socket = new MulticastSocket(port);           //��ʼ��MulticastSocket�ಢ���˿ں���֮����
      socket.setSoTimeout(1000);                     //�����鲥���ݱ��ķ��ͷ�ΧΪ��������
      socket.setTimeToLive(1);                       //�����׽��ֵĽ������ݱ����ʱ��
      socket.joinGroup(group);                       //�����鲥��
      String outMessage;
      byte[] data;
      DatagramPacket packet;
      while(true)
      {
         Date time = new Date();                     //����һ��Date�����ʵ��������ȡ�õ�ǰʱ��
         int curyear = time.getYear() + 1900;       //�õ���ǰ���
         int curmonth = time.getMonth() + 1;        //�õ���ǰ�·�
         int curday = time.getDate();               //�õ���ǰ����
         int curhour = time.getHours();             //�õ���ǰСʱ
         int curminute = time.getMinutes();         //�õ���ǰ����
         int cursecond = time.getSeconds();         //�õ���ǰ����
         outMessage = "��Ԫ" + String.valueOf(curyear) + "��" + String.valueOf(curmonth) + "��" + String.valueOf(curday) + "��" + String.valueOf(curhour) + ":" + String.valueOf(curminute) + ":" + String.valueOf(cursecond);
         data = outMessage.getBytes();
         packet =  new DatagramPacket(data,data.length,group,port);  //����һ��DatagramPacketʵ��
         socket.send(packet);                                        //���鲥�鷢�͵�ǰʱ��
         System.out.println("Message sent: " + outMessage);
      }
    }
    catch(Exception e)
    {
      System.out.println("Error: " + e);               //��׽�쳣���
    }

  }

  public static void main(String[] args) {
      timeServer svr = new timeServer();
  }
}