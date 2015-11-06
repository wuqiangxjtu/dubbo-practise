package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.Water;
import com.alibaba.dubbo.demo.WaterService;

public class WaterServiceImpl implements WaterService{

	public Water boil(Water water) {
		System.out.println("------->boil water");
		SleepUtil.sleep(1000);
		return water;
	}
}
