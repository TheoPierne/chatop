package com.openclassrooms.chatop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterRequestDTO {
  @NotBlank(message = "Email must not be blank.")
  @Email()
  private String email;
  @NotBlank(message = "Name must not be blank.")
  private String name;
  @Size(min = 8, message = "Password length must be more than or equal to 8 characters.")
  private String password;
}
