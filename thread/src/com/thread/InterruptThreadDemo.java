package com.thread;

/**
 * 线程的停止
 */
public class InterruptThreadDemo {
	public static void main(String[] args) {
		Demo2 target = new Demo2();
		Thread t1 = new Thread(target);
		Thread t2 = new Thread(target);

		t1.start();
		t2.start();

		int x = 0;
		while (true) {
			if (++x == 50) {

				t1.interrupt();
				t2.interrupt();

				break;
			}

			System.err.println("main----------->");
		}

		System.err.println("over");

	}
}

class Demo2 implements Runnable {

	private boolean flag = true;

	@Override
	public synchronized void run() {
		while (flag) {

			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("InterruptedException---" + e.toString() + "----->");
				changeFlag();
			}
			System.err.println(Thread.currentThread().getName() + "----->");

		}
	}

	public void changeFlag() {
		flag = false;
	}

}
