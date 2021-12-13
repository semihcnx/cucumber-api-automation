Feature:Bu Feature Swagger Order Endpoint API işlemlerini içerir

Scenario: Var olmayan bir sipariş aranırken doğru hata mesajı verilmesi
    Given the Swagger Petstore API is available
    When I search for an order with an id value of '123456'
    Then the requests response will contain the value 'Order not found' for the 'message' field

Scenario: Kullanıcılar evcil hayvanlar için sipariş verebilmesi
    Given the Swagger Petstore API is available
    When I place an order for a pet with an order id of '58'
    Then the order request response has a '200' response code
    And the order requests response contains the correct json data

Scenario: Kullanıcıların id ye göre sipariş arayabilmesi
		Given an order exists for a pet
		When I search for the order by its id
		Then the complete order is returned

Scenario: Kullanıcıların mevcut evcil hayvanlar için sipariş verebilmesi
		Given a cat is 'available'
		Then I am able to place an order for a cat

Scenario: Kullanıcılar mevcut olmayan evcil hayvanlar için sipariş verememesi
  Given a cat is 'unavailable'
		Then I am not able to place an order for a cat

