package com.cg.gsm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gsm.entities.Address;
import com.cg.gsm.entities.User;
import com.cg.gsm.exception.UserNotFoundException;
import com.cg.gsm.repository.UserDAOInt;

@Service
public class UserServiceIntImp implements UserServiceInt {

	@Autowired
	public UserDAOInt userdaoint;

	// To add the User
	@Override
	public User add(User bean) {
		return userdaoint.save(bean);
	}

	// To find the User
	@Override
	public List<User> findAll() {
		return userdaoint.findAll();
	}

	// To delete a user using id
	@Override
	public void deleteById(int a) throws UserNotFoundException {

		try {
			userdaoint.deleteById(a);
		} catch (Exception e) {
			throw new UserNotFoundException("User with id= " + a + " Not Found");
		}

	}

	// To get user with its Primary key
	@Override
	public User findByPK(int pk) throws UserNotFoundException {
		Optional<User> u = userdaoint.findById(pk);
		if (u.isPresent())
			return u.get();
		else
			throw new UserNotFoundException("User with id= " + pk + " Not Found");
	}

	// To update Address of a User
	@Override
	public Address updateAdd(int a, Address address) throws UserNotFoundException {
		Optional<User> u = userdaoint.findById(a);
		if (u.isPresent()) {
			User user = u.get();
			if (user.getAddress() != null) {
				address.setId(user.getAddress().getId());
			}
			user.setAddress(address);
			userdaoint.save(user);
			return user.getAddress();
		}
		throw new UserNotFoundException("User with id= " + a + " Not Found");
	}

	// To update User
	@Override
	public User updateUser(User user) throws UserNotFoundException {
		Optional<User> u = userdaoint.findById(user.getId());
		if (u.isPresent()) {
			userdaoint.save(user);
			return user;
		}
		throw new UserNotFoundException("User with id= " + user.getId() + " Not Found");
	}

}
