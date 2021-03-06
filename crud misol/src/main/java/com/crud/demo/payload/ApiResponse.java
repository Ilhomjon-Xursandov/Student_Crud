package com.crud.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private boolean success;

    private Object object;

    public ApiResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
