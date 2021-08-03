package com.katalon.wsaut.core.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.katalon.wsaut.webservice.UserSOAP;

@Component
public class Convertor {
	private ModelMapper modelMapper = new ModelMapper();
	
	public UserSOAP convertToSOAP(User entity) {
		//UserSOAP user = new UserSOAP();		
		UserSOAP user = modelMapper.map(entity, UserSOAP.class);		
		return user;
	}
	
	public User convertToEntity(UserSOAP user) {
		//User entity = new User();		
		User entity = modelMapper.map(user, User.class);		
		return entity;
	}
	
	public Gender convertToEntityGender(com.katalon.wsaut.webservice.Gender SOAPGender) {
		if (SOAPGender != null) {
			Gender entityGender = modelMapper.map(SOAPGender, Gender.class);
			return entityGender;
		}
		return null;
	}	
}
