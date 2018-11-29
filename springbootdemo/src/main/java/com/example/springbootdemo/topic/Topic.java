package com.example.springbootdemo.topic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topic {

	public Topic() {
	}

	public Topic(String id, String subTopic, String description) {
		super();
		this.id = id;
		this.subTopic = subTopic;
		this.description = description;
	}

	@Id
	private String id;
	private String subTopic;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubTopic() {
		return subTopic;
	}

	public void setSubTopic(String subTopic) {
		this.subTopic = subTopic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
