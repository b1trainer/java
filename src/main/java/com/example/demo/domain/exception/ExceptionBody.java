package com.example.demo.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionBody {

    public final String message;
    private Map<String, String> errors;

    public ExceptionBody(String message) {
        this.message = message;
    }


}
