package com.geekshow.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.geekshow.pojo.Users;

/**
 * Redis测试
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RedisTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 添加键值对
	 */
	@Test
	public void test1(){
		this.redisTemplate.opsForValue().set("key", "test");
	}
	
	/**
	 * 获取redis中的数据
	 */
	@Test
	public void test2(){
		String str = (String)this.redisTemplate.opsForValue().get("key");
		System.out.println(str);
	}
	
	/**
	 * 添加Users
	 */
	@Test
	public void test3(){
		Users users = new Users();
		users.setAge(30);
		users.setId(1);
		users.setName("张三");
		//更换序列化器
		this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		this.redisTemplate.opsForValue().set("users", users);
	}
	
	/**
	 * 获取Users
	 * 
	 */
	@Test
	public void test4(){
		//更换序列化器
		this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		Users users = (Users)this.redisTemplate.opsForValue().get("users");
		System.out.println(users);
	}

}
