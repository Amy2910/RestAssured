package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class LoginSteps {
    WebDriver driver = DriverManager.getDriver();

    @Given("utilisateur est sur la page de connexion de url {string}")
    public void utilisateur_connexion(String url) {
        driver.get(url);
    }

    @When("il entre son identifiant {string} et son mot de passe {string}")
    public void il_entre_son_identifiant_et_son_mot_de_passe(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

//    @Then("il est connecté et voit son tableau de bord")
//    public void il_est_connecte_et_voit_son_tableau_de_bord() {
////        boolean isDisplayed = driver.findElement(By.id("account_summary")).isDisplayed();
////        Assert.assertTrue("L'utilisateur devrait être connecté", isDisplayed);
//    }

    @Then("il voit un message d'erreur")
    public void il_voit_un_message_d_erreur() {
        boolean isDisplayed = driver.findElement(By.className("error")).isDisplayed();
        Assert.assertTrue("Le message d'erreur devrait s'afficher", isDisplayed);
    }

    @After
    public void fermerNavigateur() {
        DriverManager.quitDriver();
    }
}

