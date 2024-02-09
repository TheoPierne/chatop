package com.openclassrooms.chatop.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.micrometer.common.lang.Nullable;

public class ResponseHandler {

  /**
   * Génère une réponse à une requête sur l'API
   * @param message Le message à la requête sur l'API
   * @param status Le status de la réponse
   * @param response Un objet contenant plus d'information à la réponse (n'est pas obligatoire)
   * @return Retourne un objet de type ResponseEntity contenant la réponse à renvoyé à la requête sur l'API
   */
  public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, @Nullable Object response) {
    Map<String, Object> map = new HashMap<String, Object>();

    map.put("message", message);
    map.put("status", status.value());

    if (response != null) {
      map.put("data", response);
    }

    return new ResponseEntity<Object>(map, status);
  }
  
}
