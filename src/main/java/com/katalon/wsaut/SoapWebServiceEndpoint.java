package com.katalon.wsaut;

import java.util.List;

import jaxb.org.example.webservice.AddUserRequest;
import jaxb.org.example.webservice.AddUserResponse;
import jaxb.org.example.webservice.GetAllUserRequest;
import jaxb.org.example.webservice.GetAllUserResponse;
import jaxb.org.example.webservice.GetUserRequest;
import jaxb.org.example.webservice.GetUserResponse;
import jaxb.org.example.webservice.UpdateUserRequest;
import jaxb.org.example.webservice.UpdateUserResponse;
import jaxb.org.example.webservice.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.katalon.wsaut.service.SoapWebUserService;

@Endpoint
public class SoapWebServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.example.org/webservice";
	
	@Autowired
	SoapWebUserService soapWebUserService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUserById(@RequestPayload GetUserRequest request) {
		GetUserResponse userResponse = new GetUserResponse();
		User user = soapWebUserService.getUserById(request.getId());
		userResponse.setUser(user);
		return userResponse;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUserRequest")
	@ResponsePayload
	public GetAllUserResponse getAllUser(@RequestPayload GetAllUserRequest request) {
		GetAllUserResponse response = new GetAllUserResponse();
		List<User> result = soapWebUserService.getAllUser();
		response.getUser().addAll(result);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
	@ResponsePayload
	public AddUserResponse addNewUser(@RequestPayload AddUserRequest request) {
		AddUserResponse response = new AddUserResponse();
		User newUser = soapWebUserService.addUser(request.getUser());
		response.setUser(newUser);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
	@ResponsePayload
	public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
		UpdateUserResponse response = new UpdateUserResponse();
		User updatedUser = soapWebUserService.updateUser(request.getId(), request.getUser());
		response.setUser(updatedUser);
		return response;
	}
}
