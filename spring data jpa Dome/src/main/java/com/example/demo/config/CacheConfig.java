package com.example.demo.config;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {
//        EhCaChe
//        @Bean
//        public EhCacheCacheManager cacheCacheManager(net.sf.ehcache.CacheManager ehCacheManager){
//            return  new EhCacheCacheManager(ehCacheManager);
//        }
//        @Bean
//        public EhCacheManagerFactoryBean ehCacheCacheManagerFactoryBean(){
//            //注入ehCacheMangerFactorybean
//            EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//            ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehCache"));//设置eh配置文件的地址
//            return ehCacheManagerFactoryBean;
//        }
//    这是对concurrentMapCacheManger进行注入bean
//    @Bean
//    public CacheManager cacheManager(){
//        return  new ConcurrentMapCacheManager();
//    }
    //redis
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate){
//        return  new RedisCacheManager(redisTemplate);
//    }
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory(){
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.afterPropertiesSet();
//        return  jedisConnectionFactory;
//    }
//    @Bean
//    public  RedisTemplate<String ,String >redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate <String ,String > redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }

    //CompositeCacheManager
//    @Bean
//    public  CacheManager cacheManager(net.sf.ehcache.CacheManager cacheManager){
//            //循环添加到CompositeCacheManager
//        CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
//        List<CacheManager> cacheManagerList = new ArrayList<CacheManager>();
//        cacheManagerList.add(new EhCacheCacheManager(cacheManager));
//        //cacheManager.addCache(new RedisCacheManager(redisTemplate()));
//        compositeCacheManager.setCacheManagers(cacheManagerList);
//        return  compositeCacheManager;
//    }
}
