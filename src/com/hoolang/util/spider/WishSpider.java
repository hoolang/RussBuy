package com.hoolang.util.spider;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.hoolang.service.ProductsService;
import com.hoolang.spider.pipeline.SimplePipeline;
import com.hoolang.util.Hoolang;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class WishSpider implements PageProcessor {

	private Site site = Site.me()
			.setDomain("www.wish.com")
			.setSleepTime(3000)
			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36")
			.addCookie("cookie", "_xsrf=6e7058739c2b4d9885020dcaa36b9b27; sweeper_session=\"NjZmZDAzMTAtN2QwNi00MDVkLTg3YzEtYWJiNTQ1NDg0MzI4MjAxNi0wNC0yMCAwMjozNTo1MC4zNzczNTU=|1461119750|81dca46503c3f1448e29c47456a7aa31fcbb102e\"; __utma=96128154.386040617.1461119607.1461135505.1461145295.7; __utmc=96128154; __utmz=96128154.1461119607.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); bsid=cd1cb516f9e24d00a7ffb8c9f81ba9fb; sweeper_uuid=e6e81042f9364690b953568dfdf7b0bc")
			.addHeader("Accept-Encoding", "gzip, deflate")
			.addHeader("Accept-Cache-Control","max-age=0")
			.addHeader("Connection","keep-alive")
			.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
			.addHeader("X-Requested-With","XMLHttpRequest")
			.addHeader("X-XSRFToken","6e7058739c2b4d9885020dcaa36b9b27")
			;
	
	//.addHeader("Accept", "application/json, text/javascript, \*\/\*; q=0.01");

	private ProductsService productService;
	@Override
	public void process(Page page) {
		System.out.println("page.getHtml()--->"+page.getHtml());
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
		return site;
	}
	
	public WishSpider() {};

    public static WishSpider create() {
        return new WishSpider();
    }
    
	public WishSpider spider(ProductsService productService,String[] urls){
		Spider.create(new WishSpider()).addPipeline(new SimplePipeline(productService)).addUrl(urls).run();
		return this;
	}
	
	public static void main(String[] args) {
		Request request = new Request("https://www.wish.com/api/product/get");
		request.setMethod("post");

		
		/**
		Accept-Encoding:gzip, deflate
		Accept-Language:zh-CN,zh;q=0.8
		Connection:keep-alive
		Content-Length:137
		Content-Type:application/x-www-form-urlencoded; charset=UTF-8
		Cookie:_xsrf=6e7058739c2b4d9885020dcaa36b9b27; sweeper_session="NjZmZDAzMTAtN2QwNi00MDVkLTg3YzEtYWJiNTQ1NDg0MzI4MjAxNi0wNC0yMCAwMjozNTo1MC4zNzczNTU=|1461119750|81dca46503c3f1448e29c47456a7aa31fcbb102e"; __utmt=1; bsid=f3b46f4d411c4e29a5608e56aaf16d2c; __utma=96128154.386040617.1461119607.1461131065.1461134042.5; __utmb=96128154.13.10.1461134042; __utmc=96128154; __utmz=96128154.1461119607.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); sweeper_uuid=98ded8bc15634e84a1448fa302134939
		**/
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cid", "5625aabf2836c3628b83e2df"));
		nvps.add(new BasicNameValuePair("related_contest_count", "9"));
		nvps.add(new BasicNameValuePair("include_related_creator", "false"));
		nvps.add(new BasicNameValuePair("request_sizing_chart_info", "false"));
		nvps.add(new BasicNameValuePair("_buckets", ""));
		nvps.add(new BasicNameValuePair("_experiments", ""));
		request.putExtra("nameValuePair", nvps.toArray(new NameValuePair[nvps.size()]));
		
		Spider.create(new WishSpider())
		.addRequest(request)
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