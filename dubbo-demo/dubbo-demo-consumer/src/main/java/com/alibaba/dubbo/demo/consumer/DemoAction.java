/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.demo.consumer;

import java.util.concurrent.CompletableFuture;

import com.alibaba.dubbo.demo.DemoService;
import com.alibaba.dubbo.demo.Person;
import com.alibaba.dubbo.demo.PersonService;
import com.alibaba.dubbo.demo.Rice;
import com.alibaba.dubbo.demo.RiceService;
import com.alibaba.dubbo.demo.Vegetable;
import com.alibaba.dubbo.demo.VegetableService;
import com.alibaba.dubbo.demo.Water;
import com.alibaba.dubbo.demo.WaterService;

public class DemoAction {

	private DemoService demoService;
	private VegetableService vegetableService;
	private RiceService riceService;
	private WaterService waterService;
	private PersonService personService;

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setVegetableService(VegetableService vegetableService) {
		this.vegetableService = vegetableService;
	}

	public void setRiceService(RiceService riceService) {
		this.riceService = riceService;
	}

	public void setWaterService(WaterService waterService) {
		this.waterService = waterService;
	}

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	public void start() throws Exception {
//		 int i = 0;
//		for (int i = 0; i < Integer.MAX_VALUE; i++) {
//			try {
//				String hello = demoService.sayHello("world" + i);
//				System.out.println("["
//						+ new SimpleDateFormat("HH:mm:ss").format(new Date())
//						+ "] " + hello);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			Thread.sleep(2000);
//		}
		
		Vegetable vegetable = new Vegetable();
		Rice rice = new Rice();
		Water water = new Water();
		Person person = new Person();
		
		System.out.println("------------->serial begin<---------");
		long begin = System.currentTimeMillis();
		vegetableService.wash(vegetable);
		vegetableService.cook(vegetable);
		riceService.wash(rice);
		riceService.cook(rice);
		waterService.boil(water);
		personService.eat(vegetable, rice, water);
		System.out.println("Cost " + (System.currentTimeMillis() - begin) + " millis");
		System.out.println("--------------->serial end<---------");
		
		System.out.println("------------->concurrent begin<---------");
		long begin2 = System.currentTimeMillis();
		final CompletableFuture<Vegetable> vegetableFuture = CompletableFuture.supplyAsync(
				() -> {vegetableService.wash(vegetable); return vegetable;})
				.thenApply((v)->{vegetableService.cook(v); return v;});
		final CompletableFuture<Rice> riceFuture = CompletableFuture.supplyAsync(
				() -> {riceService.wash(rice); return rice;})
				.thenApply((r)->{riceService.cook(r); return r;});
		final CompletableFuture<Water> waterFuture = CompletableFuture.supplyAsync(
				()->{waterService.boil(water);return water;});
		personService.eat(vegetableFuture.get(), riceFuture.get(), waterFuture.get());
		System.out.println("Cost " + (System.currentTimeMillis() - begin2) + " millis");
		System.out.println("--------------->concurrent end<---------");
	}

}