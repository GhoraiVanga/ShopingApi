package me.suman.shopapi.service;

import java.util.Collection;

import me.suman.shopapi.entity.User;

public interface UserService {
    User findOne(String email);

    Collection<User> findByRole(String role);

    User save(User user);

    User update(User user);
}
