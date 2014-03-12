package com.gshine.rmitalker.client;

import java.awt.Image;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.gshine.rmitalker.common.User;

public class ClientEndImpl extends UnicastRemoteObject implements ClientEnd {

	JQQClient client=null;
	
	
	
	public ClientEndImpl()throws RemoteException{
		
	}
	
	public boolean sendFriendlist(User[] users) throws RemoteException {
		// TODO Auto-generated method stub
		if(users!=null){
			client.updateUserlist(users);
			return true;
		}
		return false;
	}
	
	public void addUser(User user)throws RemoteException{
		if(user!=null){
			client.addUser(user);
		}
	}
	public boolean sendMessage(String id, String message)
			throws RemoteException {
		client.receiveMessage(id, message);
		return true;
	}
	
	public void serverShutup()
		throws RemoteException{
		this.client.quit("��������ʱ�����ã������ʱ�����µ�¼��");
	}
	
	public void IDReLogin()throws RemoteException{
		this.client.quit("���ĺ����Ѿ��ڱ𴦵�¼�����ڳ����˳�");
	}
	public void setClient(JQQClient client){
		this.client=client;
	}
	public JQQClient getClient(){
		return this.client;
	}
}
