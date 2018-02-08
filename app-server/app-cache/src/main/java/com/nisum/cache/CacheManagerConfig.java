package com.nisum.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * 
 * @author brazzaq
 *
 */
@Configuration
@PropertySources({ @PropertySource("classpath:/cache.properties"),
		@PropertySource("classpath:/application.properties") })
@ComponentScan(basePackages="com.nisum")
public class CacheManagerConfig {

	@Value("${cluster.master.node.ip}")
	private String clusterMasterNodeIp;
	
	@Value("${cluster.master.nodeA.port}")
	private int clusterMasterNodeAPort;
	
	@Value("${cluster.master.nodeB.port}")
	private int clusterMasterNodeBPort;
	
	@Value("${cluster.master.nodeC.port}")
	private int clusterMasterNodeCPort;
	

	public String getClusterMasterNodeIP() {
		return clusterMasterNodeIp;
	}

	public int getClusterMasterNodeAPort() {
		return clusterMasterNodeAPort;
	}

	public int getClusterMasterNodeBPort() {
		return clusterMasterNodeBPort;
	}

	public int getClusterMasterNodeCPort() {
		return clusterMasterNodeCPort;
	}
}
