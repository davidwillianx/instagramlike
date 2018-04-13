package br.com.instagramlike.utils;

import br.com.instagramlike.models.domains.Photo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.not;

public class PhotoStoreTest {

    private static final String PATH_TO_IMG_SAVED = System.getProperty("user.home") + "/Development/java/instagramlike/src/main/webapp/gallery/";

    PhotoStore photoStore;

    private Photo photo;
    private MockMultipartFile mockMultipartFile;

    @Before
    public void setUp(){

        photoStore = new PhotoStore();

        photo = new Photo();
        mockMultipartFile = new MockMultipartFile("mock-img", "mock-img.jpg", "jpg", "nothing".getBytes());

        photo.setFile(mockMultipartFile);
    }

    @After
    public void tearDown() throws IOException {

        Files.newDirectoryStream(Paths.get(PATH_TO_IMG_SAVED))
                .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
    }

    @Test
    public void testShouldStorePhoto() throws IOException, NoSuchAlgorithmException {

        photoStore.store(photo);

        boolean isFileExistInGalleryFolder = Files.exists(Paths.get(PATH_TO_IMG_SAVED));

        Assert.assertTrue(isFileExistInGalleryFolder);

    }

    @Test
    public void testShouldHashPhotoName() throws IOException, NoSuchAlgorithmException {

        String photoOriginalFileName = mockMultipartFile.getOriginalFilename();
        photoStore.store(photo);

        File savedPhotos =  new File(PATH_TO_IMG_SAVED);
        File savedPhoto = savedPhotos.listFiles()[0];

        Assert.assertThat(savedPhoto.getName(), not(photoOriginalFileName));
    }

}
