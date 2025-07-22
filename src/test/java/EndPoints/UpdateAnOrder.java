package EndPoints;

import TestBases.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static Utils.ConfigReaderWriter.getPropKey;

@Epic("Order Management")
@Feature("Order Updates")
public class UpdateAnOrder extends TestBase {

    @Test(priority = 3)
    @Story("Modify Order")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Update an existing order's details")
    public void updateAnOrder() {

        String requestBody = String.format("{\"customerName\":\"%s\"}",
                getPropKey("order.updated.customer.name"));

        Response response = given()
                .spec(requestSpec)
                .auth().oauth2(getPropKey("access.token"))
                .pathParam("orderId", orderID)
                .body(requestBody)
                .when()
                .patch("/orders/{orderId}")
                .then()
                .statusCode(204)
                .extract().response();

        attachRequestAndResponseToReport(requestBody, response);
    }
}