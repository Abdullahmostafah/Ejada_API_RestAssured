package TestBases;

import Utils.*;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static io.restassured.config.SSLConfig.sslConfig;

public class TestBase {
    protected static String orderID;
    protected RequestSpecification requestSpec;
    private static final int HTTP_TIMEOUT_MS = 30000;
    private static final DateTimeFormatter LOG_TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    private final Map<String, PrintStream> testLogStreams = new HashMap<>();

    @BeforeSuite
    public void globalSetup() throws IOException {
        // Archive previous reports before cleaning
        FileUtils.archivePreviousReports();
        // Clean directories once at suite start
        FileUtils.cleanDirectory(FileUtils.LOG_DIR);
        FileUtils.cleanDirectory(FileUtils.ALLURE_RESULTS_DIR);
        FileUtils.cleanDirectory(FileUtils.EXTENT_REPORT_DIR);

        configureRestAssured();
    }

    @BeforeClass
    public void classSetup() {
        // Optional: Add any class-level setup that doesn't interfere with reporting
    }

    @BeforeMethod
    public void setupPerTest(Method testMethod) throws FileNotFoundException {
        String testName = testMethod.getName();
        initializeTestLogging(testName);
        configureRequestSpecification(testName);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownAll() {
        generateTestReports();
        cleanupTestResources();
    }

    private void configureRestAssured() {
        baseURI = ConfigReaderWriter.getPropKey("base.uri");
        config = config()
                .sslConfig(sslConfig().relaxedHTTPSValidation())
                .redirect(redirectConfig().followRedirects(true))
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", HTTP_TIMEOUT_MS)
                        .setParam("http.socket.timeout", HTTP_TIMEOUT_MS));
    }

    private void initializeTestLogging(String testName) throws FileNotFoundException {
        String logFilePath = String.format("%s/%s-%s.log",
                FileUtils.LOG_DIR,
                testName,
                LocalDateTime.now().format(LOG_TIMESTAMP_FORMATTER));

        PrintStream logStream = new PrintStream(new FileOutputStream(logFilePath));
        testLogStreams.put(testName, logStream);
    }

    private void configureRequestSpecification(String testName) {
        PrintStream testLogStream = testLogStreams.get(testName);

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setContentType(ContentType.JSON)
                .addFilters(createLoggingFilters(testLogStream))
                .setConfig(config)
                .build();
    }

    private List<Filter> createLoggingFilters(PrintStream logStream) {
        List<Filter> filters = new ArrayList<>();
        filters.add(new AllureRestAssured());
        filters.add(new RequestLoggingFilter(LogDetail.ALL, logStream));
        filters.add(new ResponseLoggingFilter(LogDetail.ALL, logStream));
        filters.add(new RequestLoggingFilter(LogDetail.ALL, System.out));
        filters.add(new ResponseLoggingFilter(LogDetail.ALL, System.out));
        return filters;
    }

    protected void generateTestReports() {
        try {
            // Generate Allure report only once at suite end
            ReportUtils.generateAllureReport();
            String htmlSummary = ReportUtils.getEnhancedSummaryHtml();
            attachTextToReport("Test Execution Summary", htmlSummary);
        } catch (Exception e) {
            System.err.println("Error during report generation: " + e.getMessage());
        }
    }

    private void cleanupTestResources() {
        testLogStreams.values().forEach(stream -> {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception e) {
                System.err.println("Error closing log stream: " + e.getMessage());
            }
        });
        testLogStreams.clear();
    }

    // Reporting utilities
    protected void attachResponseToReport(Response response) {
        Allure.addAttachment("Response", "application/json", response.asPrettyString());
        Allure.addAttachment("Status Code", String.valueOf(response.getStatusCode()));
    }

    protected void attachRequestAndResponseToReport(String requestBody, Response response) {
        Allure.addAttachment("Request", "application/json", requestBody);
        attachResponseToReport(response);
    }

    protected void attachTextToReport(String name, String content) {
        Allure.addAttachment(name, content);
    }

    public static String getOrderID() {
        return orderID;
    }

    public static void setOrderID(String orderID) {
        TestBase.orderID = orderID;
    }
}