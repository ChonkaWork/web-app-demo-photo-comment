package com.example.demo.controller;

import com.example.demo.domain.entity.Photo;
import com.example.demo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor //creates constructor with all final fields, don't need to write your own and use @Autowire
public class PhotoController {
    private final PhotoService photoService;

    @GetMapping
    public List<Photo> getAllPhoto() { //return DTO instead, I am using it just to avoid boilerplate code
        return photoService.findAllPhotos();
    }

    @PostMapping // As you see you don't need to write "/create". You have enough information to create HTTP request:
    // method: POST, url: http://host:port/photos/
    public Photo addPhoto(@RequestBody Photo photo) {
        return photoService.savePhoto(photo);
    }

    @DeleteMapping("/{id}")
    public void removePhoto(@PathVariable Long id) {
        photoService.removePhoto(id);
    }

    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable Long id) {
        return photoService.findPhotoById(id);
    }

    @PutMapping
    public void updatePhoto(@RequestBody Photo photo) {
        photoService.updatePhoto(photo);
    }
}
