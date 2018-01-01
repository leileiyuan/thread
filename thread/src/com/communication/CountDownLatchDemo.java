package com.communication;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 */
public class CountDownLatchDemo {

	static CountDownLatch countDownLatch = new CountDownLatch(5);
	
	public static void main(String[] args) {

		ThreadDemo td = new ThreadDemo();

		long start = System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			new Thread(td).start();
		}
		
		// 等待其它所有线程的运算完成以后，再继续执行
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();

		System.err.println("time:" + (end - start));

	}

	public static class ThreadDemo implements Runnable {

		@Override
		public void run() {
			synchronized (this) {
				try {
					for (int i = 0; i < 1000; i++) {
						if (i % 2 == 0) {
							System.err.println(i);
						}
					}
				} finally {
					// 每次线程执行完，递减1
					countDownLatch.countDown();
				}
			}
		}

	}
}
