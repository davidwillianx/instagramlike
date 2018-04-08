package br.com.instagramlike.models.domains;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuth extends org.springframework.security.core.userdetails.User {

    private User user;

    public UserAuth(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserAuth(User user, Collection<? extends GrantedAuthority> authorities){
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
