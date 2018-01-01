package com.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性问题
 * 
 * i++问题
 *  int i = 10; 
 *  i=i++ // 10
 * 
 * int temp = i; 	// 读 
 * i = i + 1; 		// 改 
 * 					// 写回到主存
 * 
 * Atomic：1.volatile 修饰，2.CAS保证原子性（compaer-and-wsap）
 * 		cas 包含了3个操作：
 * 			内存值 V
 * 			预估值 A
 * 			更新值 B
 * 			当且仅当 V=A时，V=B,否则 不做任何操作
 */
public class AtomicDemo2 {
	
	public static void main(String[] args) {
		AtomicThread at = new AtomicThread();
		for (int i = 0; i < 100; i++) {
			new Thread(at).start();
		}
	}

	public static class AtomicThread implements Runnable {

		private AtomicInteger serialNumber = new AtomicInteger();

		@Override
		public void run() {
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println(serialNumber.getAndIncrement());
		}

	}
}
