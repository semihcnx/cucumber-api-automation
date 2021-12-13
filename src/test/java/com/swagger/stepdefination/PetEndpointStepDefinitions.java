package com.swagger.stepdefination;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.swagger.endpoints.Pet;

public class PetEndpointStepDefinitions {

	private World world;
	private Pet pet = new Pet();

	public PetEndpointStepDefinitions(World world) {
		this.world = world;
	}

	@When("^I add a Pet to the system$")
	public void i_add_a__pet_to_the_system() {
		pet.addPet(world);
	}

	@Then("^the pet request response has a '(\\d+)' response code$")
	public void the_pet_request_response_has_the_correct_response_code(Integer rc) {
		pet.verifyResponseStatusValue(world.getResponse(), rc.intValue());
	}

	@Then("^the pet requests response contains the correct json data$")
	public void the_pet_requests_response_contains_the_correct_json_data() {
		pet.verifyPetValuesAreAsExpected(world.getResponse(), pet.getDefaultPet());
	}

	@Given("^a pet exists$")
	public void a_pet_exists() {
		pet.addPet(world);
		pet.verifyResponseStatusValue(world.getResponse(), Pet.SUCCESS_STATUS_CODE);
	}

	@When("^I delete the pet$")
	public void i_delete_the_pet() {
		world.setResponse(pet.deletePet(world.getRequest()));
	}

	@When("^then search for the pet by it's id$")
	public void then_search_for_the_pet_by_its_id() {
		world.setResponse(pet.getPetById(world.getRequest()));
	}

	@Given("^a cat is '(.*?)'$")
	public void a_cat_is_availablilability(String availability) {
		pet.addPet(world,
				new com.swagger.models.Pet(16, "7:feline", "Pussy Cat", "image1:image2", "17:Furry", availability));
	}

	@Then("^I can add a pet that has multiple tags$")
	public void i_can_add_a_pet_that_has_multiple_tags() {
		pet.addPet(world, new com.swagger.models.Pet(16, "45:rodent", "Rat", "image1", "17:Furry,29:cute,33:Small", "available"));
		pet.verifyResponseStatusValue(world.getResponse(), Pet.SUCCESS_STATUS_CODE);
	}

	@Then("^I can add a pet that has no tags$")
	public void i_can_add_a_pet_that_has_no_tags() {
		pet.addPet(world, new com.swagger.models.Pet(16, "45:rodent", "Rat", "image1", "", "available"));
		pet.verifyResponseStatusValue(world.getResponse(), Pet.SUCCESS_STATUS_CODE);
	}

	@When("^I add a pet to the system without providing an id value$")
	public void i_add_a_pet_to_the_system_without_providing_an_id_value() {
		pet.addPet(world,
				new com.swagger.models.Pet(null, "45:rodent", "Rat", "image1", "17:Furry,29:cute,33:Small", "available"));
	}

	@Then("^an id is automatically generated for the added pet$")
	public void an_id_is_automatically_generated_for_the_added_pet() {
		pet.verifyPetHasAnId(world.getResponse());
	}

	@When("^I add a pet and the json body is malformed and consists of only '(.*?)'$")
	public void i_add_a_pet_and_the_json_body_is_malformed(String body) {
		world.setRequest(pet.getRequestWithJSONHeaders());
		world.setResponse(pet.addPetWithBody(world.getRequest(), body));
	}

}
