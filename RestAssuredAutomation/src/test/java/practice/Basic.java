package practice;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Basic {

    @Test(priority = 1)
    public void basicTest() {
      Response response= given()
                .when()
                .pathParam("mypath","search.html")
                .queryParam("search","rohit%20sharma")
                .get("https://search.espncricinfo.com/ci/content/site/{mypath}");

      String resp =response.then().statusCode(200).extract().response().body().asString();

     //   System.out.println(resp);
        System.out.println("------------------------------");
        String content=response.getHeader("Content-Type").toString();
        System.out.println(content);

    }
}

//https://search.espncricinfo.com/ci/content/site/search.html?search=rohit%20sharma
/*
HTTP/1.1 200 OK
Content-Type: text/html; charset=UTF-8
Content-Length: 18932
EMEA-Info: ip-10-159-33-139 0.377
Content-Encoding: gzip
X-Varnish: 316673880 331385105
X-Varnish-Cache: HIT
X-Varnish-Hits: 2
Accept-Ranges: bytes
Expires: Wed, 11 Mar 2026 02:04:56 GMT
Cache-Control: max-age=0, no-cache, no-store
Pragma: no-cache
Date: Wed, 11 Mar 2026 02:04:56 GMT
Connection: keep-alive
Vary: Accept-Encoding
Strict-Transport-Security: max-age=86400
 */