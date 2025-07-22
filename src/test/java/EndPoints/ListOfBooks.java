package EndPoints;

import TestBases.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static Utils.ConfigReaderWriter.getPropKey;

@Epic("Book Management")
@Feature("Book Listing")
public class ListOfBooks extends TestBase {

    @Test
    @Story("Retrieve Book List")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get list of books with filtering options")
    public void getBookList() {

        Response response = given()
                .spec(requestSpec)
                .queryParam("type", getPropKey("book.type"))
                .queryParam("limit", getPropKey("book.limit"))
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .extract().response();

        attachResponseToReport(response);
    }
}