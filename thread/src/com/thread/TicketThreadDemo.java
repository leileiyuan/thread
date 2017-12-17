package com.thread;

public class TicketThreadDemo {

	public static void main(String[] args) {
		ThreadDemo demo = new ThreadDemo();

		Thread t1 = new Thread(demo);
		Thread t2 = new Thread(demo);
		Thread t3 = new Thread(demo);
		Thread t4 = new Thread(demo);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}
}

class ThreadDemo implements Runnable {

	private int tickets = 100;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			synchronized(this.getClass()) {
				if (tickets > 0) {
					System.err.println(Thread.currentThread().getName() + " " + tickets--);
				} else {
					return;
				}
			}

		}
	}

}
