package br.com.instagramlike.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public class PhotoStore implements GalleryStore {

    @Override
    public void store(MultipartFile photo) {

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
