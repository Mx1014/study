package test_;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import test_.serial.WriteBean;



/*try{    

ir=new InputStreamReader(System.in); //�Ӽ��̽�����һ���ַ��������룬��������һ���ַ��������Ķ���
in=new BufferedReader(ir);
String s=in.readLine();//��������in�ж���һ�У�������ȡ��ֵ��ֵ���ַ�������s
System.out.println("Input value is: "+s);
int i = Integer.parseInt(s);//ת����int��
i*=2;
System.out.println("Input value changed after doubled: "+i);
}catch(IOException e)
{System.out.println(e);}
*/

public class NumberInput{
	public static void main(String args[]){
		try{
			loop1:for(int i=0;i<5;i++){
			InputStreamReader ir;
			BufferedReader in;
			//ir=new InputStreamReader(System.in);
			ir=new InputStreamReader(System.in,"8859_1");
			in=new BufferedReader(ir);
			//new WriteBean((byte)ir);
			String s=in.readLine();//��������in�ж���һ�У�������ȡ��ֵ��ֵ���ַ�������s
			System.out.println("Input value is: "+s);
			/*int i = Integer.parseInt(s);//ת����int��
			
			i*=2;
			int c=i;
			System.out.println("Input value changed after doubled: "+c);*/
			
			
				if(s.equals("guanji")){
			//
			
			byte[] order=new byte[] { 0x3A, 0x48, 0x7A, 0x5F,(byte) 0xFF, (byte) 0xFF, 0x73, 0x68, 0x75, 0x74, 0x77, 0x03 };
			
			new WriteBean(order);
			System.out.println("/////////////////");
			break;
			}
			else
			{
				System.out.println("�Բ���������Ĳ����ڲ���ִ��������������룺");
				continue loop1;
			}
				}
		
		//System.out.println("�Բ���5�β�����Ч��Ո����x�����f������ô�ɷ��w�ˣ�����");
			
		}catch(IOException e){System.out.println(e);}
	}
}