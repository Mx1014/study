package _reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class<?> c = Class.forName("_reflect.Persion");

		Method m = c.getDeclaredMethod("getName");
		Persion p = new Persion();
		m.setAccessible(true);//����û��Ȩ�޵ķ���
		m.invoke(p);
	}
	
}
