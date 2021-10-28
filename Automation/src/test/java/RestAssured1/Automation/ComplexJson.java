package RestAssured1.Automation;

import Files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(PayLoad.ComplexPayload());

		int res = js.getInt("courses.size()");
		System.out.println(res);
		String res1 = js.getString("dashboard.purchaseAmount");
		System.out.println(res1);
		String res2 = js.getString("dashboard.website");
		System.out.println(res2);
		
		for (int i =0; i<res; i++) {
			
			String titles  = js.getString("courses["+i+"].title");
			System.out.println(titles);
			if (titles.equals("RPA")) {
				
				String ress= js.getString("courses["+i+"].price");
				System.out.println(ress);
				break;
			}
			
		}
		
	}

}
