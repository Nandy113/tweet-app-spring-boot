package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.dao.UserDao;

@Repository
public interface UserRepository extends MongoRepository<UserDao, Long> {

	public UserDao findByUsernameOrEmailAddress(String username, String emailAddress);

	public UserDao findByUsername(String username);
	
	public List<UserDao> findByUsernameContainingIgnoreCase(String searchText);
}
