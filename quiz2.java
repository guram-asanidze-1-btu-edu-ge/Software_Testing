import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class quiz2 {

    @Test
    public void testCreateUserWithValidData() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", "testuser1");
        requestBody.put("password", "testpassword");
        requestBody.put("email", "testuser1@test.com");

        given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/Account/v1/User")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void testCreateUserWithExistingUsername() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", "testuser1");
        requestBody.put("password", "testpassword");
        requestBody.put("email", "testuser2@test.com");

        given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/Account/v1/User")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void testCreateUserWithInvalidEmail() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", "testuser3");
        requestBody.put("password", "testpassword");
        requestBody.put("email", "invalid-email");

        given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/Account/v1/User")
                .then()
                .assertThat()
                .statusCode(400);
    }
}
