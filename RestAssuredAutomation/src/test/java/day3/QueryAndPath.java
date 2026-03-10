package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class QueryAndPath {
    @Test
    public void PathParam() {

      /*http:localhost:3000:users?id=1 -> base url + path + query param
        http:localhost:3000 is base url
        users is path param
        id=1 is query param
       https://www.google.com/search?q=testing&sca_esv=3407319d660045c2 -> base url + path + query param
       https://www.google.com -- base url
       search- path param
       q=testing- query param 1
       sca_esv=3407319d660045c2 - query param2

    */

        given()
                .pathParam("mypath", "search")
                .queryParam("q", "testing")
                .queryParam("sca_esv", "3407319d660045c2")
                .when()
                .get("https://www.google.com/{mypath}")
                .then()
                .statusCode(200).log().all();
    }
}
