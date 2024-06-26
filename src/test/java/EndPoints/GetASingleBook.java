package EndPoints;

import TestBases.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetASingleBook extends TestBase {
    @Test
    public void getASingleBook(){
        baseURL();
        Response response = given()
                .pathParams("bookId",1)
                .when()
                .get("/books/{bookId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .extract().response();
        response.prettyPrint();

    }
}
