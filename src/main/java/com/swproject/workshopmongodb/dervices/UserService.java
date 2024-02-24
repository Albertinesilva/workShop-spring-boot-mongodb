package com.swproject.workshopmongodb.dervices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swproject.workshopmongodb.domain.User;
import com.swproject.workshopmongodb.repository.UserRepositoty;

@Service
public class UserService {

	@Autowired
	private UserRepositoty userRepositoty;

	public List<User> findAll() {
		return userRepositoty.findAll();
	}
}
