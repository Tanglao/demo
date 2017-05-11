package com.tzl;

import com.example.RedisLockService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

	final static String businessKey = "demokey";

	@Autowired
	private RedisLockService redisLockService;

//	@Test
//	public void getLock() {
//
//		Assert.assertTrue(redisLockService.getLock(businessKey));
//	}
//
//	@Test
//	public void releaseLock() {
//		Assert.assertTrue(redisLockService.releaseLock(businessKey));
//	}
	int sum = 0;
	@Test
	public void testLock(){
		int threadNum=3;
		final int n=10;
		final CountDownLatch latch = new CountDownLatch(threadNum);
		for(int i = 0;i<threadNum;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {

					for (int j = 0; j < n; j++) {
						while (!redisLockService.getLock(businessKey)) {
							try {

								Thread.sleep(100L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println(Thread.currentThread().getName()+ "loop for " + j+" add sum to:" +sum);
						sum += 1;
						redisLockService.releaseLock(businessKey);
						try {
							Thread.sleep(100L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					latch.countDown();
				}
			},"MyThread"+i).start();
		}

		try {
			latch.await();
			System.out.println("add result:" + sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertTrue(sum ==threadNum*n);
	}

}
