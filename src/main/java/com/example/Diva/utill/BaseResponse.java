package com.example.Diva.utill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private boolean success;
    private String message;
    private Object data;

    public BaseResponse(boolean success, String message) {
        this.message = message;
        this.success = success;
    }
}
