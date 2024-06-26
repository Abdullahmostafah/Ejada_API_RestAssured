package EndPoints;

import TestBases.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class GetToken extends TestBase {

    @Test
    public void getToken(){
        baseURL();
        String requestBody = "{\n" +
                "  \"clientName\": \""+ clientName + "\",\n" +
                "  \"clientEmail\": \"" + clientEmail + "\"\n" +
                "}";


        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api-clients/")
                .then()
                .statusCode(anyOf(is(201), is(409)))  // Expecting either 200 OK or 409 Conflict
                .extract().response();
        response.prettyPrint();

        if (response.getStatusCode() == 201) {
            accessToken = response.jsonPath().getString("accessToken");
            setAccessToken(accessToken);
            System.out.println("Access Token: " + accessToken);
        } else if (response.getStatusCode() == 409) {
            System.out.println("Error: " + response.jsonPath().getString("message"));
        }

    }

}
