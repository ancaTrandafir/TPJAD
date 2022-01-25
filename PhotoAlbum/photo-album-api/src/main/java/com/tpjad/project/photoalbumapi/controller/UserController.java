package com.tpjad.project.photoalbumapi.controller;

import com.tpjad.project.photoalbumapi.model.User;
import com.tpjad.project.photoalbumapi.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import static com.tpjad.project.photoalbumapi.helpers.PasswordHash.*;

@RestController
@RequestMapping("/api")
@Transactional
public class UserController {
    @Autowired
    private UserService userService;

    boolean validLogin = true;
    String token;
    String error;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Map<String, String> json) {

        String userName = json.get("username");

        try {
            if (json.get("username") == null || json.get("password") == null) {
                validLogin = false;
                throw new ServletException("Please fill in username and password");
            }

            User user = userService.findByUserName(userName);
            if (user == null) {
                validLogin = false;
                throw new ServletException("User name not found.");
            }

            String passwordFromLogin = get_SHA_512_SecurePassword(json.get("password"), user.getSalt());
            String securedPasswordFromDb = user.getHashedPassword();

            if (!passwordFromLogin.equals(securedPasswordFromDb)) {
                validLogin = false;
                throw new ServletException("Invalid login. Please check your name and password.");
            }

            token = Jwts.builder().setSubject(userName).claim("roles", "user").setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

            System.out.println("token " + token);

            return  token;

        } catch (ServletException ex) {
            System.out.println(ex.getMessage());
            error = ex.getMessage();

        }
        return error;
    }



    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.save(user);
    }

}