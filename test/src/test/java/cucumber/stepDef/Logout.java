package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Logout {

  WebDriver driver;
  WebElement sidebar;

  String baseUrl = "https://www.saucedemo.com/";
  String username = "standard_user";
  String password = "secret_sauce";
  
  @Given("user is on dashboard page")
  public void user_is_on_saucedemo_dashboard_page() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions opt = new ChromeOptions();
    opt.setHeadless(false);

    driver = new ChromeDriver(opt);
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    driver.get(baseUrl);
    driver.findElement(By.id("user-name")).sendKeys(username);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.id("login-button")).click();

    String targetedUrl = "https://www.saucedemo.com/inventory.html";

    String dashbordUrl = driver.getCurrentUrl();

    Assert.assertEquals(dashbordUrl, targetedUrl);
  }

  @When("sidebar is hidden")
  public void sidebar_is_hidden(){    
    sidebar = driver.findElement(By.xpath("//div[@class='bm-menu-wrap']"));
    String statusSidebar = sidebar.getAttribute("hidden"); 
    Assert.assertEquals(statusSidebar, "true");
  }

  @When("user click menu icon")
  public void user_click_menu_icon() {
    driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
  }

  @Then("sidebar is opened")
  public void sidebar_is_opened(){
    sidebar = driver.findElement(By.xpath("//div[@class='bm-menu-wrap']"));
    String statusSidebar = sidebar.getAttribute("hidden");
    Assert.assertNull(statusSidebar);
  }

  @When("user click logout menu")
  public void user_click_logout_menu(){    
    driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
  }

  @Then("user redirect to login page")
  public void user_redirect_to_login_page(){
    String targetedUrl = "https://www.saucedemo.com/";
    String currentUrl = driver.getCurrentUrl();
    Assert.assertEquals(currentUrl, targetedUrl);
    
    driver.close();
    driver.quit();
  }
}
