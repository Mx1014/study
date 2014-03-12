import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TreeDemo1{
  public TreeDemo1(){
    JFrame f=new JFrame("TreeDemo1");
    Container contentPane=f.getContentPane();
    
    String[] s1={"��˾�ļ�","�����ż�","˽���ļ�"}; 
    String[] s2={"��������(C:)","��������(D:)","��������(E:)"};
    String[] s3={"��Ħվ","ְ����Ϣ","�������"};
    
    Hashtable hashtable1=new Hashtable();
    Hashtable hashtable2=new Hashtable();
    hashtable1.put("�ҵĹ��İ�",s1);
    hashtable1.put("�ҵĵ���",s2);
    hashtable1.put("�ղؼ�",hashtable2);
    hashtable2.put("��վ�б�",s3);
    
    Font font = new Font("Dialog", Font.PLAIN, 12);
    Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
   /**����widnows����**/
    while (keys.hasMoreElements()) {
         Object key = keys.nextElement();
         if (UIManager.get(key) instanceof Font) {
             UIManager.put(key, font);
         }
   } 
   try{
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  
   }catch(Exception el){
      System.exit(0);  
   }
   /**����widnows����**/
    JTree tree=new JTree(hashtable1);
    JScrollPane scrollPane=new JScrollPane();
    scrollPane.setViewportView(tree);
  contentPane.add(scrollPane);
  f.pack();
  f.setVisible(true);
  f.addWindowListener(new WindowAdapter(){
  public void windowClosing(WindowEvent e){
     System.exit(0); 
  }
  });
  } 
  public static void main(String[] args){
  new TreeDemo1();
  }
}