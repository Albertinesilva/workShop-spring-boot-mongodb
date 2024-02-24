package com.swproject.workshopmongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.swproject.workshopmongodb.domain.User;
import com.swproject.workshopmongodb.repository.UserRepositoty;

public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepositoty userRepositoty;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepositoty.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		
	}

}
