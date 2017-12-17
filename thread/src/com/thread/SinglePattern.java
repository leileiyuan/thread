package com.thread;

public class SinglePattern {

	public static void main(String[] args) {

	}
}

class Single {
	private static Single instance = null;

	private Single() {
	}

	public static Single getInstance() {
		//双重判断，减少同步锁的判定次数，提高性能
		if (instance == null) {
			synchronized (Single.class) {
				if (instance == null) {
					instance = new Single();
				}
			}
		}

		return instance;
	}
}

class SingleThread implements Runnable {
	@Override
	public void run() {
		Single.getInstance();
	}
}
