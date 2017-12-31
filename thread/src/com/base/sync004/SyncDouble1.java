package com.base.sync004;

/**
 * 重入锁 
 */
public class SyncDouble1 {

	public synchronized void methoh1() {
		System.err.println(" method1");
		methoh2();
	}

	public synchronized void methoh2() {
		System.err.println(" method2");
		methoh3();
	}

	public synchronized void methoh3() {
		System.err.println(" method3");
	}

	public static void main(String[] args) {

		SyncDouble1 sd = new SyncDouble1();

		new Thread(new Runnable() {
			@Override
			public void run() {
				sd.methoh1();
			}
		}).start();
	}
}
