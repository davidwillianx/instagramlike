package br.com.instagramlike.utils;


import br.com.instagramlike.models.domains.Photo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

public interface GalleryStore {

    String store(Photo photo) throws IOException, NoSuchAlgorithmException;

    Stream<Path> loadAll();

     byte[] load(String fileName) throws IOException;

    void delete(String fileName);

}
