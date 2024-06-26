package EndPoints;

import TestBases.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAnOrder extends TestBase {

    @Test
    public void getAnOrder(){
        baseURL();
        Response response =
                given()
                        .header("Authorization", "Bearer " + accessToken)
                        .pathParam("orderId", orderID)
                        .when()
                        .get("/orders/{orderId}")
                        .then()
                        .statusCode(200)
                        //.body("orderId", equalTo(orderID))
                        .extract().response();
        response.prettyPrint();
    }
}

