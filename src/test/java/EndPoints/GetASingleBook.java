package EndPoints;

import TestBases.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static Utils.ConfigReaderWriter.getPropKey;

@Epic("Book Management")
@Feature("Book Details")
public class GetASingleBook extends TestBase {

    @Test
    @Story("Get Book Details")
    @Severity(SeverityLevel.NORMAL)
    @Description("Retrieve details of a specific book by ID")
    public void getASingleBook() {

        Response response = given()
                .spec(requestSpec)
                .pathParam("bookId", getPropKey("book.id"))
                .when()
                .get("/books/{bookId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(Integer.parseInt(getPropKey("book.id"))))
                .extract().response();

        attachResponseToReport(response);
    }
}