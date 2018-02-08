package com.nisum.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.common.util.BaseEntity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the store database table.
 *
 */
@Entity
@Table(name = "store")
@SQLDelete(sql="Update Store i SET i.isDeleted = 1 where i.id=?")
@Where(clause="is_deleted = 0" )
@JsonIgnoreProperties({"users"})
public class Store extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "created_at")
	private Long createdAt;

	@Column(name = "is_deleted")
	private Byte isDeleted;

	@Column
	private String name;

	@Column(name = "updated_at")
	private Long updatedAt;

	@Column(name = "zipcode")
	private Long zipcode;
	
	@Column(name = "address")
	private String address;

	// bi-directional many-to-one association to User
	@OneToMany(mappedBy = "store")
	private List<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Byte getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getZipcode() {
		return zipcode;
	}

	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setStore(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setStore(null);

		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Store other = (Store) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Store() {
		super();
	}

}