package com.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNoAtomic extends Thread {

	//private volatile static int count = 0;
	
	private static AtomicInteger count = new AtomicInteger();

	public static void addCount() {
		for (int i = 0; i < 200; i++) {
			count.getAndIncrement();
			//count++;
		}
		
		//期望结果 10 * 200 = 2000   实际结果  是200 ？？？
		System.err.println(count);
	}

	@Override
	public void run() {
		addCount();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			VolatileNoAtomic volatileNoAtomic = new VolatileNoAtomic();
			volatileNoAtomic.start();
		}
	}
}
