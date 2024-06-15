package ua.maup.service;

import ua.maup.model.User;

public interface UserService {

    User authorization(String login, String password);
    User registration(User user);
}
