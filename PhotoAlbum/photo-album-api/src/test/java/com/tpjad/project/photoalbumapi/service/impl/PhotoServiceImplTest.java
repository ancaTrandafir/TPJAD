package com.tpjad.project.photoalbumapi.service.impl;

import com.tpjad.project.photoalbumapi.model.Photo;
import com.tpjad.project.photoalbumapi.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class PhotoServiceImplTest extends AbstractTestFactory {
    private Photo mockPhoto;
    private List<Photo> mockPhotos;
    private User mockUser;

    @BeforeEach
    void setUp() {
        photoService = new PhotoServiceImpl(photoDao);
    }

    @BeforeEach
    void initMockData() {
        mockPhoto = createMockPhoto("photo");
        mockPhotos = createMockPhotos();
        mockUser = createMockUser("user");
    }

    @Test
    void whenSave_thenReturnMockPhoto() {
        when(photoDao.save(any(Photo.class))).thenReturn(mockPhoto);
        Photo photo = photoService.save(mockPhoto);
        assertNotNull(photo);
        assertEquals(photo.getPhotoName(), mockPhoto.getPhotoName());
        verify(photoDao, times(1)).save(any(Photo.class));
    }

    @Test
    void whenDelete_thenReturnNothing() {
        when(photoDao.findByPhotoId(anyLong())).thenReturn(mockPhoto);
        doNothing().when(photoDao).delete(any(Photo.class));
        photoService.delete(1L);
        verify(photoDao, times(1)).findByPhotoId(anyLong());
        verify(photoDao, times(1)).delete(any(Photo.class));
    }

    @Test
    void whenFindAll_thenReturnMockPhotos() {
        when(photoDao.findAll()).thenReturn(mockPhotos);
        List<Photo> photos = photoService.findAll();
        assertNotNull(photos);
        assertEquals(2, photos.size());
        verify(photoDao, times(1)).findAll();
    }

    @Test
    void whenFindByUser_thenReturnMockPhoto() {
        when(photoDao.findByUser(any(User.class))).thenReturn(mockPhotos);
        List<Photo> photos = photoService.findByUser(mockUser);
        assertNotNull(photos);
        assertEquals(2, photos.size());
        verify(photoDao, times(1)).findByUser(any(User.class));
    }

    @Test
    void whenFindByPhotoId_thenReturnMockPhoto() {
        when(photoDao.findByPhotoId(anyLong())).thenReturn(mockPhoto);
        Photo photo = photoService.findByPhotoId(1L);
        assertNotNull(photo);
        assertEquals(mockPhoto.getPhotoName(), photo.getPhotoName());
        verify(photoDao, times(1)).findByPhotoId(anyLong());
    }
}