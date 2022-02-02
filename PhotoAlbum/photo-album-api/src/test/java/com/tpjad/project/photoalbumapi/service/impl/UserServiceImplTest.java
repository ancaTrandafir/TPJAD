package com.tpjad.project.photoalbumapi.service.impl;

import com.tpjad.project.photoalbumapi.model.Role;
import com.tpjad.project.photoalbumapi.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest extends AbstractTestFactory {

    private User mockUser;
    private List<User> mockUsers;
    private Role mockRole;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userDao, roleDao);
    }

    @BeforeEach
    void initMockData() {
        mockUser = createMockUser("user");
        mockUsers = createMockUsers();
        mockRole = createMockRole("role");
    }

    @Test
    void whenFindAllUsers_thenReturnMockUsers() {
        when(userDao.findAll()).thenReturn(mockUsers);
        List<User> users = userService.findAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userDao, times(1)).findAll();
    }

    @Test
    void whenFindByUserName_thenReturnMockUser() {
        when(userDao.findByUserName(anyString())).thenReturn(mockUser);
        User user = userService.findByUserName("username");
        assertNotNull(user);
        assertEquals(mockUser.getFirstName(), user.getFirstName());
        assertEquals(mockUser.getLastName(), user.getLastName());
        verify(userDao, times(1)).findByUserName(anyString());
    }

    @Test
    void whenSave_thenReturnMockUser() {
        when(roleDao.findByName(anyString())).thenReturn(mockRole);
        when(userDao.save(any(User.class))).thenReturn(mockUser);
        User user = null;
        try {
            user = userService.save(mockUser);
        } catch (Exception e) {
            assertNull(e);
        }
        assertNotNull(user);
        assertEquals(mockUser.getLastName(), user.getLastName());
        verify(roleDao, times(1)).findByName(anyString());
        verify(userDao, times(1)).save(any(User.class));
    }

    @Test
    void whenDelete_thenReturnNothing() {
        doNothing().when(userDao).delete(any(User.class));
        userService.delete(mockUser);
        verify(userDao, times(1)).delete(any(User.class));
    }
}