package br.com.instagramlike.utils;


import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface GalleryStore {

    void store(MultipartFile photo);

    Stream<Path> loadAll();

    Path load(String fileName);

    void delete(String fileName);

}
