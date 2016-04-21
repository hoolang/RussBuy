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
import us.codecraft.webmagic.selector.JsonPathSelector;

public class WishSpider implements PageProcessor {
	private final static String DOMAIN = "www.wish.com";
	
	private Site site = Site.me()
			.setDomain(DOMAIN)
			.setSleepTime(3000)
			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36")
			.addHeader("Cookie", "_xsrf=6e7058739c2b4d9885020dcaa36b9b27; sweeper_session=\"NjZmZDAzMTAtN2QwNi00MDVkLTg3YzEtYWJiNTQ1NDg0MzI4MjAxNi0wNC0yMCAwMjozNTo1MC4zNzczNTU=|1461119750|81dca46503c3f1448e29c47456a7aa31fcbb102e\"; __utma=96128154.386040617.1461119607.1461202432.1461202473.10; __utmb=96128154.2.10.1461202473; __utmc=96128154; __utmz=96128154.1461119607.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); sweeper_uuid=7df137106d514570b25ae5b9255ab8e2; bsid=1e3dcf29c2d34479a74d7d34dac1c1c9")
			.addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
			.addHeader("Accept-Encoding", "gzip, deflate")
			.addHeader("Accept-Language", "zh-CN,zh;q=0.8")
			.addHeader("Accept-Cache-Control","max-age=0")
			.addHeader("Connection","keep-alive")
			.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
			.addHeader("X-Requested-With","XMLHttpRequest")
			.addHeader("X-XSRFToken","6e7058739c2b4d9885020dcaa36b9b27")
			;

	private ProductsService productService;
	@Override
	public void process(Page page) {

		//page.putField("title", new JsonPathSelector("$.data.title").select(page.getRawText()));
		//page.putField("url", page.getUrl());
		page.putField("title", new JsonPathSelector("$.data.contest.name").select(page.getRawText()));
		page.putField("price", new JsonPathSelector("$.data.contest.commerce_product_info.variations[*].price").selectList(page.getRawText()));
		page.putField("size", new JsonPathSelector("$.data.contest.commerce_product_info.variations[*].size").selectList(page.getRawText()));
		page.putField("color", new JsonPathSelector("$.data.contest.commerce_product_info.variations[*].color").selectList(page.getRawText()));
		page.putField("description", new JsonPathSelector("$.data.contest.description").select(page.getRawText()));
		page.putField("tags", new JsonPathSelector("$.data.contest.tags[*].name").selectList(page.getRawText()));
		page.putField("images",	new JsonPathSelector("$.data.contest.extra_photo_urls").selectList(page.getRawText()));
		
		System.out.println("title=====>"+page.getResultItems().get("title").toString());
		System.out.println("size=====>"+page.getResultItems().get("size").toString());
		System.out.println("color=====>"+page.getResultItems().get("color").toString());
		System.out.println("price=====>"+page.getResultItems().get("price").toString());
		System.out.println("description=====>"+page.getResultItems().get("description").toString());
		System.out.println("tags=====>"+page.getResultItems().get("tags").toString());
		System.out.println("images=====>"+page.getResultItems().get("images").toString());
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
		Request[] requests = new Request[urls.length];
		for(int i = 0; i<urls.length; i++){
			Request request = new Request(urls[i]);
			request.setMethod("post");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("cid", "561cc758627ec95e0297f43a"));
			nvps.add(new BasicNameValuePair("related_contest_count", "9"));
			nvps.add(new BasicNameValuePair("include_related_creator", "false"));
			nvps.add(new BasicNameValuePair("request_sizing_chart_info", "false"));
			nvps.add(new BasicNameValuePair("_buckets", ""));
			nvps.add(new BasicNameValuePair("_experiments", ""));
			request.putExtra("nameValuePair", nvps.toArray(new NameValuePair[nvps.size()]));
			requests[i] = request;
		}
		Spider.create(new WishSpider()).addPipeline(new SimplePipeline(productService)).addRequest(requests).run();
		return this;
	}
	
	public static void main(String[] args) {
		Request request = new Request("https://www.wish.com/api/product/get");
		request.setMethod("post");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cid", "561cc758627ec95e0297f43a"));
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