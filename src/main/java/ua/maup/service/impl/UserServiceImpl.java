package ua.maup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.maup.dao.UsersDao;
import ua.maup.service.UserService;
import ua.maup.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public User authorization(String login, String password) {
        return usersDao.getUser(login, password);
    }

    @Override
    public User registration(User user) {
        return usersDao.addUser(user);
    }

}

