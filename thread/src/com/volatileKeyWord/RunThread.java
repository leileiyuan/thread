package com.volatileKeyWord;

public class RunThread extends Thread {

	//private boolean isRunning = true;
	private volatile boolean isRunning = true;

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public void run() {
		System.err.println("进入run方法");
		while (isRunning()) {
			// ...
		}
		System.err.println("run方法结束..");
	}

	public static void main(String[] args) throws Exception {
		RunThread rt = new RunThread();
		rt.start();
		// 主线程等待
		Thread.sleep(1000L);
		
		rt.setRunning(false);
		System.err.println(rt.isRunning() + " main 结束...");
	}
}
