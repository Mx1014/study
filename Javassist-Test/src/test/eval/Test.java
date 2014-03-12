package test.eval;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class Test {
	public static Object javaEval(String src) throws CannotCompileException,
			IllegalArgumentException, SecurityException,
			InvocationTargetException, IllegalAccessException {
		// 1������һ�������Class����
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("Eval$Class" + System.currentTimeMillis()
				+ Math.random());
		StringBuilder sb = new StringBuilder();
		// 2����src ����run��������
		sb.append("public static Object run(Object[] args){");
		sb.append(src);
		sb.append("return null;}");
		// 3�������ɵķ������뵽Class��
		CtMethod cm = CtNewMethod.make(sb.toString(), cc);
		cc.addMethod(cm);
		// 4���������ɵķ���
		return cc.toClass().getMethods()[0].invoke(null, (Object) null);
	}
	
	public static void main(String [] args) throws Exception, SecurityException, CannotCompileException,  InvocationTargetException{

        BufferedReader br = new BufferedReader( new InputStreamReader(System.in ));
        String line = null;
         while((line = br.readLine()) != null ){
               //exitΪ�˳�����
               if(line.equalsIgnoreCase( "exit"))
                     break;
               try{
                     javaEval(line);
              } catch(CannotCompileException e){
                     //������������Ϣ
                    System. err.println(e.getMessage());
              }
              System. out.println();
        }
        br.close();
  }
}
