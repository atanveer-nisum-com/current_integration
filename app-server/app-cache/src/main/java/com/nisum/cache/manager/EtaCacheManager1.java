//package com.nisum.cache.manager;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.google.gson.Gson;
//import com.nisum.event.model.Event;
//
//@Component
//public class EtaCacheManager1 {
//	
//	@Autowired
//	Gson gson;
//	
////	@Autowired
////	CacheManagerConfig cacheManagerConfig;
////	
////	private static JedisCluster jc = null;
////	private Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>(0);
////
////	@PostConstruct
////	private void init() {
////		jedisClusterNodes.add(new HostAndPort(cacheManagerConfig.getClusterMasterNodeIP(), cacheManagerConfig.getClusterMasterNodeAPort()));
////		jedisClusterNodes.add(new HostAndPort(cacheManagerConfig.getClusterMasterNodeIP(), cacheManagerConfig.getClusterMasterNodeBPort()));
////		jedisClusterNodes.add(new HostAndPort(cacheManagerConfig.getClusterMasterNodeIP(), cacheManagerConfig.getClusterMasterNodeCPort()));
////	
////		jc = new JedisCluster(jedisClusterNodes);
////	}
//	
//	public String readCache(String key, String value) {
//		Event event = gson.fromJson(value, Event.class);
//		String eventType = event.getEventType();
//		 return readFromCache(eventType,key);
//	}
//	
//	public String writeCache(String key, String value) {
//		Event event = gson.fromJson(value, Event.class);
//		String eventType = event.getEventType();
//		 return writeFromCache(eventType,key,value);
//	}
//	
//	public String deleteCache(String key, String value) {
//		Event event = gson.fromJson(value, Event.class);
//		String eventType = event.getEventType();
//		return deleteFromCache(eventType,key);
//	}
//	
//	@ResponseBody
//	@CachePut(value = "#eventType", key= "#key")
//	private String writeFromCache(String eventType, String key, String value) {
//		return null;
//	}
//	@Cacheable(value = "#eventType", key = "#key")
//	private String readFromCache(String eventType,String key) {
//		return null;
//	}
//	@ResponseBody
//	@CacheEvict(value="#eventType", key = "#key")
//	private String deleteFromCache(String eventType, String key) {
//		return null;
//	}
//	
//	
//}
