package com.example.demo.service;

import com.example.demo.domain.entity.Comment;
import com.example.demo.domain.entity.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PhotoService photoService;

    @Override
    @Transactional
    public void addCommentToPhoto(Comment comment, Long photoId) {
        Photo photo = photoService.findPhotoById(photoId);
        photo.addComment(comment);
        // You don't need to call save method because dirty-checking will do update for you in transaction
    }
}
