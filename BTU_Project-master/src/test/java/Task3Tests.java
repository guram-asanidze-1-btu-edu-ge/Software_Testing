import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Task3Tests {

    private String baseUrl = "https://bookstore.toolsqa.com/BookStore/v1/Books";

    @Test
    public void testStatusCode() {
        given()
                .when()
                .get(baseUrl)
                .then()
                .statusCode(200);
    }

    @Test
    public void testLastBookIsbn() {
        Response response = RestAssured.get(baseUrl);
        String lastIsbn = response.jsonPath().getString("books[-1].isbn");
        assertThat(lastIsbn, equalTo("9781593277574"));
    }

    @Test
    public void testFirstAndSecondBookPagesCount() {
        given()
                .when()
                .get(baseUrl)
                .then()
                .body("books[0].pages", equalTo(234))
                .body("books[1].pages", equalTo(254));
    }
}
