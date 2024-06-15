package ua.maup.dao;

import ua.maup.model.User;

public interface UsersDao {
    User getUser(String login, String password);
    User addUser(User user);
}
