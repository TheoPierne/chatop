package com.openclassrooms.chatop.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.repository.UserRepository;
import com.openclassrooms.chatop.model.User;
/**
 * Custom UserDetailsService implementation for Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;
  /**
   * Loads a user by their email address for Spring Security authentication.
   *
   * @param email The email address of the user.
   * @return UserDetails representing the authenticated user.
   * @throws UsernameNotFoundException If the user is not found.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> optionalUser = userRepository.findByEmail(email);
    User user = optionalUser.orElseThrow(() ->
      new UsernameNotFoundException("User not found with email: " + email)
    );
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
  }
}
