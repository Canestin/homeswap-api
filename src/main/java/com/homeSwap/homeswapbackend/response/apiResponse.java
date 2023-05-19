package com.homeSwap.homeswapbackend.response;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class apiResponse {
    private final boolean success;
    private final String message;
    // try again

    public apiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }


}
