package com.boco.test;

import com.boco.concurrent.Job;


public class MyJob extends Job<Result> {
	int record = 0;
	
	public MyJob(int record){
		this.record = record;
	}

	public Result execute() {
        System.out.println("running thread id = "+Thread.currentThread().getId());  
        //ģ��ҵ����Ҫ��������.  
        try {Thread.sleep(100 * Thread.currentThread().getId());} catch (InterruptedException e) {}  
        Result r = new Result();
        r.setCode(record * Thread.currentThread().getId());
		return r;
	}

	

}