package com.openclassrooms.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.dto.AuthLoginRequestDTO;
import com.openclassrooms.chatop.dto.AuthRegisterRequestDTO;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<User> getUser(final Integer id) {
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(final Integer id) {
        userRepository.deleteById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(AuthRegisterRequestDTO authRequest) {
        User user = new User();

        user.setEmail(authRequest.getEmail());
        user.setName(authRequest.getName());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        return user;
    }
    
    public Optional<User> getUserByEmail(AuthLoginRequestDTO authLogin) {
        return userRepository.findByEmail(authLogin.getEmail());
    }

    public boolean isPasswordValid(String password, User user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

}
