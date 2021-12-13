package com.swagger.stepdefination;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import com.swagger.endpoints.Base;

public class Definitions {
	
	private World world;
	private Base base = new Base();
	
	public Definitions(World world) {
		this.world = world;
	}
	
	@Given("^the Swagger Petstore API is available$")
	public void the_swagger_petstore_api_is_available() {
		String url =base.getBaseUrl() + "swagger.json";
		base.sendRequest(null, Base.GET_REQUEST, url, null).then().statusCode(200);
	}
	
	@Then("^the requests response will contain the value '(.*?)' for the '(.*?)' field$")
	public void i_will_be_able_to_run_connected_step_definitions(String val, String key) {
		base.verifyResponseKeyValues(key, val, world.getResponse());
	}

}
