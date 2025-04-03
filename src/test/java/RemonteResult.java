import io.restassured.response.Response;
import java.io.File;
import static io.restassured.RestAssured.given;

public class RemonteResult {
    public static void sendResults() {
        String token = GetToken.getToken();
        String url = "https://xray.cloud.getxray.app/api/v2/import/execution/cucumber";

        File resultFile = new File("target/cucumber.json");

        if (!resultFile.exists()) {
            System.out.println("Le fichier de résultats n'existe pas !");
            return;
        }

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .contentType("application/json")
                .body(resultFile)
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Résultats envoyés avec succès : " + response.asString());
    }
}

