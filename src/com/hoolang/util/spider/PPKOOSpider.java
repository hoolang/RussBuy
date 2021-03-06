package com.hoolang.util.spider;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import com.hoolang.service.ProductsService;
import com.hoolang.spider.pipeline.SimplePipeline;
import com.hoolang.util.Hoolang;

public class PPKOOSpider implements PageProcessor {

	private Site site = Site.me().setDomain("www.ppkoo.com").setSleepTime(3000).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	private ProductsService productService;
	@Override
	public void process(Page page) {
		page.putField("url", page.getUrl());
		page.putField("title", page.getHtml().xpath("h1/text()"));
		page.putField("price", page.getHtml().xpath("span[@id='js-price']//text()"));
		page.putField("description", page.getHtml()
				.xpath("//div[@class='cons_main_rt_c']/div[@class='cons_m']/ul[@class='info']/li/text()").all());
		page.putField("images",
				page.getHtml().xpath("//div[@id='thumblist']/ul/li/a/img/@src").regex("(.*)_60x60.jpg").all());

	}

	@Override
	public Site getSite() {
		site.setCharset("UTF-8");
		return site;
	}
	
	public PPKOOSpider() {};

    public static PPKOOSpider create() {
        return new PPKOOSpider();
    }
    
	public PPKOOSpider spider(ProductsService productService,String[] urls){
		Spider.create(new PPKOOSpider()).addPipeline(new SimplePipeline(productService)).addUrl(urls).run();
		return this;
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("http://www.ppkoo.com/product/5429299.htmls");
		list.add("http://www.ppkoo.com/product/5171257.html");
		String[] strArr = new String[list.size()];
		list.toArray(strArr);
		
		Spider.create(new PPKOOSpider())
		.addUrl(strArr)
		.addPipeline(new SimplePipeline().setLangType(Hoolang.LangType.ZH.toString()))
		.run();
	}

	public ProductsService getProductService() {
		return productService;
	}

	public void setProductService(ProductsService productService) {
		this.productService = productService;
	}
}