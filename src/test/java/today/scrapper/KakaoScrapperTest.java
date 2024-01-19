package today.scrapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

import static org.assertj.core.api.Assertions.fail;


class KakaoScrapperTest {

  ChromeOptions options;

  @BeforeEach
  public void setUp() {
    options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
  }


  @Test
  public void test() throws IOException, InterruptedException {

    String url = "https://blog.naver.com/ysbeerhouse";

    System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
    WebDriver webDriver = new ChromeDriver(options);
    webDriver.get(url);
    Thread.sleep(1000);

    WebElement element = webDriver.findElement(By.id("mainFrame"));
    webDriver.switchTo().frame(element);

    WebElement element2 = webDriver.findElement(By.cssSelector("#printPost1 .bcc [id*=post-view]"));
    WebElement element3 = element2.findElement(By.cssSelector(".se_mediaImage"));
    String imageUrl = element3.getAttribute("src");
    int index = imageUrl.indexOf("?");
    imageUrl = imageUrl.substring(0, index);
    String size = "?type=w966";
    imageUrl = imageUrl + size;


    try {
      URL saveImageUrl = new URL(imageUrl);
      ReadableByteChannel readableByteChannel = Channels.newChannel(saveImageUrl.openStream());

      FileOutputStream fileOutputStream = new FileOutputStream("./src/test/resources/test.png");
      fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    fail("");
  }

}

//https://postfiles.pstatic.net/MjAyNDAxMTZfMjk1/MDAxNzA1MzgxODgyMDM1.j_wkhnCW1U762geAMjQO0Jf5su2ZwwImYovI7W56Nhwg.kx1A1gE4RL1OeRv9i0nWKnmKH-OmUfJ8wY778iezI3sg.PNG.ysbeerhouse/20240116_141002_0000.png?type=w966
//https://postfiles.pstatic.net/MjAyNDAxMTZfMjk1/MDAxNzA1MzgxODgyMDM1.j_wkhnCW1U762geAMjQO0Jf5su2ZwwImYovI7W56Nhwg.kx1A1gE4RL1OeRv9i0nWKnmKH-OmUfJ8wY778iezI3sg.PNG.ysbeerhouse/20240116_141002_0000.png?type=w