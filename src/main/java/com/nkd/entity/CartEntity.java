package com.nkd.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntity {

	@Column(name = "macart")
	private String maCart;

	@Column(name = "name_cus")
	private String nameCustomer;

	@Column(name = "phone_cus")
	private String phoneCustomer;

	@Column(name = "address_cus")
	private String addressCustomer;

	@Column(name = "note")
	private String note;

	@Column(name = "status")
	private int status;

	@OneToMany(mappedBy = "cart")
	private Set<CartProductColorEntity> cartProductColor;

	public String getMaCart() {
		return maCart;
	}

	public void setMaCart(String maCart) {
		this.maCart = maCart;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getPhoneCustomer() {
		return phoneCustomer;
	}

	public void setPhoneCustomer(String phoneCustomer) {
		this.phoneCustomer = phoneCustomer;
	}

	public String getAddressCustomer() {
		return addressCustomer;
	}

	public void setAddressCustomer(String addressCustomer) {
		this.addressCustomer = addressCustomer;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<CartProductColorEntity> getCartProductColor() {
		return cartProductColor;
	}

	public void setCartProductColor(Set<CartProductColorEntity> cartProductColor) {
		this.cartProductColor = cartProductColor;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
