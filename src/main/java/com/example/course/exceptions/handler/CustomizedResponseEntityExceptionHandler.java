package com.example.course.exceptions.handler;

import com.example.course.exceptions.ExceptionResponse;
import com.example.course.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class) //exceção geral
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) { //o WebRequest é a url que foi passada como request

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));  //cria um objeto ExcecaoResponse com o campo do horario que foi a exceção,
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);                                                   // a mensagem que vai ser passada e o request que foi feito

    }

    @ExceptionHandler(ResourceNotFoundException.class) //exceção quando tenta somar valores que não são numéricos
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions (Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);       //o status http de bad request é o 400

    }
}




