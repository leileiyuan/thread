package com.atomic;

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
 */
public class AtomicDemo {
	
	public static void main(String[] args) {
		AtomicThread at = new AtomicThread();
		for (int i = 0; i < 10; i++) {
			new Thread(at).start();
		}
	}

	public static class AtomicThread implements Runnable {

		private int serialNumber = 0;

		@Override
		public void run() {
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println(Thread.currentThread().getName() + ":" + serialNumber++);
		}

	}
}
