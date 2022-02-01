package com.tpjad.project.photoalbumapi.controller;

import com.tpjad.project.photoalbumapi.model.Photo;
import com.tpjad.project.photoalbumapi.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
@CrossOrigin(origins = "http://localhost:8888")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/allPhotos")
    public List<Photo> getAllPhotos () {
        return photoService.findAll();
    }

}
