package todayMenu.scrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Scrapper {

  public ChromeOptions options;
  public WebDriver webDriver;

  public Scrapper() {
    this.options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");

    System.setProperty("webdriver.chrome.driver",
            System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");

    webDriver = new ChromeDriver(options);
  }

  public void close() {
    this.webDriver.close();
    this.webDriver.quit();
  }
}
