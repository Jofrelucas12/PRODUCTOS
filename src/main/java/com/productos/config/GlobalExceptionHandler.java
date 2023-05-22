package com.productos.config;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final boolean printStack;


    public GlobalExceptionHandler(@Value("${server.print-stack:true}") boolean printStack) {
        this.printStack = printStack;
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleGeneral(Exception ex) {
        ErrorDTO error = errorDTO("ERROR_GENERICO", "La operación solicitada no ha podido ser realizada. Favor de reintentar de nuevo", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleValidation(ValidationException ex) {
        ErrorDTO error = errorDTO("validacion", ex.getMessage(), ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleHttpNotReadable(HttpMessageNotReadableException ex) {
        ErrorDTO error = errorDTO("Mensaje http ilegible", ex.getMessage(), ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleNoSuchElement(NoSuchElementException ex) {
        ErrorDTO error = errorDTO("Elemento no encontrado", ex.getMessage(), ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorDTO error = errorDTO("El producto ya existe.", ex.getMessage(), ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        //Para que sea más legible el mensaje de error al usar la librería jakarta.validation
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMsg = error.getDefaultMessage();
            sb.append("Campo: ").append(fieldName).append(". Error: ").append(errorMsg).append(". ");
        });
        ErrorDTO error = errorDTO("Argumentos invalidos", sb.toString(), e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleHttpMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        ErrorDTO error = errorDTO("Metodo HTTP no soportado", e.getMessage(), e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleDataIntegrity(DataIntegrityViolationException e) {
        ErrorDTO error = errorDTO("Restricción violada en base de datos", "Restricción violada en base de datos", e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    /**
     * @param code
     * @param message
     * @param t
     * @return new ErrorDTO
     */
    private ErrorDTO errorDTO(String code, String message, Throwable t) {
        LOG.error("Error", t);
        String stack = ExceptionUtils.getStackTrace(t);
        return new ErrorDTO(code, message, stack);
    }

    @AllArgsConstructor
    @Getter
    class ErrorDTO {
        private String code;
        private String message;
        private String stack;
    }

}
