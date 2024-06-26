package EndPoints;

import TestBases.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAnOrder extends TestBase {
    @Test
    public void deleteAnOrder(){
        baseURL();

        Response response = given()
                        .header("Authorization", "Bearer " + accessToken)
                        .pathParam("orderId", orderID)
                        .when()
                        .delete("/orders/{orderId}")
                        .then()
                        .statusCode(204)
                        .extract().response();
        response.prettyPrint();

    }

}
