package com.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cart")
public class Cart {
	
	private int cartid;
	private User user_id;
	
	private Set<Cartitems> cartitem_id;
	
	public Cart() {}

	@Id
	@GenericGenerator(name="increment", strategy="increment")
	@GeneratedValue(generator="increment")
	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	
	@OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="cart_id")
	public Set<Cartitems> getCartitem_id() {
		return cartitem_id;
	}

	public void setCartitem_id(Set<Cartitems> cartitem_id) {
		this.cartitem_id = cartitem_id;
	}
	
	
	
	
	
	

}
