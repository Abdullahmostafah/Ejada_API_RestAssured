package EndPoints;

import TestBases.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllOrders extends TestBase {
    @Test
    public void getAllOrders(){
        baseURL();
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/orders")
                .then()
                .statusCode(200).extract().response();
        response.prettyPrint();
    }
}
