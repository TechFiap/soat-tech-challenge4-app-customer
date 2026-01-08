package com.soat_tech_challenge4.app_customer.api.rest.handler;

import com.soat_tech_challenge4.app_customer.api.rest.dto.response.ErrorResponseDto;
import com.soat_tech_challenge4.app_customer.application.exceptions.ErrorException;
import com.soat_tech_challenge4.app_customer.application.exceptions.InvalidCpfException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    @DisplayName("Deve tratar MethodArgumentNotValidException e retornar ErrorResponseDto")
    void shouldHandleValidationException() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("object", "email", "must not be empty");

        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<ErrorResponseDto> response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().status());
        assertEquals("email, must not be empty", response.getBody().error());
    }

    @Test
    @DisplayName("Deve tratar InvalidCpfException e retornar mapa com detalhes")
    void shouldHandleInvalidCpfException() {
        InvalidCpfException ex = new InvalidCpfException("CPF inválido");

        ResponseEntity<Map<String, Object>> response = handler.handleInvalidCpfException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().get("status"));
        assertEquals("Invalid CPF", response.getBody().get("error"));
        assertEquals("CPF inválido", response.getBody().get("message"));
        assertTrue(response.getBody().containsKey("timestamp"));
    }

    @Test
    @DisplayName("Deve tratar ErrorException e retornar ErrorResponseDto")
    void shouldHandleErrorException() {
        ErrorException ex = new ErrorException("Erro genérico");

        ResponseEntity<ErrorResponseDto> response = handler.handleErrorExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().status());
        assertEquals("Erro genérico", response.getBody().error());
    }

    @Test
    @DisplayName("Deve tratar IllegalArgumentException e retornar ErrorResponseDto")
    void shouldHandleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Argumento inválido");

        ResponseEntity<ErrorResponseDto> response = handler.handleIllegalArgumentExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().status());
        assertEquals("Argumento inválido", response.getBody().error());
    }
}