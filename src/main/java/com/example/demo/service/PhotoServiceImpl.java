package com.example.demo.service;

import com.example.demo.domain.entity.Photo;
import com.example.demo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    @Override
    public List<Photo> findAllPhotos() {
        return photoRepository.findAll();
    }

    @Override
    @Transactional
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void removePhoto(Long id) {
        photoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Photo findPhotoById(Long id) {

        Optional<Photo> optionalPhoto = photoRepository.findById(id);

        //regarding optional, you have different options to process it
        //1. Throw exception if not present
        Photo photo = optionalPhoto.orElseThrow(NoSuchElementException::new);

        //2. Get default value if not present
        Photo photoGetDefault = optionalPhoto.orElse(new Photo());

        //3. Get default value if not present using supplier,
        // This will invoke creation of the default value just if object is not present, instead of orElse();
        Photo photoGetDefaultSupplier = optionalPhoto.orElseGet(Photo::new);

        //4. If you need to do some action on object instead of returning use ifPresent(Consumer)
        optionalPhoto.ifPresent(System.out::println);

        //5. PLEASE do not use this
         /*
         if (!optionalPhoto.isPresent()){
            throw new NoSuchElementException("Something wrong");
            }
        */
        //guys in Oracle create couple methods for you please use it

        return photo;
    }

    //If you want to update your objects using dirtychecking mechanism add @Transactional
    // find object
    //        Photo photoToUpdate = photoRepository.findById(photo.getId()).orElseThrow();
    // set new values for your fields and do not invoke method save
    public void updatePhoto(Photo photo) {
        photoRepository.save(photo);
        /*
        in method
        * if (entityInformation.isNew(entity)) {
			em.persist(entity);
			return entity;
		} else {
			return em.merge(entity);
		}*/
    }
}
