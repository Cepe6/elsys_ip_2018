package org.elsys.ip.servlet.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.elsys.ip.servlet.model.User;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class UserService {
	private static HashSet<User> users = new HashSet<>();

	public UserService() {
		users.add(new User(1, "admin", "admin@admin.bg", new StrongPasswordEncryptor().encryptPassword("admin")));
		users.add(new User(2, "user", "user@user.bg",new StrongPasswordEncryptor().encryptPassword("user")));
	}

	public List<User> getUsers() {
		return new ArrayList<>(users);
	}

	public User getByName(String name) {
		if (name != null) {
			return users.stream().filter(u -> name.equals(u.getName())).findFirst().orElse(null);
		} else {
			return null;
		}
	}

	public User getById(int id) {
		return users.stream().filter(u -> (id == u.getId())).findFirst().orElse(null);
	}

	public void removeUser(User user) {
		users.remove(user);
	}

	public void addUser(User user) {
		users.add(user);
	}

	public int getLastIndex() {
		List<User> gettableUsers = new ArrayList<>(users);
		return gettableUsers.get(gettableUsers.size() - 1).getId();
	}
}
