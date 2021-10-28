package RestAssured1.Automation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.PayLoad;

public class GetBookings {
	
	public static String getBookingID() {
		
		
		String bookingid ="3";
		return bookingid;
	}

	public static void main(String[] args) {

		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String getbookingids = given().log().all().header("Content-Type", "application/json").when().get("/booking/")
				.then().assertThat().statusCode(200).log().all().extract().response().toString();

		String getbooking = given().log().all().header("Content-Type", "application/json").cookie("token","233").accept("application/json").when().get("/booking/"+getBookingID() +"").then().log().all().assertThat().statusCode(200).body("firstname", equalTo("Jones")).extract()
				.response().asString();

		JsonPath js= new JsonPath(getbooking);
		
//
//		System.out.println(js.getString("bookingdates[1]"));
//		Assert.assertEquals("Mark", js.getString("firstname"));
//		Assert.assertEquals(284, js.getInt("totalprice"));
//		Assert.assertEquals(false, js.getBoolean("depositpaid"));
	
		
		
		//*********** Checking the POST   request
		
	String postResponse =	given().log().all().header("Content-Type", "application/json").accept("application/json").body(PayLoad.manualPayLoad()).when().post("/booking").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	
	//*********** Checking the PUT  request
	String updateid =given().log().all().header("Content-type","application/json").accept("application/json").cookie("token", "abc123").auth().preemptive().basic("admin", "password123").accept("application/json").body(PayLoad.putPayLoad()).when().put("/booking/"+getBookingID() +"").then().log().all().extract().response().asString();
		
	
	//*********** Checking the DELETE  request
	
//	given().log().all().header("Content-type","application/json").accept("application/json").header("Authorization","YWRtaW46cGFzc3dvcmQxMjM=]").cookie("token", "abc123").when().delete("/booking/"+getBookingID() +"").then().log().all();
	
	
	//*********** Checking the ping request
	
	given().log().all().when().get("/ping").then().assertThat().statusCode(201).log().all();
	
	}

}
