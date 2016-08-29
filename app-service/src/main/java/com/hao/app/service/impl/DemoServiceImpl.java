//package com.hao.app.service.impl;
//
//import java.io.Serializable;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.hao.app.dao.DemoDao;
//import com.hao.app.pojo.Demo;
//import com.hao.app.service.DemoService;
//
//@Service
//public class DemoServiceImpl implements DemoService {
//
//	@Autowired
//	private DemoDao demoMapper;
//
//	@Autowired
//	private RedisTemplate<Serializable, Serializable> redisTemplate;
//
//	private Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);
//
//	// 访问redis
//	public Demo getDemoName(final String name) {
//		final Demo demo = demoMapper.selectByPrimaryKey(1);
//		logger.info(name + "<==demo==>" + demo);
//
//		return redisTemplate.execute(new RedisCallback<Demo>() {
//			@Override
//			public Demo doInRedis(RedisConnection conn) throws DataAccessException {
//				byte[] key = "key_01".getBytes();
//
//				conn.set(key, ("我是测试redis" + name).getBytes());
//				conn.expire(key, 60 * 3);
//
//				byte[] value = conn.get(key);
//				String v = redisTemplate.getStringSerializer().deserialize(value);
//				logger.info("redis get key_01 = " + v);
//
//				demo.setName(v);
//				return demo;
//			}
//		});
//	}
//
//	// 支持事物
//	@Transactional
//	public void testTransactional() {
//		Demo demo = new Demo();
//		demo.setName("郝yoyo124");
//		demoMapper.insert(demo);
//		System.out.println(1 / 0);
//	}
//
//	// 不支持事物
//	public void testNoTransactional() {
//		Demo demo = new Demo();
//		demo.setName("郝");
//		demoMapper.insert(demo);
//		System.out.println(1 / 0);
//	}
//
//}
