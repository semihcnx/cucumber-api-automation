package com.swagger.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Order extends Base {
	public static final int INVALID_ORDER_STATUS_CODE = 400;
	private final String ORDER_ENDPOINT_PATH = "store/order/";
	private com.swagger.models.Order defaultOrder;
	
	public Order() {
		super();
		defaultOrder = new com.swagger.models.Order();
	}
	
	public String getPath() {
		return this.ORDER_ENDPOINT_PATH;
	}
	
	public Response getOrderById(RequestSpecification request, String id) {
		String url = getBaseUrl() + this.getPath() + id;
		return sendRequest(request, Base.GET_REQUEST, url, null);
	}
	
	public Response placeOrder(RequestSpecification request) {
		return placeOrder(
				request,
				defaultOrder);
	}
	
	public Response placeOrder(RequestSpecification request, com.swagger.models.Order order) {
		String url = getBaseUrl() + this.getPath();
		return sendRequest(request, Base.POST_REQUEST, url, order);
	}
	
	public com.swagger.models.Order getDefaultOrder() {
		return this.defaultOrder;
	}

	private String prepareShipDate(String dateString) {
		return dateString.replace("Z", "+0000");
	}
	
	public void verifyOrderValuesAreAsExpected(Response response, com.swagger.models.Order order) {
		String id = order.getId().toString();
		String petId = Integer.toString(order.getPetId());
		String quantity = Integer.toString(order.getQuantity());
		String shipDate = prepareShipDate(order.getShipDate());
		String status = order.getStatus();
		String complete = Boolean.toString(order.getComplete());
		
		verifyResponseKeyValues("id", id, response);
		verifyResponseKeyValues("petId", petId, response);
		verifyResponseKeyValues("quantity", quantity, response);
		verifyResponseKeyValues("shipDate", shipDate, response);
		verifyResponseKeyValues("status", status, response);
		verifyResponseKeyValues("complete",complete, response);
		
	}
}
