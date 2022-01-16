package com.tpjad.project.photoalbumapi.controller;

import com.tpjad.project.photoalbumapi.model.Photo;
import com.tpjad.project.photoalbumapi.model.User;
import com.tpjad.project.photoalbumapi.service.CommentService;
import com.tpjad.project.photoalbumapi.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PhotoResource {

    private String imageName;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/photo/upload", method = RequestMethod.POST)
    public String upload(HttpServletResponse response, HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(it.next());
        String fileName = multipartFile.getOriginalFilename();
        imageName=fileName;

        String path = new File("src/main/resources/static/images").getAbsolutePath()+"\\"+fileName;
        System.out.println(path);
        try {
            multipartFile.transferTo(new File(path));
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Upload Success!";
    }

    @RequestMapping(value = "/photo/add", method = RequestMethod.POST)
    public Photo addPhoto(@RequestBody Photo photo) {
        photo.setImageName(imageName);
        return photoService.save(photo);
    }

    @DeleteMapping(value = "/photo/delete/{id}")
    public ResponseEntity deletePhotoById(@PathVariable Long id) {
        System.out.println(id);
        photoService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(value="/photo/user", method = RequestMethod.POST)
    public List<Photo> getPhotosByUser (@RequestBody User user) {
        return photoService.findByUser(user);
    }

    @RequestMapping(value="/photo/photoId", method = RequestMethod.POST)
    public Photo getPhotoByPhotoId (@RequestBody Long photoId) {
        return photoService.findByPhotoId(photoId);
    }

    @RequestMapping(value = "/photo/update", method = RequestMethod.POST)
    public void updatePhoto(@RequestBody Photo photo) {
        Photo currentPhoto = photoService.findByPhotoId(photo.getPhotoId());
        currentPhoto.setLikes(photo.getLikes());
        photoService.save(currentPhoto);
    }
}
