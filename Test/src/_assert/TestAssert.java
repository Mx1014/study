package _assert;

public class TestAssert {

	public static void main(String[] args) {
		boolean isOpen = false;  
		   assert isOpen=true; //��������˶��ԣ��ὫisOpen��ֵ��Ϊtrue 
		   System.out.println(isOpen);//��ӡ�Ƿ�
		TestAssert ta = new TestAssert ();
		assert true;
		ta.test(null);
	}
	
	
	public void test(String msg){
		assert msg!=null:"msg is not null";
		System.out.println(msg.split("sdf"));
	}
	
}
