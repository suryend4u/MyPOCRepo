package com.example.springbootdemo.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	/*
	 * private List<Topic> topics = new ArrayList<>(Arrays.asList(
	 * 
	 * new Topic("Spring", "Aop", "discuss Spring AOP"), new Topic("Java",
	 * "jdk8", "discuss Java8"), new Topic("SQL", "query", "discuss SQL qs"),
	 * new Topic("Docker", "Swarm", "discuss Docker ")));
	 */

	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<Topic>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}

	public Topic getTopicById(String topicId) {

		return topicRepository.findById(topicId).get();
	}

	public void addOrUpdateTopic(Topic topic) {
		topicRepository.save(topic);
	}

	/*
	 * public void updateTopic(Topic topic) {
	 * 
	 * 
	 * for (int i = 0; i < topics.size(); i++) { Topic t = topics.get(i); if
	 * (t.getId().equals(topicId)) { topics.set(i, topic); return; } }
	 * 
	 * topicRepository.save(topic);
	 * 
	 * }
	 */
	public void deleteTopic(String topicId) {
		/*
		 * for (Topic t : topics) { if (t.getTopic().equals(topicId)) {
		 * topics.remove(t); break; } }
		 */
		topicRepository.deleteById(topicId);

	}
}
