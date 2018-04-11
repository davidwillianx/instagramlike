package br.com.instagramlike.utils;

import br.com.instagramlike.models.domains.Photo;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

@Component
public class PhotoStore implements GalleryStore {

    private static final String PATH_TO_GALLERY = System.getProperty("user.dir")+"/../../Development/java/instagramlike/src/main/webapp/gallery/";

    @Override
    public void store(Photo photo) throws IOException, NoSuchAlgorithmException {

       MessageDigest hashName =  MessageDigest.getInstance("MD5");

       hashName.update(photo.getFile().getOriginalFilename().getBytes());

      FileCopyUtils
          .copy(photo.getFile().getBytes(), new File(PATH_TO_GALLERY + photo.getFile().getOriginalFilename()));

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String fileName) {
        return null;
    }

    @Override
    public void delete(String fileName) {

    }
}
