package org.wipro.crawler.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.wipro.crawler.model.MyLink;
import org.wipro.crawler.service.Crawler;



@Path("testcrawl")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class CrawlerResource {

	
	
	@GET
	
	@Path("/{url}")
	public String testMethod(@PathParam ("url") String url){
		
		System.out.println("*** @PathParamURL is *** " + url);
		return url;
	}
	

	@GET	
	public List<MyLink> testQueryParam(@QueryParam ("url") String url){
		
		System.out.println("***@QueryParam URL is *** " + url);
		Crawler crawler = new Crawler();
		List<MyLink> pagesVisited =crawler.search(url);
		return pagesVisited;
		//return url;
	}
}
