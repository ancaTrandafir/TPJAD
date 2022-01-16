package com.tpjad.project.photoalbumapi.service;

import com.tpjad.project.photoalbumapi.model.Photo;
import com.tpjad.project.photoalbumapi.model.User;

import java.util.List;

public interface PhotoService {
    Photo save(Photo photo);

    void  delete(long id);

    List<Photo> findAll();

    List<Photo> findByUser(User user);

    Photo findByPhotoId(Long photoId);

}
