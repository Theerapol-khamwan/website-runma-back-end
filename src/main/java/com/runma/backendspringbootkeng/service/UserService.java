package com.runma.backendspringbootkeng.service;

import com.runma.backendspringbootkeng.entity.User;

public interface UserService {

    public User findById(Integer userId);

    public User save(User theUser);

    public User update(Integer userId, User theUser);

    public User findByEmail(String userEmail);

}
