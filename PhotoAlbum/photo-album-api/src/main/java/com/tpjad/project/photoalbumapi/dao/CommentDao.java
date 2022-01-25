package com.tpjad.project.photoalbumapi.dao;

import com.tpjad.project.photoalbumapi.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {

    Comment save(Comment comment);

    List<Comment> findByPhotoId (Long photoId);

    List<Comment> findAll();

    Comment findByCommentId(Long commentId);

    void delete(Comment comment);
}
