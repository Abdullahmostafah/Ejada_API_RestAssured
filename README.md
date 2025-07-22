# EjadaRestAssured

## Overview

EjadaRestAssured is a Java-based project designed for automated testing of RESTful APIs using the RestAssured framework. This project provides a structured approach to validate API endpoints, ensuring their functionality, reliability, and performance. It is built to help developers and QA engineers efficiently test APIs with reusable test cases and clear reporting.

## Features

- **API Testing**: Comprehensive test suites for validating REST API endpoints.
- **RestAssured Framework**: Leverages RestAssured for simplified and readable API test scripts.
- **Modular Structure**: Organized codebase for easy maintenance and scalability.
- **Test Reporting**: Integration with testing frameworks like TestNG or JUnit for detailed test reports.
- **Configuration Management**: Supports environment-specific configurations (e.g., dev, staging, production).

## Prerequisites

To run this project, ensure you have the following installed:

- **Java**: JDK 11 or higher
- **Maven**: For dependency management and building the project
- **IDE**: IntelliJ IDEA, Eclipse, or any Java-compatible IDE
- **Git**: For cloning the repository
- (Optional) A running instance of the API you want to test

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Abdullahmostafah/EjadaRestAssured.git
   cd EjadaRestAssured
   ```

2. **Install Dependencies**:
   Run the following command to download all required dependencies:
   ```bash
   mvn clean install
   ```

3. **Configure the Environment**:
   - Update the `src/main/resources/config.properties` file (if applicable) with your API base URL and other environment-specific settings.
   - Example configuration:
     ```properties
     base.url=https://api.example.com
     api.key=your-api-key
     ```

## Usage

1. **Running Tests**:
   Execute the test suite using Maven:
   ```bash
   mvn test
   ```

2. **Test Structure**:
   - Test cases are located in `src/test/java`.
   - Example test case:
     ```java
     import io.restassured.RestAssured;
     import org.testng.annotations.Test;

     public class SampleApiTest {
         @Test
         public void testGetEndpoint() {
             RestAssured.given()
                 .baseUri("https://api.example.com")
                 .when()
                 .get("/endpoint")
                 .then()
                 .statusCode(200);
         }
     }
     ```

3. **Viewing Test Reports**:
   - After running tests, check the `target/surefire-reports` directory for detailed test results (if using Maven Surefire plugin).
   - Alternatively, integrate with tools like Allure for enhanced reporting.

## Project Structure

```
EjadaRestAssured/
├── src/
│   ├── main/
│   │   ├── java/                # Utility classes and helpers
│   │   └── resources/           # Configuration files (e.g., config.properties)
│   └── test/
│       └── java/                # Test cases and suites
├── pom.xml                      # Maven configuration file
├── README.md                    # Project documentation
└── .gitignore                   # Files and directories to ignore in Git
```

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes and commit (`git commit -m "Add your feature"`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a Pull Request.

Please ensure your code follows the project's coding standards and includes relevant tests.
