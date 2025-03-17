package com.vk.microservices.orderservice;

import com.vk.microservices.orderservice.client.InventoryClient;
import com.vk.microservices.orderservice.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.shaded.org.hamcrest.Matcher;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldSubmitOrder() {
		String payload = """
				{
					"skuCode" : "iphone_14",
					"price" : 12330,
					"quantity" : 1 
				}
				""";

		InventoryClientStub.stubInventoryCall("iphone_14",1);

		String responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(payload)
				.when()
				.post("/api/order")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();

		System.out.println("Response: " + responseBodyString);
	}

}
