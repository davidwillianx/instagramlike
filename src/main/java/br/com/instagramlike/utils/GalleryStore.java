package br.com.instagramlike.utils;


import br.com.instagramlike.models.domains.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface GalleryStore {

    void store(Photo photo);

    Stream<Path> loadAll();

    Path load(String fileName);

    void delete(String fileName);

}
