package com.swproject.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swproject.workshopmongodb.domain.User;
import com.swproject.workshopmongodb.repository.UserRepositoty;
import com.swproject.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepositoty userRepositoty;

	public List<User> findAll() {
		return userRepositoty.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = userRepositoty.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
