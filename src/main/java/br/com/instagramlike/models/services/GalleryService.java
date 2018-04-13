package br.com.instagramlike.models.services;

import br.com.instagramlike.controllers.GalleryResource;
import br.com.instagramlike.models.domains.Photo;
import br.com.instagramlike.models.repositories.PhotoRepository;
import br.com.instagramlike.utils.GalleryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class GalleryService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private GalleryStore galleryStore;


    public Photo save(Photo photo) throws IOException, NoSuchAlgorithmException {

        galleryStore.store(photo);

        return photo;
    }


}
