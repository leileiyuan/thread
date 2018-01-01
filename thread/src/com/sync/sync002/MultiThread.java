package com.sync.sync002;

public class MultiThread {

	private static int num = 0;

	/** static
	 * synchronized 这里的锁是同一个锁（类.class）无论有多个对象 都会持有相同的锁，与定义的 MultiThread mt1 = new MultiThread(); 对象无关
	 *  */
	public static synchronized void printNum(String tag) {
		try {
			if ("a".equals(tag)) {
				num = 100;
				System.err.println("tag a, set num over!");
				Thread.sleep(1000L);
			} else {
				num = 200;
				System.err.println("tag b, set num over!");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.err.println("tag:" + tag + ", num:" + num);
	}

	public static void main(String[] args) {

		MultiThread mt1 = new MultiThread();
		MultiThread mt2 = new MultiThread();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mt1.printNum("a");
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				mt2.printNum("b");
			}
		});

		t1.start();
		t2.start();
	}
}
