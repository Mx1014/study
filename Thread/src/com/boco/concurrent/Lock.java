package com.boco.concurrent;

import java.util.concurrent.Semaphore;

class Lock {
	/**
	 * �����Ƿ���Ҫ����
	 */
	boolean isJoin = false;

	/**
	 * ��������ִ�е��߳���
	 */
	int thread_count;
	
	/**
	 * �߳�ʹ�����֤
	 */
	Semaphore semaphore;
	
	Lock(int threadPoolSize){
		semaphore = new Semaphore(threadPoolSize);;
	}

}