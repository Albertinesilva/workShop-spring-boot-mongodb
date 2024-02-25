package com.swproject.workshopmongodb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swproject.workshopmongodb.domain.Post;
import com.swproject.workshopmongodb.repository.PostRepository;
import com.swproject.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepositoty;

	public Post findById(String id) {
		Optional<Post> obj = postRepositoty.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
}