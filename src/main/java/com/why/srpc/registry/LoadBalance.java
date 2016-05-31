package com.why.srpc.registry;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalance {

	private int i = 0;

	private int cw = 0;

	private int[] weight;

	private AtomicInteger count;

	/**
	 * 构造方法
	 *
	 * @param count
	 *            总数
	 */
	public LoadBalance(int count) {
		this.count = new AtomicInteger(count);
	}

	public void addCount() {
		this.count.getAndIncrement();
	}

	public void reduceCount() {
		this.count.getAndDecrement();
	}

	/**
	 * 哈希index
	 *
	 * @param key
	 * @return
	 */
	public int hashIndex(String key) {
		long hash = 5381;
		for (int i = 0; i < key.length(); i++) {
			hash = ((hash << 5) + hash) + key.charAt(i);
			hash = hash & 0xFFFFFFFFl;
		}

		int index = (int) hash % this.count.get();
		index = Math.abs(index);

		return index;
	}

	/**
	 * 加权轮询index，轮询算法不考虑多线程
	 *
	 * @return
	 */
	public int roundIndexByWeight() {
		while (true) {
			this.i = (this.i + 1) % this.count.get();
			if (this.i == 0) {
				this.cw = this.cw - gcd();
				if (this.cw <= 0) {
					this.cw = max();
					if (this.cw == 0)
						return 0;
				}
			}
			if (this.weight[this.i] >= this.cw)
				return this.i;
		}
	}

	/**
	 * 轮询index，轮询算法不考虑多线程
	 *
	 * @return
	 */
	public int roundIndex() {
		int j = this.i;
		j = (j + 1) % this.count.get();

		this.i = j;
		return this.i;
	}

	/**
	 * 求最大公约数
	 *
	 * @return
	 */
	private int gcd() {
		BigInteger value = null;
		if (this.weight.length > 0) {
			value = BigInteger.valueOf(this.weight[i]);
		}
		for (int i = 0; i < this.weight.length - 1; i++) {
			BigInteger tmp = BigInteger.valueOf(this.weight[i]);
			tmp = tmp.gcd(BigInteger.valueOf(this.weight[i + 1]));

			if (value.compareTo(tmp) > 0) {
				value = tmp;
			}
		}
		if (null != value) {
			return value.intValue();
		}
		// TODO 为空返回多少
		return 0;
	}

	/**
	 * 最大值
	 *
	 * @return
	 */
	private int max() {
		int value = 0;
		if (this.weight.length > 0) {
			value = this.weight[0];
		}
		for (int i = 0; i < this.weight.length - 1; i++) {
			int tmp = this.weight[i];
			if (value < tmp) {
				value = tmp;
			}
		}
		return value;
	}

}
