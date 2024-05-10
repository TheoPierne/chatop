package com.openclassrooms.chatop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.openclassrooms.chatop.dto.AuthRegisterRequestDTO;
import com.openclassrooms.chatop.dto.AuthLoginRequestDTO;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.UserService;
import com.openclassrooms.chatop.util.JwtUtil;
import com.openclassrooms.chatop.util.ResponseHandler;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody @NotNull AuthRegisterRequestDTO authRegisterRequest) {
    User newUser = userService.createUser(authRegisterRequest);
    userService.saveUser(newUser);

    String token = JwtUtil.generateJwtToken(newUser.getId());

    return ResponseHandler.generateResponse("User created", HttpStatus.CREATED, token);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @NotNull AuthLoginRequestDTO authLoginRequest) {
    Optional<User> user = userService.getUserByEmail(authLoginRequest);

    if (user.isEmpty()) {
      return ResponseHandler.generateResponse("User not found", HttpStatus.NOT_FOUND, null);
    }

    User newUser = user.get();
    
    if (!userService.isPasswordValid(authLoginRequest.getPassword(), newUser)) {
      return ResponseHandler.generateResponse("Password incorrect", HttpStatus.UNAUTHORIZED, null);
    }

    String token = JwtUtil.generateJwtToken(newUser.getId());

    return ResponseHandler.generateResponse("", HttpStatus.OK, token);
  }

  @GetMapping("/me")
  public ResponseEntity<Object> me(@RequestHeader("Authorization") String authorizationHeader) {
    try {
      Integer userId = getUserIdFromAuthorizationHeader(authorizationHeader);
      User user = getUserByTokenId(userId);
      return ResponseHandler.generateResponse("", HttpStatus.OK, user);
    } catch (Exception e) {
      return ResponseHandler.generateResponse("Authorization header incorrect", HttpStatus.FORBIDDEN, null);
    }
  }

  private Integer getUserIdFromAuthorizationHeader(String header) throws Exception {
    String jwtToken = JwtUtil.extractJwtFromHeader(header);


    Optional<Integer> userId = JwtUtil.extractUserId(jwtToken);

    if (userId.isEmpty()) {
      throw new Exception("Unauthorized");
    }

    return userId.get();
  }

  private User getUserByTokenId(Integer userIdFromToken) throws Exception {
    // Fetch user information based on the user ID
    Optional<User> optionalSpecificUser = userService.getUser(userIdFromToken);
    if (optionalSpecificUser.isEmpty()) {
        throw new Exception("User not found");
    }

    User user = optionalSpecificUser.get();

    return user;
}
  
}
