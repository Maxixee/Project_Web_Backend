package br.com.backend.projetoindividual.web.exception;

import br.com.backend.projetoindividual.domain.exception.CharacterAlreadyExistsException;
import br.com.backend.projetoindividual.domain.exception.InvalidRegistrationInformationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidRegistrationInformationException.class)
    public ResponseEntity<ErrorMessage> invalidRegistrationInformationException(RuntimeException ex, HttpServletRequest request) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(CharacterAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> characterAlreadyExistsException(RuntimeException ex, HttpServletRequest request) {
        log.error("API Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }
}
