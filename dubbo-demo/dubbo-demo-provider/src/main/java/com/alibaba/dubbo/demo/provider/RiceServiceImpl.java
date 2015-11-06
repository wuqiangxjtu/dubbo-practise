package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.Rice;
import com.alibaba.dubbo.demo.RiceService;

public class RiceServiceImpl implements RiceService{
	public Rice wash(Rice rice) {
		System.out.println("------->wash rice");
		SleepUtil.sleep(1000);
		return rice;
	}
	public Rice cook(Rice rice) {
		System.out.println("------->cook rice");
		SleepUtil.sleep(2000);
		return rice;
	}
}