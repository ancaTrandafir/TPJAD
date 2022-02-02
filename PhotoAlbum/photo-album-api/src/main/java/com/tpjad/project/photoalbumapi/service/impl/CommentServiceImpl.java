package com.tpjad.project.photoalbumapi.service.impl;

import com.tpjad.project.photoalbumapi.dao.CommentDao;
import com.tpjad.project.photoalbumapi.model.Comment;
import com.tpjad.project.photoalbumapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    public List<Comment> findByPhotoId (Long photoId) {
        return commentDao.findByPhotoId(photoId);
    }

    public List<Comment> findAll() {
            return commentDao.findAll();
    }

    public Comment findByCommentId(Long commentId) {
        return commentDao.findByCommentId(commentId);
    }

    public void delete(long id){
        Comment commentToDelete = findByCommentId(id);
        commentDao.delete(commentToDelete);
    }

}
