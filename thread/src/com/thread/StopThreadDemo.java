package com.thread;

/**
 * 线程的停止
 */
public class StopThreadDemo {
	public static void main(String[] args) {
		Demo target = new Demo();
		Thread t1 = new Thread(target);
		Thread t2 = new Thread(target);

		t1.start();
		t2.start();

		int x = 0;
		while (true) {
			if (++x == 50) {
				
				// 改变线程 标记，使其它线程 也结束
				target.changeFlag();
				
				break;
			}

			System.err.println("main----------->");
		}

		System.err.println("over");

	}
}

class Demo implements Runnable {

	private boolean flag = true;

	@Override
	public void run() {
		while (flag) {
			System.err.println(Thread.currentThread().getName() + "----->");
		}
	}

	public void changeFlag() {
		flag = false;
	}

}
