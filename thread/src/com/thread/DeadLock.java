package com.thread;

public class DeadLock {
	public static void main(String[] args) {
		DeadLockThread target = new DeadLockThread();

		Thread t1 = new Thread(target);
		Thread t2 = new Thread(target);

		t1.start();
		t2.start();
	}
}

class DeadLockThread implements Runnable {
	MyLock lock = new MyLock();
	int count = 0;
	@Override
	public void run() {
		while (true) {
			System.err.println(count++);
			lock.method1();
			lock.method2();
		}
	}

}

class MyLock {

	private Object obj1 = new Object();
	private Object obj2 = new Object();

	public void method1() {
		synchronized (obj1) {
			try {Thread.sleep(1L);} catch (InterruptedException e) {}
			synchronized (obj2) {
				try {Thread.sleep(1L);} catch (InterruptedException e) {}
				System.err.println(Thread.currentThread().getName()+  " method1 ... lock");
			}
		}
	}

	public void method2() {
		synchronized (obj2) {
			synchronized (obj1) {
				System.err.println(Thread.currentThread().getName()+  " method2 ... lock");
			}
		}
	}
}
