package org.wipro.crawler.service;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerHelper
{
    // We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
      private Set<String> pagesVisited ;
      private Set<String> imagesVisited ;
	  


	private List<String> links;


    /**
     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
     * up all the links on the page.
     * 
     * @param url
     *            - The URL to visit
     */
    public void crawl(String url)
    {
      //  if(url.contains("wiprodigital.com")){
    	try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection
            								.timeout(3000)
            								.proxy("kdc-proxy.wipro.com", 8080)
            								.get();
            if(connection.response().statusCode() == 200) // 200 is the HTTP OK status code
                                                          // indicating that everything is great.
            {
                System.out.println("\n**Visiting** Received web page at " + url);
            }
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("**Failure** Retrieved something other than HTML");
                return;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            int count=0;
            for(Element link : linksOnPage)
            {
                String absUrl = link.absUrl("href");
                
            	if(!links.contains(absUrl)&& !pagesVisited.contains(absUrl)){
            		this.links.add(link.absUrl("href"));
            		count++;
            	}           	
            }
            
          //  links.forEach(System.out::println);
            System.out.println("Number of pages added this time."+count);
            
            //Images
            Elements imgsOnPage = htmlDocument.select("img[src]");
            System.out.println("Found (" + imgsOnPage.size() + ") imgs");
            for(Element linkImgs : imgsOnPage)
            {
                String absUrl = linkImgs.absUrl("src");
                this.imagesVisited.add(absUrl);          	
            }
            
            return;
        }
    
        catch(IOException ioe)
        {
            System.out.println("***IOException "+ioe.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("***Exception "+e.getMessage());
        }
        
    }


    public List<String> getLinks()
    {
        return this.links;
    }


	public Set<String> getPagesVisited() {
		return pagesVisited;
	}


	public void setPagesVisited(Set<String> pagesVisited) {
		this.pagesVisited = pagesVisited;
	}


	public void setLinks(List<String> links) {
		this.links = links;
	}
	
	public Set<String> getImagesVisited() {
		return imagesVisited;
	}


	public void setImagesVisited(Set<String> imagesVisited) {
		this.imagesVisited = imagesVisited;
	}
	
}
