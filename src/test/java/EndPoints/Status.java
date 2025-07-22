package EndPoints;

import TestBases.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("API Status Checks")
@Feature("Service Health")
public class Status extends TestBase {

    @Test
    @Story("API Availability")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify API status endpoint returns 200 OK")
    public void getStatus() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/status")
                .then()
                .statusCode(200)
                .extract().response();

        attachResponseToReport(response);
    }
}