package com.temzu.market.services;

import com.temzu.market.model.entities.User;

public interface UserService {
    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    User saveUser(User user);
}
