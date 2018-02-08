package com.nisum.fcc.cassandra.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Set;


@Table(name="app_list")
public class Category extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6433430373222769092L;

	@PartitionKey
	private String key;
	
	private String type;
	
	private Set<String> value;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<String> getValue() {
		return value;
	}

	public void setValue(Set<String> value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getKey()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Category other = (Category) obj;
		return new EqualsBuilder().append(getKey(), other.getKey()).isEquals();
	}

	
	
}
