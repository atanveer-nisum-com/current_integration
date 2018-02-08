package com.nisum.user.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.common.util.BaseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@JsonIgnoreProperties({"wishlists", "userPaymentCards", "hibernateLazyInitializer", "handler"})
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1209429039239819781L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name="auth_token")
	private String authToken;

	@Column(name="created_at")
	private Long createdAt;

	@Column(name="email_address", length = 50)
	private String emailAddress;

	@Column(name="first_name", length = 50)
	private String firstName;

	@Column(name="is_deleted")
	private Byte isDeleted;

	@Column(name="is_guest")
	private Byte isGuest;

	@Column(name="last_name", length = 50)
	private String lastName;

	@JsonIgnore
	@Column(nullable = false, length = 44)
	private String password;

	@Column(name="reset_token")
	private String resetToken;

	// bi-directional many-to-one association to Module
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@Column(name="token_created_at")
	private Long tokenCreatedAt;

	@Column(name="token_validity")
	private Long tokenValidity;

	@Column(name="updated_at")
	private Long updatedAt;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private Set<Address> addresses;

	/** The user preferences. */
	//bi-directional many-to-one association to User Preference
	@OneToMany(mappedBy="user")
	private Set<UserPreference> userPreferences;
	
	//bi-directional many-to-one association to UserPaymentCard
	@OneToMany(mappedBy="user")
	private List<UserPaymentCard> userPaymentCards;

	//bi-directional many-to-one association to Wishlist
	@OneToMany(mappedBy="user")
	private Set<Wishlist> wishlists;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthToken() {
		return this.authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Long getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Byte getIsGuest() {
		return this.isGuest;
	}

	public void setIsGuest(Byte isGuest) {
		this.isGuest = isGuest;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResetToken() {
		return this.resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Long getTokenCreatedAt() {
		return this.tokenCreatedAt;
	}

	public void setTokenCreatedAt(Long tokenCreatedAt) {
		this.tokenCreatedAt = tokenCreatedAt;
	}

	public Long getTokenValidity() {
		return this.tokenValidity;
	}

	public void setTokenValidity(Long tokenValidity) {
		this.tokenValidity = tokenValidity;
	}

	public Long getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<Address> getAddresses() {
        if (this.addresses == null) 
            this.addresses = new HashSet<>();
            return this.addresses;

	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setUser(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setUser(null);

		return address;
	}

	public List<UserPaymentCard> getUserPaymentCards() {
		return this.userPaymentCards;
	}

	public void setUserPaymentCards(List<UserPaymentCard> userPaymentCards) {
		this.userPaymentCards = userPaymentCards;
	}

	public UserPaymentCard addUserPaymentCard(UserPaymentCard userPaymentCard) {
		getUserPaymentCards().add(userPaymentCard);
		userPaymentCard.setUser(this);

		return userPaymentCard;
	}

	public UserPaymentCard removeUserPaymentCard(UserPaymentCard userPaymentCard) {
		getUserPaymentCards().remove(userPaymentCard);
		userPaymentCard.setUser(null);

		return userPaymentCard;
	}

	public Set<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public void setWishlists(Set<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Wishlist addWishlist(Wishlist wishlist) {
		getWishlists().add(wishlist);
		wishlist.setUser(this);

		return wishlist;
	}

	public Wishlist removeWishlist(Wishlist wishlist) {
		getWishlists().remove(wishlist);
		wishlist.setUser(null);

		return wishlist;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(authToken, user.authToken) &&
				Objects.equals(createdAt, user.createdAt) &&
				Objects.equals(emailAddress, user.emailAddress) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(isDeleted, user.isDeleted) &&
				Objects.equals(isGuest, user.isGuest) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(password, user.password) &&
				Objects.equals(resetToken, user.resetToken) &&
				Objects.equals(store, user.store) &&
				Objects.equals(tokenCreatedAt, user.tokenCreatedAt) &&
				Objects.equals(tokenValidity, user.tokenValidity) &&
				Objects.equals(updatedAt, user.updatedAt) &&
				Objects.equals(addresses, user.addresses) &&
				Objects.equals(userPaymentCards, user.userPaymentCards) &&
				Objects.equals(wishlists, user.wishlists);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
	}
	
	public Set<UserPreference> getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(Set<UserPreference> userPreferences) {
		this.userPreferences = userPreferences;
	}

	/**
	 * Assign user to addresses.
	 */
	public void assignUserToAddresses() {
		this.addresses.forEach(x->x.setUser(this));
	}
}