package com.tiagoperroni.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagoperroni.mongo.domain.User;
import com.tiagoperroni.mongo.dto.UserDTO;
import com.tiagoperroni.mongo.repository.UserRepository;
import com.tiagoperroni.mongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(String id, User obj) {
		User newObj = findById(id);
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		return repo.save(newObj);
		
	}

}
