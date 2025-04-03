import runners.TestRunner;

public class Runner {

    public static void main(String[] args) {
        GetToken.getToken();
        System.out.println("Les features");
        GetFeatureTest.setup();
        System.out.println("Runner Test");
        org.junit.runner.JUnitCore.runClasses(TestRunner.class);
        System.out.println("Tests exécutés");
        BaseTest.tearDown();
    }
}
