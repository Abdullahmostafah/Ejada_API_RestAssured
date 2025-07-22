package EndPoints;

import TestBases.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.ConfigReaderWriter.getPropKey;
import static io.restassured.RestAssured.given;

@Epic("Order Management")
@Feature("Order Retrieval")
public class GetAllOrders extends TestBase {

    @Test
    @Story("List All Orders")
    @Severity(SeverityLevel.NORMAL)
    @Description("Retrieve list of all orders")
    public void getAllOrders() {

        Response response = given()
                .spec(requestSpec).spec(requestSpec)
                .auth().oauth2(getPropKey("access.token"))
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .extract().response();

        attachResponseToReport(response);
    }
}