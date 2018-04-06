package br.com.instagramlike.models.services;

import br.com.instagramlike.models.domains.Photo;
import br.com.instagramlike.models.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GalleryService {

    @Autowired
    private PhotoRepository photoRepository;


//    public Photo save(MultipartFile rawPhoto){
//
//    }


}
