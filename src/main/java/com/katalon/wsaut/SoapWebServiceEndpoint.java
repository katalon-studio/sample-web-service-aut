package com.katalon.wsaut;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.ws.soap.MTOM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.katalon.wsaut.core.entity.Convertor;
import com.katalon.wsaut.core.entity.User;
import com.katalon.wsaut.core.service.UserService;
import com.katalon.wsaut.webservice.AddUserRequest;
import com.katalon.wsaut.webservice.AddUserResponse;
import com.katalon.wsaut.webservice.DeleteUserRequest;
import com.katalon.wsaut.webservice.DeleteUserResponse;
import com.katalon.wsaut.webservice.GetAllUserRequest;
import com.katalon.wsaut.webservice.GetAllUserResponse;
import com.katalon.wsaut.webservice.GetUserRequest;
import com.katalon.wsaut.webservice.GetUserResponse;
import com.katalon.wsaut.webservice.UpdateUserRequest;
import com.katalon.wsaut.webservice.UpdateUserResponse;
import com.katalon.wsaut.webservice.UserSOAP;

@MTOM(enabled=true, threshold=4096)
@Endpoint
public class SoapWebServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.katalon.com/wsaut/webservice";
	
	@Autowired
	UserService userService;
	
	@Autowired
	Convertor convertor;
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUserById(@RequestPayload GetUserRequest request) {
		GetUserResponse userResponse = new GetUserResponse();
		User user = userService.getById(request.getId());
		userResponse.setUser(convertor.convertToSOAP(user));
		return userResponse;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUserRequest")
	@ResponsePayload
	public GetAllUserResponse getAllUser(@RequestPayload GetAllUserRequest request) {		
		GetAllUserResponse response = new GetAllUserResponse();
		List<UserSOAP> result = userService.list(convertor.convertToEntityGender(request.getGender()),Integer.valueOf(request.getAge()))
										   .stream()
										   .map(user -> convertor.convertToSOAP(user))
										   .collect(Collectors.toList());
											 
		response.getUser().addAll(result);
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
	@ResponsePayload
	public AddUserResponse addNewUser(@RequestPayload AddUserRequest request) {
		AddUserResponse response = new AddUserResponse();
		User newUser = userService.create(convertor.convertToEntity(request.getUser()));
		response.setUser(convertor.convertToSOAP(newUser));
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
	@ResponsePayload
	public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
		UpdateUserResponse response = new UpdateUserResponse();
		User updatedUser = userService.updateAge(request.getId(), request.getAge());
		response.setUser(convertor.convertToSOAP(updatedUser));
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
	@ResponsePayload
	public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
		DeleteUserResponse response = new DeleteUserResponse();
		userService.delete(request.getId());
		response.setNotification("Delete successfully");
		return response;
	}
}
