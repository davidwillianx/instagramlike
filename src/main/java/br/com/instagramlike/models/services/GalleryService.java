package br.com.instagramlike.models.services;

import br.com.instagramlike.models.domains.Photo;
import br.com.instagramlike.models.domains.User;
import br.com.instagramlike.models.repositories.PhotoRepository;
import br.com.instagramlike.utils.GalleryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class GalleryService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private GalleryStore galleryStore;


    public Photo save(Photo photo) throws IOException, NoSuchAlgorithmException {

       String reffName = galleryStore.store(photo);
       photo.setReffName(reffName);

       photoRepository.save(photo);

       return photo;
    }

    public boolean isPhotoExist(String photoReff, User owner){
      Optional<Photo> photoFound = photoRepository.findByReffNameAndOwner(photoReff, owner);
      return photoFound.isPresent();
    }

    public Page<Photo> searchGallery(int page, int size, User owner){
        return photoRepository.findByOwner(new PageRequest(page, size), owner);
    }



}
