package day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Authorizations {
    /*types of authorizations
    1. Basic Auth
    2. Digest Auth
    3. preemptive Basic Auth
    4. Bearer Token / jwt token
    5. OAuth 1.0
    6. OAuth 2.0
     */
    String access_token = "gho_11AMQWJLY0osogtyogCPh4_QNjeUEKugoeXDJPc9JBZO5vuge4OzA6ieJRPxtlNn4QILWM4JHPT735jp7P";
    String token = "gho_11AMQWJLY0osogtyogCPh4_QNjeUEKugoeXDJPc9JBZO5vuge4OzA6ieJRPxtlNn4QILWM4JHPT735jp7P";
    @Test(priority = 1)
    public void basicAuth() {

        given()
                .auth()
                .basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void digestAuth() {
        given()
                .auth()
                .digest("postman", "password")
                .when()
                .get("https://postman-echo.com/digest-auth")
                .then()
                .statusCode(200);
    }

    @Test(priority = 3)
    public void preemptiveBasicAuth() {
        given()
                .auth()
                .preemptive()
                .basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }

    @Test
    public void bearerToken() {

        given()
                .headers("Authorization", "Bearer " +token)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(401)
                .log().all();
    }

    @Test(priority = 5)
    public void oauth1() {
        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "token")
                .when()
                .get("https://postman-echo.com/oauth1")
                .then()
                .statusCode(200);
    }

    @Test(priority = 6)
    public void oauth2() {
        String accessToken = "xxxxyyyzzz"; // replace with actual token

        given()
                .auth().oauth2(accessToken)
                .when()
                .get("https://postman-echo.com/oauth2")
                .then()
                .statusCode(200);

    }

    @Test
        public  void bearerToken2()  {
        given()
                .auth().oauth2(access_token)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200);
        }


}
