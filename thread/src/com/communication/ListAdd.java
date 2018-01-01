package com.communication;

import java.util.ArrayList;
import java.util.List;

public class ListAdd {

	private static volatile List<String> list = new ArrayList<>();

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						list.add("aa" + (i + 1));
						System.err.println(Thread.currentThread().getName() + "," + list.size());
						Thread.sleep(1L);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (list.size() == 5) {
						throw new RuntimeException(" list.size() " + list.size());
					}
				}
			}
		},"t2");

		t1.start();
		t2.start();
	}
}
