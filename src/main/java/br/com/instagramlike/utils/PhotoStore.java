package br.com.instagramlike.utils;

import br.com.instagramlike.models.domains.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public class PhotoStore implements GalleryStore {

    @Override
    public void store(Photo photo) {

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
