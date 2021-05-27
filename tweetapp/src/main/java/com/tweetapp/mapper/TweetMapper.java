package com.tweetapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.model.Tweet;

@Mapper(componentModel = "spring")
public interface TweetMapper {

	public List<Tweet> toTweetApi(List<TweetDao> tweets);

	public TweetDao toTweetDao(Tweet tweet);

}
