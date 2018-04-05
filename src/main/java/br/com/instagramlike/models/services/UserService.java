package br.com.instagramlike.models.services;

import br.com.instagramlike.models.domains.User;
import br.com.instagramlike.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> searchByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
