package com.communication;

/**
 * 多生产者-多消费者
 */
public class ProducerAndConsumers {

	public static void main(String[] args) {
		Products product = new Products();

		Producers producer1 = new Producers(product);
		Producers producer2 = new Producers(product);

		Consumers consumer1 = new Consumers(product);
		Consumers consumer2 = new Consumers(product);

		new Thread(producer1).start();
		new Thread(producer2).start();

		new Thread(consumer1).start();
		new Thread(consumer2).start();

	}
}

class Products {
	// 产品名称
	private String name;
	// 产品编号
	private int count = 1;

	private boolean flag = false;

	public synchronized void set(String name) {

		while (flag) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		this.name = name + count;
		count++;
		System.err.println(Thread.currentThread().getName() + " 生产了      " + this.name);

		flag = true;
		notifyAll();
	}

	public synchronized void out() {
		while (!flag) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		System.err.println(Thread.currentThread().getName() + " 消费了" + this.name);
		flag = false;
		notifyAll();
	}
}

class Producers implements Runnable {

	private Products product;

	public Producers(Products product) {
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

class Consumers implements Runnable {

	private Products product;

	public Consumers(Products product) {
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
