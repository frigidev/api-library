package br.com.frigidev.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frigidev.library.entity.User;
import br.com.frigidev.library.repository.IUserRepository;

@Service
public class UserService {
	@Autowired
    IUserRepository userRepository;

    public User findByEmail(String email){
        return userRepository.findByUserEmail(email).get();
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
