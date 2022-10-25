package com.example.demo.service;

import com.example.demo.domain.entity.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> findAllPhotos();

    Photo savePhoto(Photo photo);

    void removePhoto(Long id);

    Photo findPhotoById(Long id);

    void updatePhoto(Photo photo);
}
