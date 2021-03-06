package com.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多生产者-多消费者
 */
public class ProducerAndConsumer4Lock {

	public static void main(String[] args) {
		ProductsLock2 product = new ProductsLock2();

		ProducersLock2 producer1 = new ProducersLock2(product);
		ProducersLock2 producer2 = new ProducersLock2(product);

		ConsumersLock consumer1 = new ConsumersLock(product);
		ConsumersLock consumer2 = new ConsumersLock(product);

		new Thread(producer1).start();
		new Thread(producer2).start();

		new Thread(consumer1).start();
		new Thread(consumer2).start();

	}
}

class ProductsLock {
	// 产品名称
	private String name;
	// 产品编号
	private int count = 1;

	private boolean flag = false;

	// 显示锁
	private Lock lock = new ReentrantLock();
	// 获取锁上的Condition对象
	Condition con = lock.newCondition();

	public void set(String name) {

		// 加锁
		lock.lock();

		try {
			while (flag) {
				try {
					// this.wait();
					con.await();
				} catch (InterruptedException e) {
				}
			}

			this.name = name + count;
			count++;
			System.err.println(Thread.currentThread().getName() + " 生产了      " + this.name);

			flag = true;
			// this.notifyAll();
			con.signalAll();
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
					con.await();
				} catch (InterruptedException e) {
				}
			}
			System.err.println(Thread.currentThread().getName() + " 消费了" + this.name);
			flag = false;
			// this.notifyAll();
			con.signalAll();
		} finally {
			lock.unlock();
		}

	}
}

class ProducersLock implements Runnable {

	private ProductsLock2 product;

	public ProducersLock(ProductsLock2 product) {
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

class ConsumersLock implements Runnable {

	private ProductsLock2 product;

	public ConsumersLock(ProductsLock2 product) {
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
