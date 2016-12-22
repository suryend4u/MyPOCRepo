package org.wipro.crawler.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyLink {
	
	public MyLink(){}
	
	public MyLink(String type,String link)
	{
		this.type=type;
		this.link=link;
	}

	private String type;
	private String link;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
