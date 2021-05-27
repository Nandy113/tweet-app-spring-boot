package com.tweetapp.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "replies")
public class ReplyDao {
	@Id
	private Long id;

	private String replyMessage;

	private String repliedBy;

	private Long repliedTo;

	private String repliedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}

	public String getRepliedBy() {
		return repliedBy;
	}

	public void setRepliedBy(String repliedBy) {
		this.repliedBy = repliedBy;
	}

	public Long getRepliedTo() {
		return repliedTo;
	}

	public void setRepliedTo(Long repliedTo) {
		this.repliedTo = repliedTo;
	}

	public String getRepliedAt() {
		return repliedAt;
	}

	public void setRepliedAt(String repliedAt) {
		this.repliedAt = repliedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((repliedAt == null) ? 0 : repliedAt.hashCode());
		result = prime * result + ((repliedBy == null) ? 0 : repliedBy.hashCode());
		result = prime * result + ((repliedTo == null) ? 0 : repliedTo.hashCode());
		result = prime * result + ((replyMessage == null) ? 0 : replyMessage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReplyDao other = (ReplyDao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (repliedAt == null) {
			if (other.repliedAt != null)
				return false;
		} else if (!repliedAt.equals(other.repliedAt))
			return false;
		if (repliedBy == null) {
			if (other.repliedBy != null)
				return false;
		} else if (!repliedBy.equals(other.repliedBy))
			return false;
		if (repliedTo == null) {
			if (other.repliedTo != null)
				return false;
		} else if (!repliedTo.equals(other.repliedTo))
			return false;
		if (replyMessage == null) {
			if (other.replyMessage != null)
				return false;
		} else if (!replyMessage.equals(other.replyMessage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReplyDao [id=" + id + ", replyMessage=" + replyMessage + ", repliedBy=" + repliedBy + ", repliedTo="
				+ repliedTo + ", repliedAt=" + repliedAt + "]";
	}
	
	
	
	

}
