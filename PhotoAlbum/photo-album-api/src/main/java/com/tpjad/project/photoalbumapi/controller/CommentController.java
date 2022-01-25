package com.tpjad.project.photoalbumapi.controller;

import com.tpjad.project.photoalbumapi.model.Comment;
import com.tpjad.project.photoalbumapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/allComments", method = RequestMethod.GET)
    public List<Comment> getAllComments () {
        return commentService.findAll();
    }

}
