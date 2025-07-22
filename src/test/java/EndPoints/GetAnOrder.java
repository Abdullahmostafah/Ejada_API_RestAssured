package EndPoints;

import TestBases.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.ConfigReaderWriter.getPropKey;
import static io.restassured.RestAssured.given;

@Epic("Order Management")
@Feature("Order Details")
public class GetAnOrder extends TestBase {

    @Test
    @Story("Get Order Details")
    @Severity(SeverityLevel.NORMAL)
    @Description("Retrieve details of a specific order")
    public void getAnOrder() {

        Response response = given()
                .spec(requestSpec)
                .spec(requestSpec).auth().oauth2(getPropKey("access.token"))
                .pathParam("orderId", orderID)
                .when()
                .get("/orders/{orderId}")
                .then()
                .statusCode(200)
                .extract().response();

        attachResponseToReport(response);
    }
}