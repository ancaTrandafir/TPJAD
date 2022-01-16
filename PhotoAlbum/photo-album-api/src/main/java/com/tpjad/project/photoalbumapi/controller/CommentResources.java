package com.tpjad.project.photoalbumapi.controller;

import com.tpjad.project.photoalbumapi.model.Comment;
import com.tpjad.project.photoalbumapi.model.Photo;
import com.tpjad.project.photoalbumapi.service.CommentService;
import com.tpjad.project.photoalbumapi.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CommentResources {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public void addComment (@RequestBody Comment comment) {
        Photo photo = photoService.findByPhotoId(comment.getPhotoId());
        List<Comment> commentList=photo.getCommentList();
        comment.setPhoto(photo);
        commentService.save(comment);
    }
}
