package com.lambferret.playhttpground.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice
public class CustomExceptionHandler {

    /*

    404 요청은 Controller 수준에서 handle하는 것이 아닌듯 하다
    -> NoHandlerFoundException은 404를 자동으로 잡아주는 것이 아닌 인위적인 유발을 위한 Exception인듯

     *404 메세지는 문법 오류가 아니고 잘못된 URL을 호출할 때 보이므로 다르게 처리해주어야 합니다.*

     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NoHandlerFoundException e, Model model) {
        model.addAttribute("exception", e.getMessage());
        System.out.println(Arrays.toString(e.getStackTrace()));
        System.out.println("%^&*%^&*%^&*%404^&*%&^*%^&*%^&*%^&*%^&*%^&*%^&*%^&*");

        return "notFound";
    }

    @ExceptionHandler(Exception.class)
    public String globalException(Exception e, Model model) {
        model.addAttribute("exception", e);
        System.out.println("%^&*%^&*%^&*%global^&*%&^*%^&*%^&*%^&*%^&*%^&*%^&*%^&*");

        return "error";
    }
}
