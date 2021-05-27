package com.tweetapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.api.TweetsApi;
import com.tweetapp.model.Reply;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.service.KafkaService;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;
import com.tweetapp.utils.SecurityUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1.0")
public class TweetController implements TweetsApi {
	@Autowired
	private TweetService tweetService;
	@Autowired
	private UserService userService;
	@Autowired
	private KafkaService kafkaService;

	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> insertTweet(@PathVariable("username") String username, @RequestBody Tweet body) {
		ObjectMapper mapper = new ObjectMapper();
		body.setPostedBy(username);
		try {
			kafkaService.sendMessage(mapper.writeValueAsString(body), "tweet");
			kafkaService.listen(mapper.writeValueAsString(body));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> createUser(@RequestBody User user) {
		boolean isCreated = userService.insertUser(user);
		if (isCreated) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Void> updateToTweet(@RequestParam("id") Long id, @PathVariable("username") String username,
			@RequestBody Tweet body) {
		tweetService.updateTweet(id, body, username);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Void> authenticateUser(@RequestHeader(value = "Authorization") String authorization) {
		User user = SecurityUtils.getUser(authorization);
		boolean isAuthenticated = userService.authenticateUser(user);
		if (isAuthenticated) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@Override
	public ResponseEntity<List<Tweet>> getAllTweets() {
		return new ResponseEntity<List<Tweet>>(tweetService.fetchAllTweets(), HttpStatus.OK);
	}

	public ResponseEntity<List<User>> searchByUsername(
			@Valid @RequestParam(value = "searchText", required = true) String searchText) {
		return new ResponseEntity<>(userService.searchUserByUsername(searchText), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> deleteTweet(@RequestParam("id") Long id, @PathVariable("username") String username) {
		tweetService.deleteTweet(id, username);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	 @Override 
	public ResponseEntity<Void> insertReply(@PathVariable("username") String username,
			@RequestBody Reply body, @RequestParam("id") Long id) {
		tweetService.replyToTweet(body,username, id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<Tweet>> getAllTweetsOfUser(@PathVariable("username") String username) {
		return new ResponseEntity<List<Tweet>>(tweetService.fetchAllTweetsOfUser(username), HttpStatus.OK);		
	}

}
