package com.tpjad.project.photoalbumapi.service;

import com.tpjad.project.photoalbumapi.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findByUserName(String userName);

    User save(User user);

}
