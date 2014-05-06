package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Comment;

public interface CommentService {

	List<Comment> findAll();
	
	Comment findById(Long id);
	
}
