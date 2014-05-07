package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.People;
import com.database.data.domain.User;

public interface UserService {

	List<User> findAll();

	User findById(Long id);

	Long addUser(User user);

	Long addUser(String login, String hashPasswd, String role, People people);

	User getUser(String login, String hashPasswd);
}
