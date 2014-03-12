package com.gshine.rmitalker.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.gshine.rmitalker.client.ClientEnd;
import com.gshine.rmitalker.common.User;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: dlut
 * </p>
 * 
 * @author g.shine
 * @version 1.0
 */

public interface TalkerServer extends Remote {

	//��¼����������е�User��������ֻ�õ�id��pwd,ͬʱ�������ע�ᣬ���ڷ������ؽУ���ͻ��˴���Ϣ
	public boolean login(String id,String pwd,ClientEnd client)throws RemoteException;

	// ע�����û��������User�в�����ID�ţ����������ش���ID�ŵ�User����
	public User register(User u)throws RemoteException;
	
	//���������û�ID,�����û��Ӻ���
	public User[] getAllUsers()throws RemoteException;
	
	//��ȡ�����б�
	public User[] getAllFriends(String id)throws RemoteException;
	
	//��Ӻ���
	public boolean addFriend(String sid,String fid)throws RemoteException;
	
	//������Ϣ
	public boolean sendMessage(String fromID,String toID,String message)throws RemoteException;
	
	//ע����¼
	public boolean logout(String id)throws RemoteException;
	
	//��ȡ������Ϣ
	public void getOfflineMsg(String id)throws RemoteException;
	
	//����ID����û���Ϣ
	public User getUserById(String id)throws RemoteException;
}