package com.katalon.wsaut.core.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
		modelMapper.addConverter(toImage);
		modelMapper.createTypeMap(Byte.class, Image.class);
		UserSOAP user = modelMapper.map(entity, UserSOAP.class);		
		return user;
	}
	
	public User convertToEntity(UserSOAP user) {
		//User entity = new User();
		modelMapper.addConverter(toBytes);
		modelMapper.createTypeMap(Image.class, Byte.class);
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
	
	Converter<byte[], Image> toImage = new AbstractConverter<byte[], Image>() {

		@Override
		protected Image convert(byte[] source) {
			ByteArrayInputStream bis = new ByteArrayInputStream(source);
			BufferedImage image = null;
			try {
				image = ImageIO.read(bis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return image;
		}
	};
	
	Converter<Image, byte[]> toBytes = new AbstractConverter<Image, byte[]>() {
		@Override
		protected byte[] convert(Image source) {
			byte[] bytes = null;
			if (source instanceof BufferedImage) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        try {
					ImageIO.write((BufferedImage) source, "jpg", baos);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        bytes = baos.toByteArray();
			}			
	        return bytes;
		}
	};
}
