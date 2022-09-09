package com.nkd.dto;

import java.util.HashSet;
import java.util.Set;

public class CartDTO extends AbstractDTO {

	private Set<OrderDTO> listOrder;
	private String nameCustomer;
	private String maCart;
	private String phoneCustomer;
	private String addressCustomer;
	private String note;

	public CartDTO() {
		this.listOrder = new HashSet<>();
	}

	public Long getTotal() {
		long total = 0;
		for (OrderDTO item : listOrder) {
			total = total + item.getAmount() * item.getPrice();
		}
		return total;
	}

	public int getCount() {
		int count = 0;
		for (OrderDTO item : listOrder) {
			count = count + item.getAmount();
		}
		return count;
	}

	public Set<OrderDTO> getListOrder() {
		return listOrder;
	}

	public void setListOrder(Set<OrderDTO> listOrder) {
		this.listOrder = listOrder;
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

	public String getMaCart() {
		return maCart;
	}

	public void setMaCart(String maCart) {
		this.maCart = maCart;
	}

}
