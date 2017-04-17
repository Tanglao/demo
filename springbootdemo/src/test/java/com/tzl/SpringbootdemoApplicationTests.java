package com.tzl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

	@Autowired
	private StringRedisTemplate template;

	@Test
	public void connStringRedisTemplate() {
		Assert.isNull(template.opsForValue().get("key"));
	}

	@Test
	public void getStringRedisTemplateValue() {
		Assert.isTrue(StringUtils.hasLength(template.opsForValue().get("key")));
	}

}
