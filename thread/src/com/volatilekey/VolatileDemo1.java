package com.volatilekey;

/**
 * 运行结果是 flag:true ，main中的 while循环 未结束，td.isFlag()的返回值为false 线程中的共享变量flag
 * 没有被td对象获取到更新后的值
 */
public class VolatileDemo1 {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();

		System.err.println(td.isFlag());

		new Thread(td).start();

		while (true) {
			synchronized (td) {
				if (td.isFlag()) {
					System.err.println("-----------main---------");
					break;
				}
			}
		}

	}

	public static class ThreadDemo implements Runnable {

		private boolean flag = false;

		@Override
		public void run() {
			try {
				Thread.sleep(200L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			flag = true;
			System.err.println("flag=" + flag);
		}

		public boolean isFlag() {
			return flag;
		}

	}

}
