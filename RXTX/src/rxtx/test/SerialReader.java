package rxtx.test;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.TooManyListenersException;

public class SerialReader extends Observable implements Runnable,
		SerialPortEventListener {
	static CommPortIdentifier portId;
	int delayRead = 200;
	int numBytes; // buffer�е�ʵ�������ֽ���
	private static byte[] readBuffer = new byte[20]; // 4k��buffer�ռ�,���洮�ڶ��������
	static Enumeration portList;
	InputStream inputStream;
	SerialPort serialPort;
	HashMap serialParams;
	// �˿ڶ��������¼�������,�ȴ�n������ٶ�ȡ,�Ա�������һ���Զ���
	public static final String PARAMS_DELAY = "delay read"; // ��ʱ�ȴ��˿�����׼����ʱ��
	public static final String PARAMS_TIMEOUT = "timeout"; // ��ʱʱ��
	public static final String PARAMS_PORT = "port name"; // �˿�����
	public static final String PARAMS_DATABITS = "data bits"; // ����λ
	public static final String PARAMS_STOPBITS = "stop bits"; // ֹͣλ
	public static final String PARAMS_PARITY = "parity"; // ��żУ��
	public static final String PARAMS_RATE = "rate"; // ������

	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI: // 10
		case SerialPortEvent.OE: // 7
		case SerialPortEvent.FE: // 9
		case SerialPortEvent.PE: // 8
		case SerialPortEvent.CD: // 6
		case SerialPortEvent.CTS: // 3
		case SerialPortEvent.DSR: // 4
		case SerialPortEvent.RI: // 5
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2
			break;
		case SerialPortEvent.DATA_AVAILABLE: // 1
			try {
				// ��ζ�ȡ,���������ݶ���
				// while (inputStream.available() > 0) {
				// numBytes = inputStream.read(readBuffer);
				// }
				numBytes = inputStream.read(readBuffer);
				changeMessage(readBuffer, numBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * ��ʼ���˿ڲ����Ĳ���.
	 * 
	 * 
	 * @see
	 */
	public SerialReader(HashMap params) {
		this.addObserver(new ReaderObserver());
		serialParams = params;
		init();
	}

	public static void main(String[] args) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put(PARAMS_TIMEOUT, "1000");
		params.put(PARAMS_RATE, "2400");
		params.put(PARAMS_DATABITS, "8");
		params.put(PARAMS_STOPBITS, "1");
		params.put(PARAMS_PARITY, "1");
		params.put(PARAMS_DELAY, "2400");
		params.put(PARAMS_PORT, "COM3");
		SerialReader sr = new SerialReader(params);
	}
	private void init() {
		try {
			// ������ʼ��
			int timeout = Integer.parseInt(serialParams.get(PARAMS_TIMEOUT)
					.toString());
			int rate = Integer.parseInt(serialParams.get(PARAMS_RATE)
					.toString());
			int dataBits = Integer.parseInt(serialParams.get(PARAMS_DATABITS)
					.toString());
			int stopBits = Integer.parseInt(serialParams.get(PARAMS_STOPBITS)
					.toString());
			int parity = Integer.parseInt(serialParams.get(PARAMS_PARITY)
					.toString());
			delayRead = Integer.parseInt(serialParams.get(PARAMS_DELAY)
					.toString());
			String port = serialParams.get(PARAMS_PORT).toString();
			// �򿪶˿�
			portId = CommPortIdentifier.getPortIdentifier(port);
			serialPort = (SerialPort) portId.open("SerialReader", timeout);
			inputStream = serialPort.getInputStream();
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			serialPort.setSerialPortParams(rate, dataBits, stopBits, parity);
		} catch (PortInUseException e) {
			System.out.println("�˿��Ѿ���ռ��!");
			e.printStackTrace();
		} catch (TooManyListenersException e) {
			System.out.println("�˿ڼ����߹���!");
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			System.out.println("�˿ڲ������֧��!");
			e.printStackTrace();
		} catch (NoSuchPortException e) {
			System.out.println("�˿ڲ�����!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ͨ��observer pattern���յ������ݷ��͸�observer
	// ��buffer�еĿ��ֽ�ɾ�����ٷ��͸�����Ϣ,֪ͨ�۲���
	public void changeMessage(byte[] message, int length) {
		setChanged();
		byte[] temp = new byte[length];
		System.arraycopy(message, 0, temp, 0, length);
		notifyObservers(temp);
	}

	static void listPorts() {
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier portIdentifier = (CommPortIdentifier) portEnum
					.nextElement();
			System.out.println(portIdentifier.getName() + " - "
					+ getPortTypeName(portIdentifier.getPortType()));
		}
	}

	static String getPortTypeName(int portType) {
		switch (portType) {
		case CommPortIdentifier.PORT_I2C:
			return "I2C";
		case CommPortIdentifier.PORT_PARALLEL:
			return "Parallel";
		case CommPortIdentifier.PORT_RAW:
			return "Raw";
		case CommPortIdentifier.PORT_RS485:
			return "RS485";
		case CommPortIdentifier.PORT_SERIAL:
			return "Serial";
		default:
			return "unknown type";
		}
	}

	/**
	 * @return A HashSet containing the CommPortIdentifier for all serial ports
	 *         that are not currently being used.
	 */
	public static HashSet<CommPortIdentifier> getAvailableSerialPorts() {
		HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
		Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
		while (thePorts.hasMoreElements()) {
			CommPortIdentifier com = (CommPortIdentifier) thePorts
					.nextElement();
			switch (com.getPortType()) {
			case CommPortIdentifier.PORT_SERIAL:
				try {
					CommPort thePort = com.open("CommUtil", 50);
					thePort.close();
					h.add(com);
				} catch (PortInUseException e) {
					System.out.println("Port, " + com.getName()
							+ ", is in use.");
				} catch (Exception e) {
					System.out.println("Failed to open port " + com.getName()
							+ e);
				}
			}
		}
		return h;
	}
	
	class ReaderObserver implements Observer{
		
		@Override
		public void update(Observable o, Object arg) {
			System.out.println((new String((byte[])arg)));
		}
		
	}
}
