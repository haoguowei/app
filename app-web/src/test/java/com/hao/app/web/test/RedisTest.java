//package com.hao.app.web.test;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.hao.app.commons.entity.UserDemo;
//import com.hao.app.commons.utils.RedisUtil;
//
////表示继承了SpringJUnit4ClassRunner类
//@RunWith(SpringJUnit4ClassRunner.class)
////加载配置
//@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
//public class RedisTest {
//
//	private Logger logger = LoggerFactory.getLogger(FangTest.class);
//
//	@Autowired
//	private RedisTemplate<Serializable, Serializable> redisTemplate;
//	
//	private final RedisSerializer<String> strSerializer = new StringRedisSerializer();
//
//	@Test
//	public void test() {
//		
//		redisTemplate.execute(new RedisCallback<String>() {
//
//			@Override
//			public String doInRedis(RedisConnection conn) throws DataAccessException {
//				byte[] key = "key_01".getBytes();
//				conn.set(key, "你好啊".getBytes());
//				conn.expire(key, 60 * 3);
//
//				byte[] value = conn.get(key);
//				String v = redisTemplate.getStringSerializer().deserialize(value);
//				logger.info("REDIS GET, key_01 = " + v);
//				return v;
//			}
//		});
//	};
//	
//	@Test
//	public void testHMSet() {
//		
//		redisTemplate.execute(new RedisCallback<String>() {
//			
//			@Override
//			public String doInRedis(RedisConnection conn) throws DataAccessException {
//				byte[] key = strSerializer.serialize("userObject");
//				
//				UserDemo u1 = new UserDemo();
//				u1.setId(1L);
//				u1.setName("张三");
//				u1.setAge(34);
//				u1.setDate(new Date());
//				
//				UserDemo u2 = new UserDemo();
//				u2.setId(2L);
//				u2.setName("李四");
//				u2.setAge(23);
//				u2.setDate(new Date());
//				
//				RedisSerializer<UserDemo> serializer = new JacksonJsonRedisSerializer<UserDemo>(UserDemo.class);
//				Map<byte[], byte[]> hashes = new HashMap<byte[], byte[]>();
//				hashes.put(strSerializer.serialize("1"), serializer.serialize(u1));
//				hashes.put(strSerializer.serialize("2"), serializer.serialize(u2));
//				conn.hMSet(key, hashes);
//				conn.expire(key, 60);
//				
//				String[] fields = "1,2,3,4,5,6,7,8".split(",");
//				List<byte[]> ls = conn.hMGet(key, RedisUtil.serializeMulti(fields));
//				for(byte[] v : ls){
//					System.out.println("======>" + serializer.deserialize(v));
//				}
//				
//				return null;
//			}
//		});
//	};
//	
//	
//}