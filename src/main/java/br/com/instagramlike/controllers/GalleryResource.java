package br.com.instagramlike.controllers;

import br.com.instagramlike.models.domains.Photo;
import br.com.instagramlike.models.domains.User;
import br.com.instagramlike.models.services.AuthenticatedService;
import br.com.instagramlike.models.services.GalleryService;
import br.com.instagramlike.utils.UserAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/gallery")
public class GalleryResource {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private AuthenticatedService authenticatedService;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity add(@RequestPart("file") MultipartFile file) throws IOException {

        User user = authenticatedService.user();
        Photo photo = Photo.builder(file, user);

        galleryService.save(photo);

        return new ResponseEntity(photo, HttpStatus.OK);
    }




}
