package com.tweetapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.ReplyDao;
import com.tweetapp.dao.TweetDao;
import com.tweetapp.mapper.ReplyMapper;
import com.tweetapp.mapper.TweetMapper;
import com.tweetapp.model.Reply;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.ReplyRepository;
import com.tweetapp.repository.TweetRepository;

@Service
public class TweetService {

	@Autowired
	private TweetRepository tweetRepository;
	@Autowired
	private TweetMapper tweetMapper;
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private ReplyRepository replyRepository;

	public List<Tweet> fetchAllTweets() {
		List<Tweet> tweets = tweetMapper.toTweetApi(tweetRepository.findAll());
		for (Tweet tweet : tweets) {
			tweet.setReplies(replyMapper.toReplyApi(replyRepository.findByRepliedTo(tweet.getId())));
		}
		return tweets;
	}

	
	public List<Tweet> fetchAllTweetsOfUser(String username) {
		List<Tweet> tweets = tweetMapper.toTweetApi(tweetRepository.findByPostedBy(username));
		for (Tweet tweet : tweets) {
			tweet.setReplies(replyMapper.toReplyApi(replyRepository.findByRepliedTo(tweet.getId())));
		}
		return tweets;
	}
	
	public boolean deleteTweet(Long id, String username) {
		Optional<TweetDao> tweetDao = tweetRepository.findById(id);
		if (tweetDao.isPresent() && tweetDao.get().getPostedBy().equals(username)) {
			replyRepository.deleteByRepliedTo(id);
			tweetRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public void updateTweet(Long id, Tweet body, String username) {
		body.setId(id);
		body.setPostedBy(username);
		body.setPostedAt(LocalDateTime.now().toString());
		tweetRepository.save(tweetMapper.toTweetDao(body));
	}

	public void insertTweet(Tweet tweet) {
		TweetDao tweetDao = tweetMapper.toTweetDao(tweet);
		tweetDao.setPostedAt(LocalDateTime.now().toString());
		tweetDao.setId(tweetRepository.count() + 1L);
		System.out.println(tweet.toString());
		tweetRepository.save(tweetDao);
	}

	public void replyToTweet(Reply body, String username, Long id) {
		body.setRepliedAt(LocalDateTime.now().toString());
		body.setRepliedBy(username);
		body.setRepliedTo(id);
		ReplyDao reply = replyMapper.toReplyDao(body);
		reply.setId(replyRepository.count() + 1L);
		replyRepository.save(reply);

	}
}
