package day6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Serialization {
    //conversion of java object (POJO) to json format is called serialization
    @Test
    public void convertPOJOToJSON() {

        POJO pojo = new POJO();
        pojo.setFirstName("jagad");
        pojo.setLastName("g");
        pojo.setAge(25);
        pojo.setGender("Male");
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsondata = mapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(pojo);
            System.out.println(jsondata);

            given()
                    .when()
                    .contentType("application/json")
                    .body(jsondata)
                    .post("http://localhost:3000/users")
                    .then()
                    .statusCode(201).log().all();
            System.out.println("------------------------------");
            given()
                    .when().  contentType("application/json")
                    .body(jsondata)
                    .delete("http://localhost:3000/users?lastName=kumar")
                    .then()
                    .statusCode(200).log().all();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
