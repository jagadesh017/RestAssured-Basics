package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Logs {


    @Test
    public void getLogs() {
        given().pathParam("mypath", "search")
                .queryParam("q", "testing")
                .queryParam("sca_esv", "3407319d660045c2")
                .when()
                .get("https://www.google.com/{mypath}")

                .then()
                //.log().all(); // to get all logs
                //.log().cookies(); // to get cookies
        //.log().headers(); // to get headers
                .log().body(); // to get body
    }
}
