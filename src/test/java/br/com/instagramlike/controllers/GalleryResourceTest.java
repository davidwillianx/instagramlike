package br.com.instagramlike.controllers;

import br.com.instagramlike.models.domains.Photo;
import br.com.instagramlike.models.services.GalleryService;
import org.apache.commons.io.FileUtils;
import org.hibernate.boot.jaxb.SourceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.IOException;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GalleryResourceTest {

    @InjectMocks
    private GalleryResource galleryResource;

    @Mock
    private GalleryService galleryService;

    private MockMvc mockMvc;

    private MockMultipartFile mockMultipartFile;

    private static final String PATH_IMG_TEST = System.getProperty("user.home")
            + "/src/test/java/br/com/instagramlike/dependencies/mock-img.jpg";


    Photo photo;


    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(galleryResource)
//                .apply(springSecurity())
                .build();

        mockMultipartFile = new MockMultipartFile(
                "file",
                "mock-img.jpg",
                "image/jpeg", "whateverpicture.jpg".getBytes()
        );
    }

    @Test
    public void testAddShouldSave() throws Exception {

        photo = new Photo();
        photo.setFile(mockMultipartFile);

        when(galleryService.save(photo))
             .thenReturn(photo);

        mockMvc.perform(
                fileUpload("/api/gallery")
                .file(mockMultipartFile)
                .contentType("multipart/form-data")
        ).andExpect(status().isOk());

    }






}
