package org.wipro.crawler.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CrawledLink {

	private String link;
	private String type;
	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
