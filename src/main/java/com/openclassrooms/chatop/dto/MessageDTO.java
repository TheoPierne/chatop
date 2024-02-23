package com.openclassrooms.chatop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    @NotBlank(message = "Message must not be blank.")
    @Size(max = 2000, message = "Message length must be less than 2000 characters.")
    private String message;
    @NotNull(message = "Sender is required.")
    private Integer user_id;
    @NotNull(message = "Rental is required.")
    private Integer rental_id;
}
