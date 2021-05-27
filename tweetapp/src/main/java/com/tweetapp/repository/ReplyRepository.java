package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.dao.ReplyDao;

@Repository
public interface ReplyRepository extends MongoRepository<ReplyDao, Long> {

	public List<ReplyDao> findByRepliedTo(Long id);

	public void deleteByRepliedTo(Long id);

}
