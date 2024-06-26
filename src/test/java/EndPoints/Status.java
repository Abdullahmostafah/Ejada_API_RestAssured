package EndPoints;

import TestBases.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Status extends TestBase {

    @Test
    public void getStatus(){
        baseURL();
        Response response = given()
                .when()
                .get("/status")
                .then()
                .statusCode(200)
                .extract().response();
        response.prettyPrint();
    }

}
