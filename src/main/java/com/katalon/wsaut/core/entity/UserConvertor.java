package com.katalon.wsaut.core.entity;

import jaxb.org.example.webservice.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {
	
	public User convert(UserEntity entity) {
		User user = new User();
		BeanUtils.copyProperties(entity, user);
		
		return user;
	}
	
	public UserEntity convertToEntity(User user) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		
		return entity;
	}
}
