package com.fpt.PRM392_FinalProject.exception;

import com.fpt.PRM392_FinalProject.model.CustomError;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exception extends RuntimeException{
    HttpStatus httpStatus;
    CustomError error;

    public static Exception notFound(String message) {
        return Exception.builder()
                 .error(CustomError.builder()
                        .code("404")
                        .message(message)
                        .build())
                .build();
    }
}
