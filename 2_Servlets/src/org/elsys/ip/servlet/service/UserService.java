package org.elsys.ip.servlet.service;

import java.util.ArrayList;
import java.util.List;

import org.elsys.ip.servlet.model.User;

public class UserService {
	private static List<User> users = new ArrayList<>();

	public UserService() {
		users.add(new User(1, "admin", "admin@admin.bg"));
		users.add(new User(2, "user", "user@user.bg"));
	}

	public List<User> getUsers() {
		return users;
	}

	public User getByName(String name) {
		System.out.println(name);
		if (name != null) {
			return users.stream().filter(u -> name.equals(u.getName())).findFirst().orElse(null);
		} else {
			return null;
		}
	}
}
