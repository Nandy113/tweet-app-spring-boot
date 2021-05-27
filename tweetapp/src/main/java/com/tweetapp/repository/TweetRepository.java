package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.dao.UserDao;

@Repository
public interface TweetRepository extends MongoRepository<TweetDao, Long> {
	
	public List<TweetDao> findByPostedBy(String username);

}
