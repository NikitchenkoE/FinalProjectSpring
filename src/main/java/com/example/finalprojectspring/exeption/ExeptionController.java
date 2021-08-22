package com.example.finalprojectspring.exeption;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@Log4j
public class ExeptionController {

    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request, Exception e) {
        log.error("Request " + request.getRequestURL() + " Threw an Exception", e);
        return "error";
    }

    @ExceptionHandler(value = NotEnoughMoneyException.class)
    public String handleExceptionNotEnoghMoney(HttpServletRequest request, Exception e) {
        log.error("Request " + request.getRequestURL() + " Threw an Exception", e);
        return "errorNotEnoughMoney";
    }
}
