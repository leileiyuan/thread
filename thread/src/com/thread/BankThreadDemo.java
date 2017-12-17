package com.thread;

public class BankThreadDemo {
	public static void main(String[] args) {
		Consumer consumer = new Consumer();
		Thread t1 = new Thread(consumer);
		Thread t2 = new Thread(consumer);

		t1.start();
		t2.start();
	}
}

class Bank {
	
	private int sum;

	// 多线程并发 操作同一对象的共享变量，会有线程安全问题
	public synchronized void add(int num) {
		sum += num;
		System.err.println("sum=" + sum);
	}
}

class Consumer implements Runnable {

	// 此为线程共享变量，该变量中的共性数据是线程不安全的
	private Bank bank = new Bank();

	@Override
	public void run() {

		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bank.add(100);
		}
	}

}
