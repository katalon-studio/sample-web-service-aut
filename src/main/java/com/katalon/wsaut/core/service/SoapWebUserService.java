package com.katalon.wsaut.core.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import jaxb.org.example.webservice.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.katalon.wsaut.core.entity.UserConvertor;
import com.katalon.wsaut.entity.UserEntity;
import com.katalon.wsaut.core.repository.UserRepository;;

@Service
public class SoapWebUserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	UserConvertor convertor;
	
	public User getUserById(Long id) {
		Optional<UserEntity> optUser = repository.findById(id);
		if (optUser.isPresent()) {
			return convertor.convert(optUser.get());
		}else return null;
	}
	
	public List<User> getAllUser(){
		List<UserEntity> result = repository.findAll();
		return result.stream().map(entity -> convertor.convert(entity))
					   		  .collect(Collectors.toList());
	}
	
	public User addUser(User user) {		
		UserEntity entity = convertor.convertToEntity(user);
		UserEntity newEntity = repository.save(entity);
		return convertor.convert(newEntity);		
	}
	
	public User updateUser(Long id, User user) {
		Optional<UserEntity> optEntity = repository.findById(id);
		UserEntity newEntity = convertor.convertToEntity(user);
		if (optEntity.isPresent()) {
			UserEntity oldEntity = optEntity.get();
			if (!newEntity.getUsername().isEmpty()) {
				oldEntity.setUsername(newEntity.getUsername());
			}
			if (!newEntity.getPassword().isEmpty()) {
				oldEntity.setPassword(newEntity.getPassword());
			}
			if (newEntity.getGender() != null) {
				oldEntity.setGender(newEntity.getGender());
			}
			if (Integer.valueOf(newEntity.getAge()) != null) {
				oldEntity.setAge(newEntity.getAge());
			}
			
			repository.save(oldEntity);
			User updatedUser = convertor.convert(oldEntity);
			return updatedUser;
		}
		return null;
	}
	
	
}
