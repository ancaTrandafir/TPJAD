package com.tpjad.project.photoalbumapi.controller;

import com.tpjad.project.photoalbumapi.model.User;
import com.tpjad.project.photoalbumapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest")
@Transactional
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/users", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/user/userName", method = RequestMethod.POST)
    public User findByUserName(@RequestBody String userName) {

        return userService.findByUserName(userName);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.save(user);
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }

}
