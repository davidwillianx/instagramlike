package br.com.instagramlike.models.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "instagram_photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    @Transient
    private String path;
    private String description;
    private String reffName;

    @OneToMany(mappedBy = "photo")
    private List<Like> likes;

    @JsonIgnore
    @Transient
    private MultipartFile file;

    @OneToMany(mappedBy = "photo")
    private List<Comment> comments;

    @OneToOne
    private User owner;

    public static Photo builder(MultipartFile file, User owner){

        Photo photo = new Photo();

        photo.setName(file.getOriginalFilename());
        photo.setOwner(owner);
        photo.setFile(file);

        return photo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public String getReffName() {
        return reffName;
    }

    public void setReffName(String reffName) {
        this.reffName = reffName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
