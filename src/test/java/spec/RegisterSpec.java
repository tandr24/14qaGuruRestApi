package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.with;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.http.ContentType.JSON;

public class RegisterSpec {

    public static ResponseSpecification responseSpec (int status)  {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .log(LogDetail.ALL)
                .build();
    }


    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .header("x-api-key", "reqres-free-v1")
            .log().all()
            .contentType(JSON);
}
