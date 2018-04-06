package br.com.instagramlike.models.domains;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "instagram_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private  String password;

    @OneToMany(mappedBy = "owner")
    private List<Photo> gallery;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
