package com.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * 让 t2线程先执行，进行awit等待，t1线程再执行，进行唤醒；t1执行完毕后，释放锁, t2再会继续执行，不实时，
 */
public class ListAdd2 {

	private static volatile List<String> list = new ArrayList<>();

	public static void main(String[] args) {
		Object lock = new Object();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					try {
						for (int i = 0; i < 10; i++) {
							list.add("aa" + (i + 1));
							System.err.println(Thread.currentThread().getName() + "," + list.size());
							Thread.sleep(500L);

							if (list.size() == 5) {
								System.err.println("已发出通知");
								lock.notify();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					try {
						while (true) {
							if (list.size() != 5) {
								lock.wait();
							}
							Thread.sleep(1L);
							throw new RuntimeException("list size ->" + list.size());
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t2");

		t2.start();
		t1.start();
	}
}
