package TestBases;

import io.restassured.RestAssured;


public class TestBase {
protected static String accessToken;
protected static String orderID;
public static String clientName = "Abdullah";
public static String clientEmail = "Abdulla5aaaa52haz12@Hotmail.com"; //Change to create new token
public static String updatedCustomerName = "Medhat";


    public static String getOrderID() {
        return orderID;
    }

    public static void setOrderID(String orderID) {
        TestBase.orderID = orderID;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        TestBase.accessToken = accessToken;
    }

    public void baseURL() {
        RestAssured.baseURI = "https://simple-books-api.glitch.me";
    }
}
