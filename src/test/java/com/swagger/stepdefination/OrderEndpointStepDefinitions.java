package com.swagger.stepdefination;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.swagger.endpoints.Order;

public class OrderEndpointStepDefinitions {

	private World world;
	private Order order = new Order();

	public OrderEndpointStepDefinitions(World world) {
		this.world = world;
	}

	@When("^I search for an order with an id value of '(.*?)'$")
	public void i_search_for_an_order_with_an_id_value_of_id(String id) {
		world.setResponse(order.getOrderById(world.getRequest(),id));
	}

	@When("^I place an order for a pet with an order id of '(\\d+)'$")
	public void i_make_a_post_request(Integer orderId) {
		world.setRequest(order.getRequestWithJSONHeaders());
		world.setResponse(order.placeOrder(world.getRequest(), new com.swagger.models.Order(orderId, 0, 1, "2019-02-05T14:11:44.922Z", "placed", false)));
	}

	@Then("^the order request response has a '(\\d+)' response code$")
	public void the_response_has_the_correct_response_code(Integer rc) {
		order.verifyResponseStatusValue(world.getResponse(), rc.intValue());
	}

	@Then("^the order requests response contains the correct json data$")
	public void the_json_response_contains_the_correct_data() {
		order.verifyResponseKeyValues("id", "58", world.getResponse());
	}

	@Given("^an order exists for a pet$")
	public void an_order_exists_for_a_pet() {
		world.setRequest(order.getRequestWithJSONHeaders());
		world.setResponse(order.placeOrder(world.getRequest()));
		order.verifyResponseStatusValue(world.getResponse(), Order.SUCCESS_STATUS_CODE);
	}

	@When("^I search for the order by its id$")
	public void i_search_for_the_order_by_its_id() {
		world.setResponse(order.getOrderById(world.getRequest(), order.getDefaultOrder().getId().toString()));
	}

	@Then("^the complete order is returned$")
	public void the_complete_order_is_returned() {
		order.verifyOrderValuesAreAsExpected(world.getResponse(), order.getDefaultOrder());
	}
	
	@Then("^I am able to place an order for a cat$")
	public void i_am_able_to_place_an_order_for_a_cat() {
		world.setResponse(order.placeOrder(world.getRequest(), new com.swagger.models.Order(33, world.getPet().getId(), 1, "2019-02-05T14:11:44.922Z", "placed", false)));
		order.verifyResponseStatusValue(world.getResponse(), order.SUCCESS_STATUS_CODE);
	}
	
	@Then("^I am not able to place an order for a cat$")
	public void i_am_not_able_to_place_an_order_for_a_cat() {
		world.setResponse(order.placeOrder(world.getRequest(), new com.swagger.models.Order(22, world.getPet().getId(), 1, "2019-02-05T14:11:44.922Z", "placed", false)));
		order.verifyResponseStatusValue(world.getResponse(), order.INVALID_ORDER_STATUS_CODE);
	}
}
