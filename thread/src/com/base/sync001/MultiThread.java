package com.base.sync001;

/**
 * 多个线程去操作共享数据，会引发线程安全问题， 对run方法作同步即可解决，
 * 那么，如果外部的线程非常多（假如2000000个），那么只一个线程进入代码执行，其它2000000-1个线程来竞争这把锁，竞争会非常激烈...性能极差
 */
public class MultiThread {

	public static void main(String[] args) {
		Demo mt = new Demo();
		Thread t1 = new Thread(mt, "t1");
		Thread t2 = new Thread(mt, "t2");
		Thread t3 = new Thread(mt, "t3");
		Thread t4 = new Thread(mt, "t4");

		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}
}

class Demo implements Runnable {

	private int count = 0;

	@Override
	public synchronized void run() {
		System.err.println(Thread.currentThread().getName() + ",count=" + ++count);
	}

}