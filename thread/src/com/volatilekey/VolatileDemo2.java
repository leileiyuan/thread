package com.volatilekey;

public class VolatileDemo2 {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();

		while (true) {
			if (td.isFlag()) {
				System.err.println("main-----------------");
				break;
			}
		}
	}

	public static class ThreadDemo implements Runnable {

		private volatile boolean flag = false;

		@Override
		public void run() {

			try {
				Thread.sleep(200L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flag = true;
			System.err.println("flag:" + flag);
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

	}
}
