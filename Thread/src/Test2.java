public abstract class Test2 extends Thread{
	public void run(){
		try {
			while (true)
			{
				process();
				System.out.println("��ʱ��������");
				sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void process() throws Exception;
}