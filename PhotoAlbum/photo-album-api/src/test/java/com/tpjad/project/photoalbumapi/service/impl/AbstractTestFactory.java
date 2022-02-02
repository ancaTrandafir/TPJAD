package com.tpjad.project.photoalbumapi.service.impl;

import com.tpjad.project.photoalbumapi.dao.CommentDao;
import com.tpjad.project.photoalbumapi.dao.PhotoDao;
import com.tpjad.project.photoalbumapi.dao.RoleDao;
import com.tpjad.project.photoalbumapi.dao.UserDao;
import com.tpjad.project.photoalbumapi.model.Comment;
import com.tpjad.project.photoalbumapi.model.Photo;
import com.tpjad.project.photoalbumapi.model.Role;
import com.tpjad.project.photoalbumapi.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class AbstractTestFactory {
    @Mock
    protected CommentDao commentDao;

    @Mock
    protected PhotoDao photoDao;

    @Mock
    protected UserDao userDao;

    @Mock
    protected RoleDao roleDao;

    @InjectMocks
    protected CommentServiceImpl commentService;

    @InjectMocks
    protected PhotoServiceImpl photoService;

    @InjectMocks
    protected UserServiceImpl userService;

    @BeforeEach
    void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    protected Comment createMockComment(String comm) {
        Comment comment = new Comment();
        comment.setContent(comm);
        comment.setCommentId(1L);
        comment.setUserName("username");
        comment.setPhoto(createMockPhoto("name"));
        return comment;
    }

    protected Photo createMockPhoto(String name) {
        Photo photo = new Photo();
        photo.setPhotoId(1L);
        photo.setPhotoName(name);
        photo.setTitle("title");
        photo.setDescription("description");
        photo.setImageName("image");
        return photo;
    }

    protected List<Comment> createMockComments() {
        Comment c1 = createMockComment("com1");
        Comment c2 = createMockComment("com2");
        return Arrays.asList(c1, c2);
    }

    protected List<Photo> createMockPhotos() {
        Photo p1 = createMockPhoto("photo1");
        Photo p2 = createMockPhoto("photo2");
        return Arrays.asList(p1, p2);
    }

    protected User createMockUser(String user) {
        User usr = new User();
        usr.setFirstName(user);
        usr.setUserName(user);
        usr.setLastName(user);
        usr.setPassword(user);
        usr.setPhotoList(createMockPhotos());
        usr.setLikedPhotoList(createMockPhotos());
        return usr;
    }

    protected List<User> createMockUsers() {
        User u1 = createMockUser("user1");
        User u2 = createMockUser("user2");
        return Arrays.asList(u1, u2);
    }

    protected Role createMockRole(String rol) {
        Role role = new Role();
        role.setRoleId(1L);
        role.setName(rol);
        role.setUsers(createMockUsers());
        return role;
    }

    protected List<Role> createMockRoles() {
        Role r1 = createMockRole("role1");
        Role r2 = createMockRole("role2");
        return Arrays.asList(r1, r2);
    }
}
