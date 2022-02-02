package com.tpjad.project.photoalbumapi.service.impl;

import com.tpjad.project.photoalbumapi.dao.RoleDao;
import com.tpjad.project.photoalbumapi.dao.UserDao;
import com.tpjad.project.photoalbumapi.model.User;
import com.tpjad.project.photoalbumapi.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static com.tpjad.project.photoalbumapi.helpers.PasswordHash.getSalt;
import static com.tpjad.project.photoalbumapi.helpers.PasswordHash.get_SHA_512_SecurePassword;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public User save(User user) throws NoSuchAlgorithmException {
        String passwordToHash = user.getPassword();
        String salt = getSalt();
        user.setSalt(salt);
        String securedPassword = get_SHA_512_SecurePassword(passwordToHash, salt);
        user.setHashedPassword(securedPassword);
        user.setRoles(Arrays.asList(roleDao.findByName("ROLE_USER")));
        return userDao.save(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

}
