package todayMenu;

import todayMenu.scrapper.BeerHouseScrapper;
import todayMenu.scrapper.GoshiBuffetScrapper;
import todayMenu.scrapper.Scrapper;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Scrapper scrapper = new Scrapper();
    BeerHouseScrapper beerHouseScrapper = new BeerHouseScrapper(scrapper);
    GoshiBuffetScrapper goshiBuffetScrapper = new GoshiBuffetScrapper(scrapper);
    String beerHouseUrl = beerHouseScrapper.getTodayMenuImageUrl();
    String goshiBuffetUrl = goshiBuffetScrapper.getTodayMenuImageUrl();

    System.out.println(beerHouseUrl);
    System.out.println(goshiBuffetUrl);
    scrapper.close();
  }
}