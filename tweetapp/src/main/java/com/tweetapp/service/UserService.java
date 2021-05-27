package com.tweetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.UserDao;
import com.tweetapp.mapper.UserMapper;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	public List<User> fetchAllUsers() {
		return userMapper.toUsersApi(userRepository.findAll());
	}

	public boolean insertUser(User newUser) {
	
		UserDao existingUser = userRepository.findByUsernameOrEmailAddress(newUser.getUsername(),
				newUser.getEmailAddress());
		if (existingUser == null) {
			//userDao
			UserDao userDao = userMapper.toUserDao(newUser);
			userDao.setId(userRepository.count() + 1L);
			userRepository.save(userDao);
			return true;
		}
		return false;
	}

	public boolean authenticateUser(User user) {
		UserDao loggedInUser = userRepository.findByUsername(user.getUsername());
		if (loggedInUser == null) {
			return false;
		} else if (!user.getPassword().equals(loggedInUser.getPassword())) {
			return false;
		}
		return true;
	}
	
	public List<User> searchUserByUsername(String searchText){
		List<UserDao>users=userRepository.findByUsernameContainingIgnoreCase(searchText);
		return userMapper.toUsersApi(users);
	}

}
