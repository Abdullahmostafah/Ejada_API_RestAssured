package EndPoints;

import TestBases.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateAnOrder extends TestBase {

    @Test
    public void updateAnOrder(){
        baseURL();
        String requestBody = "{\n" +
                "  \"customerName\": \"" + updatedCustomerName + "\"\n" +
                "}";

        Response response=
                given()
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(ContentType.JSON)
                        .pathParam("orderId", orderID)
                        .body(requestBody)
                        .when()
                        .patch("/orders/{orderId}")
                        .then()
                        .statusCode(204) //200
                        //.body("customerName", equalTo(updatedCustomerName))
                        .extract().response();
        response.prettyPrint();
    }
}

