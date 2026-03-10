package day3;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Headers {

    @Test
    public void getHeaders() {


       Response response= given()
                .pathParam("mypath", "search")
                .queryParam("q", "testing")
                .queryParam("sca_esv", "3407319d660045c2")
                .when()
                .get("https://www.google.com/{mypath}");

       // to get single header value

                String headerValue= response.getHeader("content-type");
                System.out.println(headerValue);
        // to get all headers values

              io.restassured.http.Headers allHeaders=  response.getHeaders();
              for( Header head: allHeaders) {

                  System.out.println( head.getName()+ " and " +head.getValue());
              }

    }
    }


