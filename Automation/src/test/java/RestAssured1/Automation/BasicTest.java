package RestAssured1.Automation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.PayLoad;
public class BasicTest {
	
	static String postresponse;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
	
		
		// Adding a new entry using post response
		
		postresponse =	given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body( PayLoad.basicPayLoad())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		JsonPath js = new JsonPath(postresponse);
	String placeid =	js.getString("place_id");
	System.out.println("The place id"+" "+ placeid);
	
	
	
	// Now trying to update the place in that entry using put http method
	
	String newAddress = "Summer Walk, Africa";
	
	String putresponse =given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
	.body("{\r\n" + 
		"\"place_id\":\""+placeid+"\",\r\n" + 
		"\"address\":\""+newAddress+"\",\r\n" + 
		"\"key\":\"qaclick123\"\r\n" + 
		"}")
	.when().put("maps/api/place/update/json")
	.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
	
	System.out.println(putresponse);
	
	// Get status code 
	
	String getResponse  =given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id", placeid)
	.when().get("maps/api/place/get/json").then().assertThat().log().all().body("address", equalTo(newAddress)).statusCode(200).extract().response().toString();
	

	}
	
	
	



}
