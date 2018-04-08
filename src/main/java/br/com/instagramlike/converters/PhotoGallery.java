package br.com.instagramlike.converters;

import br.com.instagramlike.models.domains.Photo;
import org.springframework.web.multipart.MultipartFile;

public class PhotoGallery {

    private Photo photo;
    private MultipartFile file;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "PhotoGallery{" +
                "photo=" + photo +
                ", file=" + file +
                '}';
    }
}
