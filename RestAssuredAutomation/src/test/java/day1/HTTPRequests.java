package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

/*
given-content type, set cookies, add auth, add param, set headers info etc
when-post, put,get, delete
then-validate status code, extract response, extract headers cookies and response body
*/
//get- to retrive data -> we dont have when condition - only when and then
// post- to create user data -> we will use hashmap as of now- need given,when,then
// put- to update the key and values -> we will use hashmap as of now- need given,when,then
// delete- to delete the values -> we dont have when condition - only when and then

// given-> contentType, body
// when-> get,post,put,delete url's
//then-> statusCode,body,log,all,equalTo

public class HTTPRequests {
    String id;

    @Test
    public void getUser() {

        given()

                .when()
                .get("http://localhost:3000/users")

                .then()
                .statusCode(200)
                .body("[0].fistName", equalTo("jagadeesh"))
                .log().all();
    }

    @Test
    public void postUser() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "smith");
        map.put("age", "25");
        map.put("subjectID", "java");
        map.put("job", "triner");

        given()
                .contentType("application/json")
                .body(map)
                .when()
                .post("http://localhost:3000/users")
                .then()
                .statusCode(201)
                .log().all();
        // to get the id value

        id= given().contentType("application/json")
                .body(map)
                .when()
                .post("http://localhost:3000/users")
                .jsonPath().get("firstName").toString();
        System.out.println(id);
    }

    @Test
    public void putUser() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "smith");
        map.put("age", "32");

        given().contentType("application/json")
                .body(map)
                .when()
                .post("http://localhost:3000/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("smith"))
                .body("age", equalTo("32"))
                .log().all();


    }
    @Test
    public void deleteUser() {
        given().contentType("application/json")
                .when().delete("http://localhost:3000/users" + id)
                        .then()
                        .statusCode(404);
    }
}
