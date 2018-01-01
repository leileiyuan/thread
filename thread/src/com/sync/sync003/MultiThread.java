package com.sync.sync003;

/**
 * 同步和异步
 */
public class MultiThread {

	public synchronized void method1() {
		try {
			System.err.println(Thread.currentThread().getName());
			Thread.sleep(4000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void method2() {
		System.err.println(Thread.currentThread().getName());
	}

	public static void main(String[] args) {

		MultiThread mt = new MultiThread();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mt.method1();
			}

		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				mt.method2();
			}
		}, "t2");

		t1.start();
		t2.start();
	}

}
