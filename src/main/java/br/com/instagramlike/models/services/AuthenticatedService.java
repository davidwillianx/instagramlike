package br.com.instagramlike.models.services;

import br.com.instagramlike.models.domains.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
public class AuthenticatedService {

//    @Autowired
//    private UserService userService;


    @Before("@annotation(br.com.instagramlike.utils.UserAuthenticated)")
    public void authenticatedUser(ProceedingJoinPoint joinPoint) throws Throwable {

//        String emailUserAuthenticated = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Optional<User> userAuthenticated = userService.searchByEmail(emailUserAuthenticated);

        System.out.println("This aspect not so much easy was called by");

//        joinPoint.proceed( new Object[]{userAuthenticated.get()});
        joinPoint.proceed();
    }

}
