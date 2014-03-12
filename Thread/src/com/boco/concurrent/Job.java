package com.boco.concurrent;

import java.util.concurrent.Callable;

/**
 * @author xueliang
 *
 * @param <T> �����ִ�н������
 */
public abstract class Job<E> implements Callable<E> {

	// ��
	private Lock lock = null;

	void setLock(Lock lock) {
		this.lock = lock;
	}

	public E call() throws Exception {
		E result = null;
		try {
			result = this.execute();// ִ�������������
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�ж��Ƿ���Ҫ���������
		if(lock.isJoin){
			synchronized (lock) {
				// ������ҵ�������������ݼ��߳�����ͬʱ�������߳�
				lock.thread_count--;
				lock.notifyAll();
			}
		}
		lock.semaphore.release();//�ͷ��߳�ʹ�����
		return result;
	}

	/**
	 * ҵ������
	 */
	public abstract E execute();

}