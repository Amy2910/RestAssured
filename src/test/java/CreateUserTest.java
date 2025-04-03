import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest {
    @Test
    public void testCreateUser(){
        File json = new File("src/test/resources/user.json");
        given().baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body("job", equalTo("Developpeuse"))
                .body("name", equalTo("Alice"))
                .body("id", notNullValue())
                .log().all()
                .extract().response();
    }
}