package org.wipro.crawler.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.wipro.crawler.model.MyLink;

public class Crawler {

	
		  private static final int MAX_PAGES_TO_SEARCH = 3000;
		  private Set<String> pagesVisited = new HashSet<String>();
		  private List<String> pagesToVisit = new LinkedList<String>();
		  private Set<String> imgsVisited = new HashSet<String>();
          

		  /**
		   * This creates crawler branches
		   * that make an HTTP request and parse the response (the web page).
		   * 
		   * @param url
		   *            - The starting point of the crawler
		   */
		  public List<MyLink> search(String url)
		  {
			  CrawlerHelper helper = new CrawlerHelper();
			  helper.setImagesVisited(imgsVisited);
		      do
		      {
		          String currentUrl;
		          
		          if(this.pagesToVisit.isEmpty())
		          {
		              currentUrl = url;
		              this.pagesVisited.add(url);
		          }
		          else
		          {
		              currentUrl = this.nextUrl();
		          }
		          helper.setLinks(pagesToVisit);
		          helper.setPagesVisited(pagesVisited);
		         // helper.setImagesVisited(imgsVisited);
		          System.out.println("No in list: "+pagesToVisit.size()+" no in set: "+pagesVisited.size());
		          
		          try {
					if(getDomainName(url).equals(getDomainName(currentUrl)))
					{
						helper.crawl(currentUrl);
					}
					  
				} catch (MalformedURLException e) {				
					System.out.println(e.getMessage());
				} 
		          
		          this.pagesToVisit=helper.getLinks();
		      } while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH && !pagesToVisit.isEmpty());
		      System.out.println("\n**pagesVisited** Visited " + this.pagesVisited.size() + " web page(s)");
		      
		      List<MyLink> mylinks = new ArrayList<MyLink>();
		      
		      for(String pageLink:pagesVisited)
		      {
		    	  MyLink myLnk = new MyLink("Link",pageLink);
		    	  mylinks.add(myLnk);
		      }
		      
		      this.imgsVisited= helper.getImagesVisited();
		 //     imgsVisited.forEach(System.out::println);
		      
		    /*  System.out.println("*********Now adding all to main set********");
		      pagesVisited.addAll(imgsVisited);
		      pagesVisited.forEach(System.out::println);*/
		     // System.out.println("pagesToVisit " + this.pagesToVisit.size() + " web page(s)");
		      for(String imgLink:imgsVisited)
		      {
		    	  MyLink myImg = new MyLink("Image",imgLink);
		    	  mylinks.add(myImg);
		      }
		      
		      return mylinks;
		      
		  }


		  /**
		   * Returns the next URL to visit (in the order that they were found). We also do a check to make
		   * sure this method doesn't return a URL that has already been visited.
		   * 
		   * @return
		   */
		  private String nextUrl()
		  {
		      String nextUrl;
		      do
		      {
		          nextUrl = this.pagesToVisit.remove(0);
		      } while(this.pagesVisited.contains(nextUrl));
		      this.pagesVisited.add(nextUrl);
		      return nextUrl;
		  }
		  
		  public static String getDomainName(String url) throws MalformedURLException{
			    if(!url.startsWith("http") && !url.startsWith("https")){
			         url = "http://" + url;
			    }        
			    URL netUrl = new URL(url);
			    String host = netUrl.getHost();
			    if(host.startsWith("www")){
			        host = host.substring("www".length()+1);
			    }
			    return host;
			}
}

