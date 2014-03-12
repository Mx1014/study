/**
 * 
 */
package com.gshine.rmitalker.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.gshine.rmitalker.common.User;

/**
 * @author Administrator
 * 
 */
public interface ClientEnd extends Remote {

	// ���ܺ����б�
	public boolean sendFriendlist(User[] users) throws RemoteException;

	// ���ܺ�����Ϣ
	public boolean sendMessage(String id, String message)
			throws RemoteException;

	// ���ܷ������ĶϿ���Ϣ
	public void serverShutup() throws RemoteException;

	// �����ظ���¼
	public void IDReLogin() throws RemoteException;

	// ��Ӻ��ѵ��б���
	public void addUser(User user) throws RemoteException;
}
