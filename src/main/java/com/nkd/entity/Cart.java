package com.nkd.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

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

	@OneToMany(mappedBy = "cart")
	private Set<CartProductColor> cartProductColor;

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

	public Set<CartProductColor> getCartProductColor() {
		return cartProductColor;
	}

	public void setCartProductColor(Set<CartProductColor> cartProductColor) {
		this.cartProductColor = cartProductColor;
	}

}
