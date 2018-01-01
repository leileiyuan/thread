package com.sync.sync004;

/**
 * 重入锁
 */
public class SyncDouble2 {

	public static void main(String[] args) {
		Sub sub = new SyncDouble2().new Sub();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				sub.subMethod();
			}
		});
		
		t.start();
	}

	public class Main {

		public int i = 20;

		public synchronized void mainMethod() {
			try {
				i--;
				System.err.println("main method i = " + i);
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class Sub extends Main {
		public synchronized void subMethod() {
			try {
				while (i > 0) {
					i--;
					System.err.println("sub method i = " + i);
					Thread.sleep(1L);
					// 调用 父类的方法，操作同一共享变量
					mainMethod();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
