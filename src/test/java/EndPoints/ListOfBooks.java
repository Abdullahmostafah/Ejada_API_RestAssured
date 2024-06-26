package EndPoints;

import TestBases.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListOfBooks extends TestBase {
    @Test
    public void getBookList() {
        baseURL();
        Response response = given()
                .queryParam("type", "fiction")
                .queryParam("limit",20)
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .extract().response();
        response.prettyPrint();
    }

}



