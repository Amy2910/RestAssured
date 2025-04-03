import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetToken {

    private static final String AUTH_URL = "https://xray.cloud.getxray.app/api/v2/authenticate";
    private static final String CLIENT_ID = "AFC9594E0A7F469592C85F5D6BDC6EEB";
    private static final String CLIENT_SECRET = "ae0402b7075192c2a554bde07c9ee9ff424e3ab764a1ac082fda09aa39b370f8";
    public static Object GetToken;

    // Méthode pour récupérer le token
    public static String getToken() {
        String requestBody = "{ \"client_id\": \"" + CLIENT_ID + "\", " +
                "\"client_secret\": \"" + CLIENT_SECRET + "\" }";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(AUTH_URL)
                .then()
                .statusCode(200)
                .extract().response();

        return response.asString().replace("\"", ""); // Nettoie le token
    }
}
