package com.openclassrooms.chatop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.service.MessageService;
import com.openclassrooms.chatop.util.ResponseHandler;

@RestController
public class MessageController {
  
  @Autowired
  private MessageService messageService;

  @PostMapping("/messages")
  public ResponseEntity<Object> postMessage(@RequestBody Message message) {
    Message savedResponse = messageService.saveMessage(message);

    if (savedResponse != null) {
        return ResponseHandler.generateResponse("Message send with success", HttpStatus.OK, null);
    } else {
        return ResponseHandler.generateResponse("An error occurred while saving the message.", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }
}
