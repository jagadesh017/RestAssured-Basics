package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestPayloadResponse {
    public static final String[] COURSE_ARR = {"C", "java"};

    /* to get post response and update post response we will use post, put keywords
    we can store payload data in four different ways
    1. using HashMap- store data in key and value bases

    2. using org.json- we need to call json object and store the data in key and values bases

    3. using POJO - plain old java object: we will use encapsulation concept to store the data and retrive the data
    Encapsulation: wrapping of variables and methods together a single class/unit
    we will use setter and getter method to set the values and get the values

    4. using json external file- we will store data in json format and we will call the same data by using
     file reader, json tokener and json object
     */

    // using hashmap
    @Test
    public void testPostRequestPayloadResponseUsingHashMap() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "scott");
        map.put("location", "england");

        String courseArr[] = {"C", "java"};
        for(int i = 0; i < courseArr.length; i++){
            map.put("courseName", courseArr[i]);
        }

        given().contentType("application/json")
                .body(map)
                .when()
                .post("http://localhost:3000/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("scott"))
                .body("location", equalTo("england"))
               // .body("courseName[0]", equalTo("C"))
                //.body("courseName[1]", equalTo("java"))
               // .header("content-type", "application/json; charset=utf-8")
                .log().all();
    }

    //using json object
    @Test
    public void testPostRequestPayloadResponseUsingJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("name", "scott");
        obj.put("location", "england");

        String course[] = {"c", "c++"};
        obj.put("courses", course);

        given().contentType("application/json")
                .body(obj.toString())
                .when()
                .post("http://localhost:3000/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("scott"))
                .body("location", equalTo("england"))
                .body("courses[0]", equalTo("c"))
                .body("courses[1]", equalTo("c++"))
                //.header("content-type", "application/json; charset=utf-8")
                .log().all();
    }

    //using POJO: plain old java object
    @Test
    public void testPostRequestPayloadResponseUsingPOJO() {
        // we need to create object for pojo class and call setter and getter methods

        POJOClass  obj = new POJOClass();
        obj.setName("scott");
        obj.setLocation("england");
        String course[] = {"C","c++"};
        obj.setCourse(course);

        given().contentType("application/json")
                .body(obj)
                .when()
                .post("http://localhost:3000/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("scott"))
                .body("location", equalTo("england"))
                .body("course[0]", equalTo("C"))
                .body("course[1]", equalTo("c++"))
                //.header("content-type", "application/json; charset=utf-8")
                .log().all();
    }

    @Test
    public void testPostRequestPayloadResponseUsingJSONExtenalFile() throws IOException {

        File file = new File("./students.json");
        FileReader reader = new FileReader(file);
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject obj = new JSONObject(tokener);

        given().contentType("application/json")
                .body(obj.toString())
                .when()
                .post("http://localhost:3000/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("scott"))
                .body("location", equalTo("england"))
                .body("course[0]", equalTo("C"))
                .body("course[1]", equalTo("c++"))
                //.header("content-type", "application/json; charset=utf-8")
                .log().all();


    }
}
