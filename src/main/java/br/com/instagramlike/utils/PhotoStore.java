package br.com.instagramlike.utils;

import br.com.instagramlike.models.domains.Photo;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class PhotoStore implements GalleryStore {

    public static final String JPG_EXTENSION = ".jpg";
    private static final String PATH_TO_GALLERY = System.getProperty("user.home") + "/Development/java/instagramlike/src/main/webapp/gallery/";


    @Override
    @Description("Return the file name maded by UUI random")
    public String store(Photo photo) throws IOException, NoSuchAlgorithmException {

      String fileExtension = photo.getFile().getOriginalFilename().split(Pattern.quote("."))[1];
      String fileReffName = UUID.randomUUID().toString() + "." + fileExtension;
      String fullPathToFile = PATH_TO_GALLERY +  fileReffName;

      FileCopyUtils.copy(photo.getFile().getBytes(),  new File(fullPathToFile));

      return fileReffName;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public byte[] load(String fileName) throws IOException {

        byte[] img;
        img = Files.readAllBytes(Paths.get(PATH_TO_GALLERY + fileName));

        return img;
    }

    @Override
    public void delete(String fileName) {

    }

}
