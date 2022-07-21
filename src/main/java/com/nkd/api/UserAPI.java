package com.nkd.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nkd.dto.UserDTO;
import com.nkd.service.IUserService;

@RestController
public class UserAPI {

	@Autowired
	private IUserService userService;

	@PostMapping("/users")
	public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
		return userService.save(userDTO);
	}
	
	@PutMapping("/users/{id}")
	public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("id") long id) {
		userDTO.setId(id);
		return userService.save(userDTO);
	}
	
	@DeleteMapping(value = "/users")
	public void deleteNew(@RequestBody long[] ids) {
		userService.delete(ids);
	}
	
	@GetMapping(value = "/user")
    public String userApi(){
        return "Returned successfully from User API";
    }

    @GetMapping(value = "/admin")
    public String adminApi(){
        return "Returned successfully from Admin API";
    }
}
