package _uname;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIpPort {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString();//��ñ���IP
		String address=addr.getHostName().toString();//��ñ�������
		System.out.println(ip+"\\"+address);
	}
}
