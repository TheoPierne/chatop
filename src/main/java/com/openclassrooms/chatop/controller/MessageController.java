package com.openclassrooms.chatop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.MessageDTO;
import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.MessageService;
import com.openclassrooms.chatop.service.RentalService;
import com.openclassrooms.chatop.service.UserService;
import com.openclassrooms.chatop.util.ResponseHandler;

import jakarta.validation.constraints.NotNull;

@RestController
public class MessageController {
  
  @Autowired
  private MessageService messageService;

  @Autowired
  private RentalService rentalServices;

  @Autowired
  private UserService userService;

  @PostMapping("/messages")
  public ResponseEntity<Object> postMessage(@RequestBody @NotNull MessageDTO message) {

    Optional<Rental> optionalRental = rentalServices.getRental(message.getRental_id());
    if (optionalRental.isEmpty()) {
        return ResponseHandler.generateResponse("An error occurred while loading the rental.", HttpStatus.NOT_FOUND, null);
    }

    Optional<User> optionalUser = userService.getUser(message.getUser_id());
    if (optionalRental.isEmpty()) {
      return ResponseHandler.generateResponse("An error occurred while loading the owner of the rental.", HttpStatus.NOT_FOUND, null);
    }

    Message newMessage = new Message(optionalRental.get(), optionalUser.get(), message.getMessage());

    Message savedResponse = messageService.saveMessage(newMessage);

    if (savedResponse != null) {
        return ResponseHandler.generateResponse("Message send with success", HttpStatus.OK, null);
    } else {
        return ResponseHandler.generateResponse("An error occurred while saving the message.", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }
}
