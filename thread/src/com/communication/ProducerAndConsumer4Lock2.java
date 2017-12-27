package com.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多生产者-多消费者
 */
public class ProducerAndConsumer4Lock2 {

	public static void main(String[] args) {
		ProductsLock2 product = new ProductsLock2();

		ProducersLock producer1 = new ProducersLock(product);
		ProducersLock producer2 = new ProducersLock(product);

		ConsumersLock2 consumer1 = new ConsumersLock2(product);
		ConsumersLock2 consumer2 = new ConsumersLock2(product);

		new Thread(producer1).start();
		new Thread(producer2).start();

		new Thread(consumer1).start();
		new Thread(consumer2).start();

	}
}

class ProductsLock2 {
	// 产品名称
	private String name;
	// 产品编号
	private int count = 1;

	private boolean flag = false;

	// 显示锁
	private Lock lock = new ReentrantLock();
	// 获取锁上的Condition对象
	Condition produter = lock.newCondition();
	Condition consume = lock.newCondition();

	public void set(String name) {

		// 加锁
		lock.lock();

		try {
			while (flag) {
				try {
					// this.wait();
					produter.await();
				} catch (InterruptedException e) {
				}
			}

			this.name = name + count;
			count++;
			System.err.println(Thread.currentThread().getName() + " 生产了      " + this.name);

			flag = true;
			// this.notifyAll();
			// 执行消费者唤醒,唤醒一个消费者
			consume.signal();
		} finally {
			// 释放锁
			lock.unlock();
		}

	}

	public void out() {

		lock.lock();

		try {
			while (!flag) {
				try {
					// this.wait();
					consume.await();
				} catch (InterruptedException e) {
				}
			}
			System.err.println(Thread.currentThread().getName() + " 消费了" + this.name);
			flag = false;
			// this.notifyAll();
			produter.signal();
		} finally {
			lock.unlock();
		}

	}
}

class ProducersLock2 implements Runnable {

	private ProductsLock2 product;

	public ProducersLock2(ProductsLock2 product) {
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

class ConsumersLock2 implements Runnable {

	private ProductsLock2 product;

	public ConsumersLock2(ProductsLock2 product) {
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
