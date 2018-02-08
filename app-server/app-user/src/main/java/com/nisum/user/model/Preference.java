package com.nisum.user.model;

import java.io.Serializable;
import javax.persistence.*;

import com.nisum.common.util.BaseEntity;


/**
 * The persistent class for the preference database table.
 * 
 */
@Entity
@Table(name="preference")
@NamedQuery(name="Preference.findAll", query="SELECT p FROM Preference p")
public class Preference extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 7921032974920193829L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 100)
	private String name;

	public Preference() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preference other = (Preference) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}