package test_.serial;
import java.util.Date;
import java.text.*;

public class GetTime {
	public String GetTime(){
		Date d = new Date();
		// String str = d.toString();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  kk:mm:ss ");// ����yyyy-MM-dd����Ҫ��ʾ�ĸ�ʽ
		// ����������ϣ����޸����ʹ��򣻾����ʾΪ��MM-month,dd-day,yyyy-year;kk-hour,mm-minute,ss-second;
		String str=sdf.format(d);
		//System.out.println("��ȡ��Ƭ��Ϣʱ�� : " +str);
		return str;
	}

}
