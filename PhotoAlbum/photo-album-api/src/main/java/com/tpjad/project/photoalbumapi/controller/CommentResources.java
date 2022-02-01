package com.tpjad.project.photoalbumapi.controller;

import com.tpjad.project.photoalbumapi.model.Comment;
import com.tpjad.project.photoalbumapi.model.Photo;
import com.tpjad.project.photoalbumapi.service.CommentService;
import com.tpjad.project.photoalbumapi.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CommentResources {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/add")
    public void addComment (@RequestBody Comment comment) {
        Photo photo = photoService.findByPhotoId(comment.getPhotoId());
        List<Comment> commentList=photo.getCommentList();
        comment.setPhoto(photo);
        commentService.save(comment);
    }

    @DeleteMapping(value = "/comment/delete/{id}")
    public ResponseEntity deleteCommentById(@PathVariable Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
