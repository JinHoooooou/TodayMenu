package todayMenu.scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoshiBuffetScrapper {
  private static final String GOSHI_BUFFET_URL = "https://pf.kakao.com/_Vhnxob/posts";
  private Scrapper scrapper;

  public GoshiBuffetScrapper(Scrapper scrapper) {
    this.scrapper = scrapper;
  }

  public String getTodayMenuImageUrl() throws InterruptedException {
    getFirstHtml();
    return scrapImageUrlFromHtml();
  }

  private String scrapImageUrlFromHtml() {
    List<WebElement> list = scrapper.webDriver.findElements(By.cssSelector(".wrap_post"));
    // 현재는 두번째 게시물 (알바구하느라..) (첫번째 게시물은 알바 구인글)
    WebElement target = list.get(1);
    return target.findElement(By.cssSelector(".link_post .img_thumb")).getAttribute("src");
  }

  private void getFirstHtml() throws InterruptedException {
    scrapper.webDriver.get(GOSHI_BUFFET_URL);
    Thread.sleep(1000);
  }
}
