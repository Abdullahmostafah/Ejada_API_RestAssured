package EndPoints;

import TestBases.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static Utils.ConfigReaderWriter.*;

@Epic("Authentication")
@Feature("Token Management")
public class GetToken extends TestBase {

    @Test(priority = 1)
    @Story("Generate Access Token")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Generate API access token for authenticated requests")
    public void getToken() {

        String timestamp = String.valueOf(System.currentTimeMillis());
        String clientName = getPropKey("client.name") + timestamp;
        String clientEmail = timestamp + getPropKey("client.email");

        String requestBody = String.format("{\"clientName\":\"%s\",\"clientEmail\":\"%s\"}",
                clientName,
                clientEmail);

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/api-clients")
                .then()
                .statusCode(anyOf(is(201), is(409)))
                .extract().response();

        attachRequestAndResponseToReport(requestBody, response);

        if (response.getStatusCode() == 201) {
            String accessToken = response.jsonPath().getString("accessToken");
            setPropKey("access.token", accessToken);
            attachTextToReport("Generated Token", accessToken);
        }
    }
}