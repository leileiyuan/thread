package com.communication;

/**
 * countDownLatch 实时
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ListAdd3 {

	private static volatile List<String> list = new ArrayList<>();

	public static void main(String[] args) {

		CountDownLatch countDownLatch = new CountDownLatch(1);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						list.add("aa" + (i + 1));
						System.err.println(Thread.currentThread().getName() + "," + list.size());
						Thread.sleep(500L);

						if (list.size() == 5) {
							System.err.println("已发出通知");
							countDownLatch.countDown();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						if (list.size() != 5) {
							countDownLatch.await();
						}
						Thread.sleep(1L);
						throw new RuntimeException("list size ->" + list.size());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");

		t2.start();
		t1.start();
	}
}
