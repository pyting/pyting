package us.pyting.crawler.processor.example;

import us.pyting.crawler.Page;
import us.pyting.crawler.ResultItems;
import us.pyting.crawler.Site;
import us.pyting.crawler.Spider;
import us.pyting.crawler.processor.PageProcessor;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youlink.yi@gmail.com <br>
 * @since 0.1.0
 */
public class BaiduBaikePageProcessor implements PageProcessor {

    private Site site = Site.me()//.setHttpProxy(new HttpHost("127.0.0.1",8888))
            .setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.putField("name", page.getHtml().xpath("//title/text()"));
        page.putField("description", page.getHtml().xpath("//div[@class='para']/allText()"));
   }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	Instant start=Instant.now();
        //single download
        Spider spider = Spider.create(new BaiduBaikePageProcessor()).thread(2);
        String urlTemplate = "http://baike.baidu.com/search/word?word=%s";
//        ResultItems resultItems = spider.<ResultItems>get(String.format(urlTemplate, "水力发电"));
//        System.out.println(resultItems);

        //multidownload
        List<String> list = new ArrayList<String>();
        list.add(String.format(urlTemplate,"水力发电"));
        list.add(String.format(urlTemplate,"风力发电"));
        list.add(String.format(urlTemplate,"太阳能"));
        list.add(String.format(urlTemplate,"地热发电"));
        List<ResultItems> resultItemses = spider.<ResultItems>getAll(list);
        for (ResultItems resultItemse : resultItemses) {
            System.out.println(resultItemse.getAll());
        }
        spider.close();
        
        Instant end=Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		long millis = timeElapsed.toMillis();
		System.out.println("[Finished in " + millis / 1000.0 + " s]");
    }
}
