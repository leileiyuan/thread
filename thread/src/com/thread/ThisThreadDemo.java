package com.thread;

/**
 * 测试 同步代码块 与同步锁 持有的对象锁 是不是this. 
 */
public class ThisThreadDemo {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		Thread t1 = new Thread(ticket);
		Thread t2 = new Thread(ticket);

		t1.start();

		// 主线程执行太快，执行可能全是flag=true，需要让主线程往下往下执行的慢一点，让t1执行下，再设置成 false，再执行t2
		try {
			Thread.sleep(1L);
		} catch (InterruptedException e) {
		}
		ticket.flag = false;
		t2.start();
	}
}

class Ticket implements Runnable {

	private int tickets = 100;
	//private Object obj = new Object();
	boolean flag = true;

	@Override
	public void run() {

		if (flag) {
			while (true) {
				//synchronized (obj) { // 该同步代码块与 同步方法sell 持有不是同一对象锁，共享变量可能出现错误 数据
				 // 持有同一对象锁，共享变量数据正确 ，
				/** 同步方法持有的对象锁是this*/
				synchronized (this) {
					if (tickets > 0) {
						try {
							Thread.sleep(1L);
						} catch (InterruptedException e) {
						}
						System.err.println(Thread.currentThread().getName() + " obj -- ticlets=" + tickets--);
					}
				}
			}
		} else {
			while (true) {
				this.sell();
			}
		}

	}

	public synchronized void sell() {
		if (tickets > 0) {
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
			}
			System.err.println(Thread.currentThread().getName() + " sell -- ticlets=" + tickets--);
		}
	}
}
