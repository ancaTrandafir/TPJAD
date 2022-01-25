package com.tpjad.project.photoalbumapi.helpers;

import com.tpjad.project.photoalbumapi.dao.RoleDao;
import com.tpjad.project.photoalbumapi.model.Role;
import com.tpjad.project.photoalbumapi.model.User;
import com.tpjad.project.photoalbumapi.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.tpjad.project.photoalbumapi.helpers.PasswordHash.getSalt;
import static com.tpjad.project.photoalbumapi.helpers.PasswordHash.get_SHA_512_SecurePassword;

@Component
public class SetupDataLoader implements CommandLineRunner {

    boolean alreadySetup = false;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public void seedDb() throws NoSuchAlgorithmException {

        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        Role adminRole = roleDao.findByName("ROLE_ADMIN");
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setUserName("admin");
        String saltAdmin = getSalt();
        admin.setSalt(saltAdmin);
        String securedPassword = get_SHA_512_SecurePassword("admin", saltAdmin);
        admin.setHashedPassword(securedPassword);
        admin.setRoles(Arrays.asList(adminRole));
        userDao.save(admin);

        User user1 = new User();
        user1.setFirstName("Anca");
        user1.setLastName("Trandafir");
        user1.setUserName("anca");
        String saltUser1 = getSalt();
        user1.setSalt(saltUser1);
        String securedPassword1 = get_SHA_512_SecurePassword("anca", saltUser1);
        user1.setHashedPassword(securedPassword1);
        System.out.println("passwordAnca "+ securedPassword1);
        userDao.save(user1);

        User user2 = new User();
        user2.setFirstName("Alex");
        user2.setLastName("Sabou");
        user2.setUserName("alex");
        String saltUser2 = getSalt();
        user2.setSalt(saltUser2);
        String securedPassword2 = get_SHA_512_SecurePassword("alex", saltUser2);
        user2.setHashedPassword(securedPassword2);
        userDao.save(user2);

        alreadySetup = true;
    }

    Role createRoleIfNotFound(String name) {

        Role role = roleDao.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleDao.save(role);
        }
        return role;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDb();
    }

}