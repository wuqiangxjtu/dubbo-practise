package com.alibaba.dubbo.demo.provider;

public class SleepUtil {
	public static void sleep(int m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}