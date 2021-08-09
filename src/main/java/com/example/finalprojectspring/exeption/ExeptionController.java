package com.example.finalprojectspring.exeption;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ExeptionController {

    private Log logger = LogFactory.getLog(ExeptionController.class);

    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request, Exception e) {
        logger.error("Request " + request.getRequestURL() + " Threw an Exception", e);
        return "error";
    }
}
