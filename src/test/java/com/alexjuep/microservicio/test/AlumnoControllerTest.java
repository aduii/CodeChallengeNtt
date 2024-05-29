package com.alexjuep.microservicio.test;

import com.alexjuep.microservicio.controller.AlumnoController;
import com.alexjuep.microservicio.model.ResponseMessage;
import com.alexjuep.microservicio.model.Alumno;
import com.alexjuep.microservicio.service.AlumnoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AlumnoControllerTest {

    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    @Test
    public void testGuardarAlumnoExistente() {
        // Given
        Alumno alumno = new Alumno("1", "Juan", "Perez", true, 25);
        when(alumnoService.guardar(alumno)).thenReturn(Mono.just(alumno));
        when(alumnoService.obtenerPorId("1")).thenReturn(Mono.just(alumno)); // Simula que el alumno existe

        // When
        Mono<ResponseEntity<ResponseMessage>> response = alumnoController.guardar(alumno);

        // Then
        response.subscribe(result -> {
            assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
            assertEquals("Ya existe el ID", result.getBody().getData());
        });
    }

    @Test
    public void testGuardarAlumnoNuevo() {
        // Given
        Alumno alumno = new Alumno("2", "Maria", "Lopez", true, 28);
        when(alumnoService.obtenerPorId("2")).thenReturn(Mono.empty());
        when(alumnoService.guardar(alumno)).thenReturn(Mono.just(alumno));

        // When
        Mono<ResponseEntity<ResponseMessage>> response = alumnoController.guardar(alumno);

        // Then
        response.subscribe(result -> {
            assertEquals(HttpStatus.CREATED, result.getStatusCode());
            assertEquals(alumno, result.getBody().getData());
        });
    }
}