package com.nkd.dto;

import java.util.HashSet;
import java.util.Set;

public class CartDTO extends AbstractDTO<CategoryDTO> {

	private Set<OrderDTO> listOrder;
	private String userName;
	private Long total;
	private int count;
	private String name;
	private String phone;
	private String address;
	private String note;

	public CartDTO() {
		this.listOrder = new HashSet<>();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Set<OrderDTO> getListOrder() {
		return listOrder;
	}

	public void setListOrder(Set<OrderDTO> listOrder) {
		this.listOrder = listOrder;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

}
