package test.mkclass;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

public class JavassistLearn{
	
	
	public static void main(String[] args) throws Exception{
		ProxyFactory factory=new ProxyFactory();
		//���ø��࣬ProxyFactory���ᶯ̬����һ���࣬�̳иø���
		factory.setSuperclass(JavassistClass.class);
		//���ù��������ж���Щ����������Ҫ������
		factory.setFilter(new MethodFilter() {
			@Override
			public boolean isHandled(Method m) {
				if(m.getName().equals("getName")){
					return true;
				}
				return false;
			}
		});
		//�������ش���
		factory.setHandler(new MethodHandler() {
			@Override
			public Object invoke(Object self, Method thisMethod, Method proceed,
					Object[] args) throws Throwable {
				//���غ�ǰ�ô�������дname���Ե�����
				//ʵ������ɸ��������޸�
				JavassistClass o=(JavassistClass) self;
				o.setName("haha");
				return proceed.invoke(self, args);
			}
		});
		
		Class<?> c=factory.createClass();
		JavassistClass object=(JavassistClass) c.newInstance();
		System.out.println(object.getName());
		
	}

}
