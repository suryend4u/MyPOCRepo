package com.example.springbootdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdemo.topic.Topic;
import com.example.springbootdemo.topic.TopicService;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;

	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}

	@RequestMapping("/topics/{topicId}")
	public Topic getTopicbyId(@PathVariable String topicId) {
		return topicService.getTopicById(topicId);
	}

	@PostMapping("/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addOrUpdateTopic(topic);
	}

	@PutMapping("/topics/{topicId}")
	public void updateTopic(@RequestBody Topic topic) {
		// topicService.updateTopic(topic, topicId);
		topicService.addOrUpdateTopic(topic);
	}

	@DeleteMapping("/topics/{topicId}")
	public void deleteTopic(@PathVariable String topicId) {
		topicService.deleteTopic(topicId);
	}

}
