package com.habr.exception_handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;


@ControllerAdvice
@ResponseBody
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Map<String, Object> body = new LinkedHashMap<>();
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomGlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date().toString());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    //setting for PathVariable error
    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<Object> handleConstraintViolation(Exception ex) {
        body.put("timestamp", new Date().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", ex.getMessage().substring(ex.getMessage().indexOf(':') + 1));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
        body.put("timestamp", new Date().toString());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("errors", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleNullPointerException(RuntimeException ex) {
        body.put("timestamp", new Date().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("errors", "Something went wrong");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) throws JsonProcessingException {
        return new ResponseEntity<>(parseExceptionMessage(Objects.requireNonNull(ex.getMessage())), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({HttpServerErrorException.class})
    public ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException ex) throws JsonProcessingException {
        return new ResponseEntity<>(parseExceptionMessage(Objects.requireNonNull(ex.getMessage())), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({WebClientResponseException.class})
    public ResponseEntity<Object> handleWebClientResponseException(WebClientResponseException ex) throws JsonProcessingException {
        return new ResponseEntity<>(parseExceptionMessage(Objects.requireNonNull(ex.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private JsonNode parseExceptionMessage(String msg) throws JsonProcessingException {
        return objectMapper.readTree(msg.substring(msg.indexOf('{'), msg.length() - 1));
    }
}
