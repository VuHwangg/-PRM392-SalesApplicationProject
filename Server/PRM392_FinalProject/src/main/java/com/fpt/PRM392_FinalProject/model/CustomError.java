package com.fpt.PRM392_FinalProject.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomError {
    String message;
    LocalDateTime timestamp;
    String code;
    String error;
    String path;
}
