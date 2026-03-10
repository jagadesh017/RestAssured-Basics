package day3;


import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Cookies {

    @Test
    public void getCookies() {


        Response response = given()

                .pathParam("mypath", "search")
                .queryParam("q", "testing")
                .queryParam("sca_esv", "3407319d660045c2")
                .when()
                .get("https://www.google.com/{mypath}");

        // get single cookie value

        String cookies = response.getCookie("AEC");
        System.out.println(cookies);
        // to get multiple cookies values


       Map<String, String> allCookies= response.getCookies();

       for(String key: allCookies.keySet()) {
           System.out.println(key + "and  " + response.getCookie(key));
       }
    }

}

