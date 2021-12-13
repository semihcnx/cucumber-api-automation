package com.swagger.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class User extends Base {
	private final String USER_ENDPOINT_PATH = "user/";
	private com.swagger.models.User defaultUser;
	
	public User() {
		super();
		defaultUser = new com.swagger.models.User();
	}
	
	public String getPath() {
		return this.USER_ENDPOINT_PATH;
	}
	
	public com.swagger.models.User getDefaultUser() {
		return this.defaultUser;
	}
	
	public Response getUserByUsername(RequestSpecification request) {
		return getUserByUsername(request, getDefaultUser().getUsername());
	}
	
	public Response getUserByUsername(RequestSpecification request, String username) {
		String url = getBaseUrl() + this.getPath() + username;
		return sendRequest(request, Base.GET_REQUEST, url, null);
	}

	public Response createUser(RequestSpecification request) {		
        return createUser(
				request,
				defaultUser);
	}
	
	public Response createUser(RequestSpecification request, com.swagger.models.User user) {
		String url = getBaseUrl() + this.getPath();
		return sendRequest(request, Base.POST_REQUEST, url, user);
	}	

}
