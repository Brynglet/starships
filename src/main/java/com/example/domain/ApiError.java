package com.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError extends RuntimeException {

    private HttpStatusCode httpStatusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private ZonedDateTime timestamp;
    private String message;

    public ApiError(HttpStatusCode httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.timestamp = ZonedDateTime.now();
        this.message = message;
    }
}