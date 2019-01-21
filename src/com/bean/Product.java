package com.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="product")
public class Product {
	
	private int pid,quantity;
	private double price;
	private String product_name,product_image;
	
	private Category category_id;
	private Set<Cartitems> cartitems_id;
	
	public Product() {}

	@Id
	@GenericGenerator(name="increment", strategy="increment")
	@GeneratedValue(generator="increment")
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	
	@ManyToOne
	@JoinColumn(name="category_id")
	public Category getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Category category_id) {
		this.category_id = category_id;
	}



	@OneToMany(cascade=CascadeType.ALL,mappedBy="product_id")
	public Set<Cartitems> getCartitems_id() {
		return cartitems_id;
	}

	public void setCartitems_id(Set<Cartitems> cartitems_id) {
		this.cartitems_id = cartitems_id;
	}
	
	
	
	

}
