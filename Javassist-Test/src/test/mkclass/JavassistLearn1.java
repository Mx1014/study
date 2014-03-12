package test.mkclass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtField.Initializer;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistLearn1{
	
	
	public static void main(String[] args) throws Exception{
		ClassPool cp=ClassPool.getDefault();
		CtClass ctClass=cp.makeClass("test");
		
		StringBuffer body=null;
		//����  1����������  2����������  3��������CtClass
		CtField ctField=new CtField(cp.get("java.lang.String"), "name", ctClass);
		ctField.setModifiers(Modifier.PRIVATE);
		//����name���Ե�get set����
		ctClass.addMethod(CtNewMethod.setter("setName", ctField));
		ctClass.addMethod(CtNewMethod.getter("getName", ctField));
		ctClass.addField(ctField, Initializer.constant("default"));
		
		//����  1����������   2��������CtClass
		CtConstructor ctConstructor=new CtConstructor(new CtClass[]{}, ctClass);
		body=new StringBuffer();
		body.append("{\n name=\"me\";\n}");
		ctConstructor.setBody(body.toString());
		ctClass.addConstructor(ctConstructor);
		
		//������  1����������  2����������  3�������������  4��������CtClass
		CtMethod ctMethod=new CtMethod(CtClass.voidType,"execute",new CtClass[]{},ctClass);
		ctMethod.setModifiers(Modifier.PUBLIC);
		body=new StringBuffer();
		body.append("{\n System.out.println(name);");
		body.append("\n System.out.println(\"execute ok\");");
		body.append("\n return ;");
		body.append("\n}");
		ctMethod.setBody(body.toString());
		ctClass.addMethod(ctMethod);
		Class<?> c=ctClass.toClass();
		Object o=c.newInstance();
		Method setMethod = o.getClass().getMethod("setName", new Class[]{String.class});
		setMethod.invoke(o, "asssdasd");
		Method method=o.getClass().getMethod("execute", new Class[]{});
		//�����ֽ����������execute����
		method.invoke(o, new Object[]{});
	}

}

