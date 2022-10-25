package com.example.demo.service;

import com.example.demo.domain.entity.Comment;

public interface CommentService {
    void addCommentToPhoto(Comment comment, Long photoId);
}
