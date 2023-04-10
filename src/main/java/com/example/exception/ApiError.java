package com.example.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.ZonedDateTime;

@Data
public class ApiError extends RuntimeException {

    private HttpStatusCode httpStatusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private ZonedDateTime timestamp;
    private String message;

    public ApiError(HttpStatusCode httpStatusCode, String message) {
        super();
        this.httpStatusCode = httpStatusCode;
        this.timestamp = ZonedDateTime.now();
        this.message = message;
    }
}