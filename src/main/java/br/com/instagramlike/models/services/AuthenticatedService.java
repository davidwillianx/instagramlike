package br.com.instagramlike.models.services;

import br.com.instagramlike.models.domains.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class AuthenticatedService {

    @Autowired
    private UserService userService;


    public User user(){

        String emailUserAuthenticated = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userAuthenticated = userService.searchByEmail(emailUserAuthenticated);


        return userAuthenticated.get();
    }

}
