package com.tpjad.project.photoalbumapi.service.impl;

import com.tpjad.project.photoalbumapi.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CommentServiceImplTest extends AbstractTestFactory {
    private Comment mockComment;
    private List<Comment> mockComments;

    @BeforeEach
    void setUp() {
        commentService = new CommentServiceImpl(commentDao);
    }

    @BeforeEach
    void initMockData() {
        mockComment = createMockComment("comm1");
        mockComments = createMockComments();
    }

    @Test
    void whenSave_thenReturnMockComment() {
        when(commentDao.save(any(Comment.class))).thenReturn(mockComment);
        Comment comment = commentService.save(mockComment);
        assertNotNull(comment);
        assertEquals("comm1", comment.getContent());
        assertEquals(1L, comment.getCommentId());
        assertEquals("username", comment.getUserName());
        verify(commentDao, times(1)).save(any(Comment.class));
    }

    @Test
    void whenFindByPhotoId_thenReturnMockComments() {
        when(commentDao.findByPhotoId(anyLong())).thenReturn(mockComments);
        List<Comment> comments = commentService.findByPhotoId(1L);
        assertNotNull(comments);
        assertEquals(2, comments.size());
        verify(commentDao, times(1)).findByPhotoId(anyLong());
    }

    @Test
    void whenFindAll_thenReturnMockComments() {
        when(commentDao.findAll()).thenReturn(mockComments);
        List<Comment> comments = commentService.findAll();
        assertNotNull(comments);
        assertEquals(2, comments.size());
        verify(commentDao, times(1)).findAll();
    }

    @Test
    void whenFindByCommentId_thenReturnMockComment() {
        when(commentDao.findByCommentId(anyLong())).thenReturn(mockComment);
        Comment comment = commentService.findByCommentId(1L);
        assertNotNull(comment);
        assertEquals("comm1", comment.getContent());
        verify(commentDao, times(1)).findByCommentId(anyLong());
    }

    @Test
    void whenDelete_thenReturnNothing() {
        when(commentDao.findByCommentId(anyLong())).thenReturn(mockComment);
        doNothing().when(commentDao).delete(any(Comment.class));
        commentService.delete(1L);
        verify(commentDao, times(1)).findByCommentId(anyLong());
        verify(commentDao, times(1)).delete(any(Comment.class));
    }
}