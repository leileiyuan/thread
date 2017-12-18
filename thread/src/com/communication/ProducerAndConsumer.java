package com.communication;

public class ProducerAndConsumer {

	public static void main(String[] args) {
		Product product = new Product();

		Producer producer = new Producer(product);
		Consumer consumer = new Consumer(product);

		new Thread(producer).start();
		new Thread(consumer).start();

	}
}

class Product {
	// 产品名称
	private String name;
	// 产品编号
	private int count = 1;

	private boolean flag = false;

	public synchronized void set(String name) {

		if (flag) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		this.name = name + count;
		count++;
		System.err.println(Thread.currentThread().getName() + " 生产了" + this.name);

		flag = true;
		notify();
	}

	public synchronized void out() {
		if (!flag) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		System.err.println(Thread.currentThread().getName() + " 消费了" + this.name);
		flag = false;
		notify();
	}
}

class Producer implements Runnable {

	private Product product;

	public Producer(Product product) {
		this.product = product;
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(1L);
			} catch (Exception e) {
			}
			product.set("面包");
		}
	}

}

class Consumer implements Runnable {

	private Product product;

	public Consumer(Product product) {
		this.product = product;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1L);
			} catch (Exception e) {
			}
			product.out();
		}

	}

}
