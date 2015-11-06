package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.PersonService;
import com.alibaba.dubbo.demo.Rice;
import com.alibaba.dubbo.demo.Vegetable;
import com.alibaba.dubbo.demo.Water;

public class PersonServiceImpl implements PersonService{

	public void eat(Vegetable vegetable, Rice rice, Water water) {
		System.out.println("------->person eat");
		SleepUtil.sleep(500);
	}

}
