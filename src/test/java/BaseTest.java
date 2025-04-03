import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseTest {

    private static final String ZIP_FILE_PATH = "src/test/resources/exported.zip";
    private static final String EXTRACT_DIR = "src/test/resources/features/";


    @BeforeAll
    public static void setup() {
        System.out.println("Les features");
        GetFeatureTest.downloadFeatures();
        GetFeatureTest.unzipFile(ZIP_FILE_PATH, EXTRACT_DIR);
    }

    @Test
    public void testFileExists() {
        File file = new File("src/test/resources/exported.zip");
        assertTrue(file.exists(), "Le fichier ZIP n'existe pas !");
        System.out.println("Le fichier ZIP existe à l'emplacement : " + file.getAbsolutePath());
    }


    @AfterAll
    public static void tearDown() {
        System.out.println( "Envoi des résultats à XRAY ");
        RemonteResult SendResult;
        RemonteResult.sendResults();
    }
}
