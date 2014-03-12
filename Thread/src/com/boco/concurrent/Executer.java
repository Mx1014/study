package com.boco.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ���м�����
 * @author xueliang
 * ���ӣ�
 * ����10���߳�,true��ʾ��Ҫ���ܽ��
 * Executer e = new Executer(10, true);
 * for(...){
 * 	  Job<Result> job = new MyJob();
 *    //Job<List<Result>> job = new MyJob();
 * 	  e.fork(job);//�������ɷ���executer
 * }
 * ��������ܺ󷵻�
 * List<Result> obj = e.join();
 * e.shutdown();
 */
public class Executer {
	// �洢�����ִ�н��
	private List<Future> futres = new ArrayList<Future>();
	// ����������,�Լ��̼߳�����
	private Lock lock = null;
	// �̳߳�
	private ExecutorService pool = null;
	
	/**
	 * ��������ִ����
	 * @param threadPoolSize ִ��������߳���
	 * @param isJoin �Ƿ���Ҫ������������
	 */
	public Executer(int threadPoolSize, boolean isJoin) {
		pool = Executors.newFixedThreadPool(threadPoolSize);
		lock = new Lock(threadPoolSize);
		lock.isJoin = isJoin;
	}

	/**
	 * �����ɷ�
	 * @param <E> ���񷵻�ֵ����
	 * @param job
	 */
	public <E> void fork(Job<E> job) {
		// ����ͬ����
		job.setLock(lock);
		// ��ȡ��ɣ����û�п����߳���ɣ���һֱ�ȴ�
		lock.semaphore.acquireUninterruptibly();
		// �������ɷ����̳߳�ȥִ��
		Future future = pool.submit(job);
		// �ж��Ƿ���Ҫ���ܽ��
		if(lock.isJoin){
			futres.add(future);
			// �����߳���
			synchronized (lock) {
				lock.thread_count++;
			}
		}
	}

	/**
	 * �㼯������
	 * @param <E>
	 * @return E���͵ļ���
	 */
	public <E> List<E> join() {
		synchronized (lock) {
			while (lock.thread_count > 0) {// ����߳��������Ϊ0�����ʾ�������������
				// System.out.println("threadCount: "+THREAD_COUNT);
				try {
					// �������û��ȫ����ɣ�����𡣵ȴ���ɵ��������֪ͨ
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		List list = new ArrayList();
		// ȡ��ÿ������Ĵ����������ܺ󷵻�
		for (Future future : futres) {
			try {
				// ��Ϊ�����Ѿ���ɣ�����ֱ��get
				Object result = future.get();
				if (result != null) {
					if (result instanceof Collection)
						list.addAll((Collection) result);
					else
						list.add(result);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * �ر���������
	 */
	public void shutdown(){
		if(pool != null)
			pool.shutdown();
	}
}