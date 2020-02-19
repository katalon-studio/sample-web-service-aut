package com.katalon.wsaut.core.entity;

import org.example.webservice.UserSOAP;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {
	
	public UserSOAP convertToSOAP(User entity) {
		UserSOAP user = new UserSOAP();
		BeanUtils.copyProperties(entity, user);
		
		return user;
	}
	
	public User convertToEntity(UserSOAP user) {
		User entity = new User();
		BeanUtils.copyProperties(user, entity);
		
		return entity;
	}
}
