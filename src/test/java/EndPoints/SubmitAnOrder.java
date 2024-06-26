package EndPoints;

import TestBases.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class SubmitAnOrder extends TestBase {

    @Test
    public void postOrder(){
        baseURL();
        String requestBody = "{\n" +
                "  \"bookId\": 1,\n" +
                "  \"customerName\": \"John\"\n" +
                "}";
        Response response = (Response) given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody)
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .body("orderId", notNullValue())
                .extract().response();
        response.prettyPrint();
        String orderID = response.jsonPath().getString("orderId");
        setOrderID(orderID);
    }
}

