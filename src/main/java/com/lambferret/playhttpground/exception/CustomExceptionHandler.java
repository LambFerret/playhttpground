package com.lambferret.playhttpground.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundException(ApiErrorException e, Model model) {
        ApiErrorResponse response = new ApiErrorResponse(e.getCode(), e.getMessage());
        model.addAttribute("code", e.getCode());
        model.addAttribute("status", e.getStatus());
        model.addAttribute("msg", e.getMessage());
        return "thymeleaf:error";
    }
}
