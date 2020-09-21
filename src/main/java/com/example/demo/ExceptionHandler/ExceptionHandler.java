package com.example.demo.ExceptionHandler;

import org.jooq.exception.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.regex.PatternSyntaxException;

public class ExceptionHandler extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        try {
//            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//            String message = null;
//            if (ex instanceof PatternSyntaxException) {
//                status = HttpStatus.BAD_REQUEST;
//                message = ex.getMessage();
//            }
//            response.sendError(status.value(), message);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        return new ModelAndView();
    }
}
