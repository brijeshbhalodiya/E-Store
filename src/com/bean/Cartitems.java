package com.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cartitems")
public class Cartitems {

	private int cartitemid,quantity;
	
	private Cart cart_id;
	private Product product_id;
	
	public Cartitems() {}
	
	
	@Id
	@GenericGenerator(name="increment", strategy="increment")
	@GeneratedValue(generator="increment")
	public int getCartitemid() {
		return cartitemid;
	}

	public void setCartitemid(int cartitemid) {
		this.cartitemid = cartitemid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@ManyToOne
	@JoinColumn(name="cart_id")
	public Cart getCart_id() {
		return cart_id;
	}


	public void setCart_id(Cart cart_id) {
		this.cart_id = cart_id;
	}

	@ManyToOne
	@JoinColumn(name="product_id")
	public Product getProduct_id() {
		return product_id;
	}


	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}
	
}
