package com.openclassrooms.chatop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.repository.MessageRepository;

import jakarta.persistence.EntityManager;

@Service
public class MessageService {
  
  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private EntityManager entityManager;

  @Transactional
  public Message saveMessage(Message message) {
    message = messageRepository.save(message);

    // On refresh l'objet message avant de le retourner, sinon "created_at" et "updated_at" auront pour valeur null
    // puisque ces deux valeurs sont créées par MySQL
    entityManager.refresh(message);
    return message;
  }
}
