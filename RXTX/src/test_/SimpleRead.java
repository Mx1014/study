package test_;
import java.io.*;
import java.util.*;
//import javax.comm.*;
import gnu.io.*;

public class SimpleRead implements Runnable, SerialPortEventListener {//SerialPortEventListener ���ݴ��ж˿��¼�

    static CommPortIdentifier portId;    //CommPortIdentifierͨѶ�˿ڹ���
    static Enumeration portList;//ö����

    InputStream inputStream;
    SerialPort serialPort;    //SerialPort RS-232����ͨѶ�˿�
    Thread readThread;

    public static void main(String[] args) {

        portList = CommPortIdentifier.getPortIdentifiers();
        /*����������getPortIdentifiers�������һ��ö�ٶ���
        �ö����ְ�����ϵͳ�й���ÿ���˿ڵ�CommPortIdentifier����ע������Ķ˿ڲ�������ָ���ڣ�Ҳ�������ڡ�
        ������������Դ�������getPortIdentifiers(CommPort)������Ѿ���Ӧ�ó���򿪵Ķ˿����Ӧ��CommPortIdentifier���� 
        getPortIdentifier(String portName)��ȡָ���˿��������硰COM1������CommPortIdentifier����*/

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)/*getPortType�������ض˿�����*/ {
                 if (portId.getName().equals("COM2"))/* ��Windows�µĵ�һ������*/ {
               // if (portId.getName().equals("/dev/term/a"))/*��Unix-likeϵͳ�µĵ�һ������*/ {
                    SimpleRead reader = new SimpleRead();
                }
            }
        }
    }

    public SimpleRead() {
        try {
            serialPort = (SerialPort) portId.open("Serial_Communication", 2000);/* open������ͨѶ�˿ڣ����һ��CommPort����
            ��ʹ�����ռ�˿ڡ�����˿���������Ӧ�ó���ռ�ã���ʹ�� CommPortOwnershipListener�¼����ƣ�����һ��PORT_OWNERSHIP_REQUESTED�¼���
            ÿ���˿ڶ�����һ�� InputStream ��һ��OutputStream������˿�����open�����򿪵ģ���ô�κε�getInputStream����������ͬ�����������󣬳�����close �����á�
            ��������������һ��ΪӦ�ó��������ڶ����������ڶ˿ڴ�ʱ�����ȴ��ĺ�������*/
            
        } catch (PortInUseException e) {}
        try {
            inputStream = serialPort.getInputStream();/*��ȡ�˿ڵ�����������*/
        } catch (IOException e) {}
    try {
            serialPort.addEventListener(this);/*ע��һ��SerialPortEventListener�¼������������¼�*/
    } catch (TooManyListenersException e) {}

        serialPort.notifyOnDataAvailable(true);/*���ݿ���*/

        try {
            serialPort.setSerialPortParams(9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);/*���ô��ڳ�ʼ�������������ǲ����ʣ�����λ��ֹͣλ��У��*/
        } catch (UnsupportedCommOperationException e) {}

        readThread = new Thread(this);
        readThread.start();
    }

    public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {}
    }

//�����¼�
    public void serialEvent(SerialPortEvent event) {

        switch(event.getEventType()) {
        case SerialPortEvent.BI:/*Break interrupt,ͨѶ�ж�*/
        case SerialPortEvent.OE:/*Overrun error����λ����*/
        case SerialPortEvent.FE:/*Framing error����֡����*/
        case SerialPortEvent.PE:/*Parity error��У�����*/
        case SerialPortEvent.CD:/*Carrier detect���ز����*/
        case SerialPortEvent.CTS:/*Clear to send���������*/
        case SerialPortEvent.DSR:/*Data set ready�������豸����*/
        case SerialPortEvent.RI:/*Ring indicator������ָʾ*/
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is empty��������������*/
            break;

        case SerialPortEvent.DATA_AVAILABLE:/*Data available at the serial port���˿��п������ݡ������������飬������ն�*/
            byte[] readBuffer = new byte[20];

            try {
                while (inputStream.available() > 0) {
                    int numBytes = inputStream.read(readBuffer);
                    System.out.print(numBytes);
                }
                
                System.out.print(new String(readBuffer));
            } catch (IOException e) {}
            break;
        }
    }
}
