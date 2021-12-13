Feature: Bu Feature Swagger Pet Endpoint API işlemlerini içerir

Scenario: User evcil hayvan ekleyebilmesi
    Given the Swagger Petstore API is available
    When I add a Pet to the system 
    Then the pet request response has a '200' response code
    And the pet requests response contains the correct json data

Scenario: User evcil hayvanları silebilmesi
    Given a pet exists
    When I delete the pet
    And then search for the pet by it's id 
    Then the requests response will contain the value 'Pet not found' for the 'message' field

Scenario: User birden fazla etikete sahip evcil hayvanlar ekleyebilir
    Given the Swagger Petstore API is available
    Then I can add a pet that has multiple tags


Scenario: User etiketi olmayan evcil hayvanları ekleyebilir
    Given the Swagger Petstore API is available
    Then I can add a pet that has no tags


Scenario: Id's are automatically generated for pets when not supplied with the request
    Given the Swagger Petstore API is available
    When I add a pet to the system without providing an id value
    Then an id is automatically generated for the added pet


Scenario: Eksik parametre ile evcil hayvan eklenmesi ile 400 response dönmesi
    Given the Swagger Petstore API is available
    When I add a pet and the json body is malformed and consists of only '{'
    Then the pet request response has a '400' response code
    Then the requests response will contain the value 'bad input' for the 'message' field


