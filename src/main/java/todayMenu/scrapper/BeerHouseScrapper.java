package todayMenu.scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BeerHouseScrapper {
  private static final String BEER_HOUSE_URL = "https://blog.naver.com/ysbeerhouse";
  private Scrapper scrapper;

  public BeerHouseScrapper(Scrapper scrapper) {
    this.scrapper = scrapper;
  }

  public String getTodayMenuImageUrl() throws InterruptedException {
    getFirstHtml();
    waitClientSideRendering();
    String imageUrl = scrapImageUrlFromHtml();

    return resizeTo966(imageUrl);
  }

  private void getFirstHtml() throws InterruptedException {
    scrapper.webDriver.get(BEER_HOUSE_URL);
    Thread.sleep(1000);
  }

  private void waitClientSideRendering() {
    WebElement afterRendering = scrapper.webDriver.findElement(By.id("mainFrame"));
    scrapper.webDriver.switchTo().frame(afterRendering);
  }

  private String scrapImageUrlFromHtml() {
    WebElement element2 = scrapper.webDriver.findElement(By.cssSelector("#printPost1 .bcc [id*=post-view] .se_mediaImage"));
    return element2.getAttribute("src");
  }

  private String resizeTo966(String imageUrl) {
    int index = imageUrl.indexOf("?");
    return imageUrl.substring(0, index) + "?type=w966";
  }
}
