package com.tweetapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tweetapp.dao.ReplyDao;
import com.tweetapp.model.Reply;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

	
	public List<Reply> toReplyApi(List<ReplyDao> replyDao);

	public ReplyDao toReplyDao(Reply body);
}
