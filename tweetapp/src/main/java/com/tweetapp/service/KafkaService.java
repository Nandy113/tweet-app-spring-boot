package com.tweetapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.model.Tweet;

@Service
public class KafkaService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private TweetService tweetService;

	public void sendMessage(String msg, String topicName) {
		kafkaTemplate.send(topicName, msg);
	}

	@KafkaListener(topics = "tweet", groupId = "tweetapp")
	public void listen(String message) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Tweet tweet = mapper.readValue(message, Tweet.class);
			tweetService.insertTweet(tweet);

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
