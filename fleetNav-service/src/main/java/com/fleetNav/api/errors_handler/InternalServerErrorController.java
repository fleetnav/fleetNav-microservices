package com.fleetNav.api.errors_handler;

import com.fleetNav.api.dto.error.BaseErrorResponse;
import com.fleetNav.api.dto.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorController {

    @ExceptionHandler(Exception.class)
    public BaseErrorResponse handleInternalServerError(Exception exception) {
        return ErrorResponse.builder()
                .message("You probably have the wrong json format or values, or the specified url does not exist.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }
}
