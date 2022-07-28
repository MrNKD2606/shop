package com.nkd.dto;

import java.util.HashSet;
import java.util.Set;

public class CartDTO extends AbstractDTO<CategoryDTO> {

	private Set<OrderDTO> listOrder;
	private String name;
	private String maCart;
	private String phone;
	private String address;
	private String note;
	private int status;

	public CartDTO() {
		this.listOrder = new HashSet<>();
	}

	public Long getTotal() {
		long total = 0;
		for(OrderDTO item : listOrder) {
			total = total + item.getAmount()*item.getPrice();
		}
		return total;
	}
	
	public int getCount() {
		int count = 0;
		for(OrderDTO item : listOrder) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMaCart() {
		return maCart;
	}

	public void setMaCart(String maCart) {
		this.maCart = maCart;
	}

}
