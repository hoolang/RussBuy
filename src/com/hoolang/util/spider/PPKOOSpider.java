package com.hoolang.util.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class PPKOOSpider implements PageProcessor {

	private Site site = Site.me().setDomain("www.ppkoo.com").setSleepTime(3000).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public void process(Page page) {

		page.putField("title", page.getHtml().xpath("h1/text()"));
		page.putField("price", page.getHtml().xpath("span[@id='js-price']//text()"));
		// <div class="cons_main_rt_c"><div class="cons_m"><ul class="info">
		page.putField("description", page.getHtml()
				.xpath("//div[@class='cons_main_rt_c']/div[@class='cons_m']/ul[@class='info']/li/text()").all());
		// <div class="b-thumb" id="thumblist"><ul>
		page.putField("images",
				page.getHtml().xpath("//div[@id='thumblist']/ul/li/a/img/@src").regex("(.*)_60x60.jpg").all());
		page.getHtml().getDocument().outputSettings();
	}

	@Override
	public Site getSite() {
		site.setCharset("UTF-8");
		return site;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("http://www.ppkoo.com/product/5429299.html");
		list.add("http://www.ppkoo.com/product/5171257.html");
		String[] strArr = new String[list.size()];
		list.toArray(strArr);
		Spider.create(new PPKOOSpider()).addUrl(strArr).run();
		//Spider.create(new PPKOOSpider()).addRequest(arg0)
	}
}