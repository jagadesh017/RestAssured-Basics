package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.management.MBeanAttributeInfo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseBodyThroughJson {

    @Test
    public void ParsingJSONObjectResponseData() {

        // scenario to get one object value from json array
        given()
             .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/users")
                .then()
                .statusCode(200)
                .header("Content-type", "application/json")
                .body("users[0].subjectID", equalTo(null));
    }
    @Test
    public void ParsingJSONObjectResponseData1() {
        // scenario to get multiple validations

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/users");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getHeader("content-type"), "application/json");
        // to get body we need to use jsonpath.get

        String body = response.jsonPath().get("users[1].id");
        Assert.assertEquals(body, null);
    }
    @Test
    public void ParsingJSONObjectResponseData2() {
        // scenario: we need to print all lastnames

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/users");

        // we need to create json object to get all lastnames in the json array
        JSONArray jsonObject = new JSONArray(response.asString());
        boolean status = false;
        for (int i = 0; i < jsonObject.length(); i++) {
            System.out.println(jsonObject.getJSONObject((i)) + ",");
            String lastNameValues = jsonObject.getJSONObject((i)).get("lastName").toString();
            System.out.print(lastNameValues+ ",");
            if (lastNameValues.equals("ggr")) {
                status = true;
                break;
            }
        }
        Assert.assertTrue(status);

    }
    @Test
    public void ParsingJSONObjectResponseData3() {
        //secnatio to add price of all products

        Response response = given()

                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/users");

        JSONArray jo = new JSONArray(response.asString());
        double total=0;
        for (int i = 0; i < jo.length(); i++) {

           String  totalAmount = jo.getJSONObject(i).get("price").toString();

            total = total + Double.parseDouble(totalAmount);

        }
        System.out.println( total);




    }
}