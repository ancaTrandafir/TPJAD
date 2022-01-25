package com.tpjad.project.photoalbumapi.service;

import com.tpjad.project.photoalbumapi.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    List<Comment> findByPhotoId (Long photoId);

    List<Comment> findAll();

    Comment findByCommentId(Long commentId);

    void  delete(long id);

}
