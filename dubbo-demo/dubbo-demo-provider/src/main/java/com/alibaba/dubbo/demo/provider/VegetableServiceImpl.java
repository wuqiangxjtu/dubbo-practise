package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.Vegetable;
import com.alibaba.dubbo.demo.VegetableService;

public class VegetableServiceImpl implements VegetableService{
	
	public Vegetable wash(Vegetable vegetable) {
		System.out.println("------->wash vegetable");
		SleepUtil.sleep(1500);
		return vegetable;
	}
	
	public Vegetable cook(Vegetable vegetable) {
		System.out.println("------->cook vegetable");
		SleepUtil.sleep(1500);
		return vegetable;
	}
}
