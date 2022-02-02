package com.tpjad.project.photoalbumapi.service.impl;

import com.tpjad.project.photoalbumapi.dao.PhotoDao;
import com.tpjad.project.photoalbumapi.model.Photo;
import com.tpjad.project.photoalbumapi.model.User;
import com.tpjad.project.photoalbumapi.service.PhotoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoDao photoDao;

    public PhotoServiceImpl(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    public Photo save(Photo photo) {
        return photoDao.save(photo);
    }

    public void delete(long id){
        Photo photoToDelete = findByPhotoId(id);
        photoDao.delete(photoToDelete);
    }

    public List<Photo> findAll() {
        return photoDao.findAll();
    }

    public List<Photo> findByUser(User user) {
        return photoDao.findByUser(user);
    }

    public Photo findByPhotoId(Long photoId) {
        return photoDao.findByPhotoId(photoId);
    }
}
