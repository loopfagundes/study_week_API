package br.com.dbserver;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

public class Rest {
    private final String url;
    private final RequestSpecification requestSpecification;
    private final ResponseSpecification responseSpecification;

    public Rest(String _url) {
        url = _url;
        Logger LOGGER = LogManager.getLogger(Rest.class);
        requestSpecification = new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config()
                        .logConfig(LogConfig.logConfig()
                                .enablePrettyPrinting(true)
                                .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
                                .defaultStream(new PrintStreamExtent(IoBuilder.forLogger(LOGGER).buildPrintStream()))
                        )
                ).log(LogDetail.ALL).build();
        responseSpecification = new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }

    public ValidatableResponse get(String path, int statusCode) {
        return RestAssured.given().spec(requestSpecification).when().get(url + path).then()
                .spec(responseSpecification).statusCode(statusCode);
    }

    public ValidatableResponse post(String path, int statusCode, Class object) {
        return RestAssured.given().spec(requestSpecification).body(object).when().post(url + path).then()
                .spec(responseSpecification).statusCode(statusCode);
    }

    public ValidatableResponse post(String path, int statusCode, String json) {
        return RestAssured.given().spec(requestSpecification).body(json).when().post(url + path).then()
                .spec(responseSpecification).statusCode(statusCode);
    }

    public ValidatableResponse put(String path, int statusCode, Class object) {
        return RestAssured.given().spec(requestSpecification).body(object).when().put(url + path).then()
                .spec(responseSpecification).statusCode(statusCode);
    }

    public ValidatableResponse patch(String path, int statusCode, Class object) {
        return RestAssured.given().spec(requestSpecification).body(object).when().patch(url + path).then()
                .spec(responseSpecification).statusCode(statusCode);
    }

    public ValidatableResponse delete(String path, int statusCode) {
        return RestAssured.given().spec(requestSpecification).when().delete(url + path).then()
                .spec(responseSpecification).statusCode(statusCode);
    }
}
