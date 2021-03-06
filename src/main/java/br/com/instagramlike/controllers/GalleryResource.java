package br.com.instagramlike.controllers;

import br.com.instagramlike.errors.PhotoNotFoundException;
import br.com.instagramlike.models.domains.Photo;
import br.com.instagramlike.models.domains.User;
import br.com.instagramlike.models.domains.UserAuth;
import br.com.instagramlike.models.services.AuthenticatedService;
import br.com.instagramlike.models.services.GalleryService;
import br.com.instagramlike.utils.PhotoStore;
import br.com.instagramlike.utils.UserAuthenticated;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/gallery")
public class GalleryResource {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private AuthenticatedService authenticatedService;

    @Autowired
    private PhotoStore photoStore;


    @ApiOperation("Create a new picture at user gallery")
    @ApiResponse(
       code = 200, message="OK", response = ResponseEntity.class, responseContainer = "Photo Object"
    )
    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity add(
           @ApiParam(value = "Photo object to attach to the multipartfile") @RequestPart("photo") String photoStringParameters,
           @ApiParam(value = "Multipartfile photo that you want to save", required = true)  @RequestPart("file") MultipartFile file
    ) throws IOException, NoSuchAlgorithmException {

        ObjectMapper mapper;
        mapper = new ObjectMapper();

        Photo photo =  mapper.readValue(photoStringParameters, Photo.class);
        User user = authenticatedService.user();

        photo.setOwner(user);
        photo.setFile(file);

        galleryService.save(photo);

        return new ResponseEntity(photo, HttpStatus.OK);
    }

    @ApiOperation("Get list of photos by")
    @ApiResponse(
            code = 200, message = "OK", response = Page.class, responseContainer = "List"
    )
    @GetMapping(value = "/page/{page}/{size}", produces = "application/json")
    public Page<Photo>  showMyGallery(
            @ApiParam(value = "Number of current page") @PathVariable int page,
            @ApiParam(value = "List size") @PathVariable int size){
        User userAuth = authenticatedService.user();
        Page<Photo> photoPage = galleryService.searchGallery(page, size, userAuth);
        return photoPage;
    }

    @ApiOperation("Show image by url filter")
    @GetMapping(value = "/img/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] showImage(
        @ApiParam(value = "The image url to search") @PathVariable String image
    ) throws IOException {

      User  userAuth = authenticatedService.user();

      image = image.concat(PhotoStore.JPG_EXTENSION);
      boolean isPhotoExist =  galleryService.isPhotoExist(image, userAuth);

      if(!isPhotoExist)
          throw  new PhotoNotFoundException("Foto nao encontrada");

      return photoStore.load(image);
    }

}
