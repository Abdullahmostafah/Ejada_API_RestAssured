package EndPoints;

import TestBases.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static Utils.ConfigReaderWriter.getPropKey;

@Epic("Order Management")
@Feature("Order Creation")
public class SubmitAnOrder extends TestBase {

    @Test(priority = 2)
    @Story("Create New Order")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Submit a new book order with valid details")
    public void postOrder() {

        String requestBody = String.format("{\"bookId\":%s,\"customerName\":\"%s\"}",
                getPropKey("order.book.id"),
                getPropKey("order.customer.name"));

        Response response = given()
                .spec(requestSpec)
                .auth().oauth2(getPropKey("access.token"))
                .body(requestBody)
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .body("orderId", notNullValue())
                .extract().response();

        setOrderID(response.jsonPath().getString("orderId"));
        attachRequestAndResponseToReport(requestBody, response);
    }
}