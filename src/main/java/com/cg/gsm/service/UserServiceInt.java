package com.cg.gsm.service;

import java.util.List;
import com.cg.gsm.entities.Address;
import com.cg.gsm.entities.User;
import com.cg.gsm.exception.UserNotFoundException;

public interface UserServiceInt {

	public User add(User bean);

	public List<User> findAll();

	public void deleteById(int a) throws UserNotFoundException;

	public User findByPK(int a) throws UserNotFoundException;

	public Address updateAdd(int a, Address address) throws UserNotFoundException;

	public User updateUser(User user) throws UserNotFoundException;

}
