package test_.serial;

import java.sql.*;

public class DBConnection {
    public void DBC (){
        try{
        String strurl="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=D:\\SQL\\db.mdb";//���ACCESS�ļ�λ��
        //String strurl ="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=db.mdb";//��ΪNO-DSN��ʽ
        //String strurl="jdbc:odbc:test";
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection conn=DriverManager.getConnection(strurl);
        System.out.println("���ӳɹ�!");
        Statement stmt=conn.createStatement();
       // ResultSet rs=stmt.executeQuery("select * from code");
        //ResultSet rs= stmt.executeQuery("select * from code where code="+"'"+"bbbb"+"'");
       stmt.executeUpdate("update code set code ="+"'"+"gfg"+"'"+"where code="+"'"+"ggggg"+"'");
       ResultSet rs=stmt.executeQuery("select * from code");
        while(rs.next())
        {
            String s= rs.getString("code");
        	System.out.println(s);
        }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
  
    public static void main(String[] args) throws ClassNotFoundException {
        DBConnection dbc = new DBConnection();
        dbc.DBC();
    }
} 
