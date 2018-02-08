package com.nisum.cache.manager;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nisum.cache.CacheManagerConfig;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.HostAndPort;

@Component
public class EtaCacheManager {

	@Autowired
	CacheManagerConfig cacheManagerConfig;

	private static JedisCluster jc = null;
	private Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>(0);

	@PostConstruct
	private void init() {
		jedisClusterNodes.add(new HostAndPort(cacheManagerConfig.getClusterMasterNodeIP(), cacheManagerConfig.getClusterMasterNodeAPort()));
		jedisClusterNodes.add(new HostAndPort(cacheManagerConfig.getClusterMasterNodeIP(), cacheManagerConfig.getClusterMasterNodeBPort()));
		jedisClusterNodes.add(new HostAndPort(cacheManagerConfig.getClusterMasterNodeIP(), cacheManagerConfig.getClusterMasterNodeCPort()));
		jc = new JedisCluster(jedisClusterNodes);
	}

	public String writeToCache(String key, String value) {
		return jc.set(key, value);
	}

	public String readFromCache(String key) {
		return jc.get(key);
	}

	public Long deleteFromCache(String key) {
		return jc.del(key);
	}
}
