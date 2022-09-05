package com.nkd.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class UserDTO extends AbstractDTO {

	@NotBlank(message = "Name is mandatory")
	private String userName;

	private String fullName;

	private String password;

	private List<String> roles;

	// @NotBlank(message = "Email is mandatory")
	private String email;

	// @Min(value = 18, message = "Age can not be less than 18")
	// @Max(value = 50, message = "Age can not be greater than 50")
	// private Integer age;

	public UserDTO() {
	}

	public UserDTO(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Integer getAge() {
//		return age;
//	}
//
//	public void setAge(Integer age) {
//		this.age = age;
//	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}

}
