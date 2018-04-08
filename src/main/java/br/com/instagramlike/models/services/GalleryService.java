package br.com.instagramlike.models.services;

import br.com.instagramlike.models.domains.Photo;
import br.com.instagramlike.models.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

    @Autowired
    private PhotoRepository photoRepository;


    public Photo save(Photo photo){
        System.out.println(photo.getOwner().getEmail());

        return null;

    }


}
