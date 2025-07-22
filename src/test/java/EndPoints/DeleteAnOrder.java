package EndPoints;

import TestBases.TestBase;
import Utils.ConfigReaderWriter;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.ConfigReaderWriter.getPropKey;
import static io.restassured.RestAssured.given;

@Epic("Order Management")
@Feature("Order Deletion")
public class DeleteAnOrder extends TestBase {

    @Test(priority = 4)
    @Story("Remove Order")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete an existing order")
    public void deleteAnOrder() {

        Response response = given()
                .spec(requestSpec)
                .auth().oauth2(getPropKey("access.token"))
                .pathParam("orderId", orderID)
                .when()
                .delete("/orders/{orderId}")
                .then()
                .statusCode(204)
                .extract().response();

        attachResponseToReport(response);
    }
}