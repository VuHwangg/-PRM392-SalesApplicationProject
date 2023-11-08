package com.fpt.PRM392_FinalProject.exception;

import com.fpt.PRM392_FinalProject.model.CustomError;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exception extends RuntimeException{
    HttpStatus httpStatus;
    CustomError error;



    public static Exception notFound(String message, String path) {
        return Exception.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .error(CustomError.builder()
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .code("404")
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .build())
                .build();
    }

    public static Exception badRequest(String message) {
        return Exception.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(CustomError.builder()
                        .code("400")
                        .message(message)
                        .build())
                .build();
    }

    public static Exception badRequest(String message, String path) {
        return Exception.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(CustomError.builder()
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .code("404")
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .path(path)
                        .build())
                .build();
    }
}
