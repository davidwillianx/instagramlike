package br.com.instagramlike.models.services;

import br.com.instagramlike.models.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service("authSecurityService")
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userFound = userService.searchByEmail(email);

        if(!userFound.isPresent()) throw new UsernameNotFoundException("User not found");

        User user = userFound.get();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());

    }
}
