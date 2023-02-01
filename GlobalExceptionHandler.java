package com.myblog.blogapp.exception;

import com.myblog.blogapp.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /*when ever exception occur post not found exception it will create ResourceNotFoundException object
  and go to @ControllerAdvce class and then initialize ExceptionHandler method and show parameter which given in ErrorDetail class */
    /*this is specific exception which is only in this project
    only for post not found because it is handel by  Costomized Class*/

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handelResourceNotFoundException(
            ResourceNotFoundException exception,/*this is like catch block whenever exception occur
             it will create object and pass address to exception*/

            WebRequest webRequest /*it is like ModelMap object in controller Springboot
            will know which object has created and give address to webRequest */
    ) {
        /*this act as e.printStrackTrace()*/
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        /*getFDescription carries uri information*/
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

  /*Global Exception because it was handel by Exception
   Class and Exception Class can handel all type of Exception*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handelAllException(
            ResourceNotFoundException exception,

            WebRequest webRequest
    ) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);


    }
}