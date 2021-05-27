package com.tweetapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

import com.tweetapp.dao.UserDao;
import com.tweetapp.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	public List<User> toUsersApi(List<UserDao> users);

	public UserDao toUserDao(User user);

}
