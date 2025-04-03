import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;

import static io.restassured.RestAssured.given;

public class GetFeatureTest {

    protected static String TOKEN;

    @BeforeAll
    public static void setup() {
        TOKEN = GetToken.getToken(); // Utilise la classe TokenUtils pour récupérer le token
    }

    public static void downloadFeatures() {

    }

    public static void unzipFile(String zipFilePath, String extractDir) {
    }

    @Test
    public void testFeature() {
        String url = "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=POEI20252-524";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MDEyMTo5YmQxYjU4Ny04OGJlLTQzZjctODg5MS1hYTY5ZDZmNjJjZTkiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTc0MzQ5ODE2NywiZXhwIjoxNzQzNTg0NTY3LCJhdWQiOiJBRkM5NTk0RTBBN0Y0Njk1OTJDODVGNUQ2QkRDNkVFQiIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IkFGQzk1OTRFMEE3RjQ2OTU5MkM4NUY1RDZCREM2RUVCIn0.TduTCbxiVWzuR8tjoEwE_6j6ZaaQbwZ2cTpZ3UwGUaA";

        // Envoi de la requête
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().response();

        // Vérification du statut de la réponse
        if (response.getStatusCode() == 200) {
            try (InputStream inputStream = response.asInputStream();
                 OutputStream outputStream = new FileOutputStream(new File("src/test/resources/exported.zip"))) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                System.out.println("Le fichier a été téléchargé avec succès.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Échec de la requête. Code de statut : " + response.getStatusCode());
        }
    }

    @AfterAll
    public static void tearDown() {
        System.out.println( "Envoi des résultats à XRAY ");
        RemonteResult.sendResults();
    }

}

//    public void main(String[] args) {
//        GetFeatureTest test = new GetFeatureTest();
//        test.testFeature();
//    }
