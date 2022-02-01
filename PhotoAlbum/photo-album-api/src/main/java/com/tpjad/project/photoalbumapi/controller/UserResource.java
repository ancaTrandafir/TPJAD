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

    @GetMapping("/user/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/user/userName")
    public User findByUserName(@RequestBody String userName) {
        return userService.findByUserName(userName);
    }

    @PostMapping("/user/update")
    public User updateUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.save(user);
    }

    @DeleteMapping("/user/delete")
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }

}
