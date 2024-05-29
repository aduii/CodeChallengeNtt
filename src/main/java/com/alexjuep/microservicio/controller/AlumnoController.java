package com.alexjuep.microservicio.controller;

import com.alexjuep.microservicio.model.Alumno;
import com.alexjuep.microservicio.model.ResponseMessage;
import com.alexjuep.microservicio.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @PostMapping
    public Mono<ResponseEntity<ResponseMessage>> guardar(@RequestBody Alumno alumno) {
        return alumnoService.obtenerPorId(alumno.getId())
                .flatMap(existingAlumno -> {
                    return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseMessage(HttpStatus.CONFLICT, "Ya existe el ID")));
                })
                .switchIfEmpty(alumnoService.guardar(alumno)
                        .map(savedAlumno -> ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ResponseMessage(HttpStatus.CREATED, savedAlumno))));
    }

    @GetMapping("/obtenerAlumnosActivos")
    public Flux<Alumno> obtenerAlumnosActivos(@RequestParam boolean es_activo){
        return alumnoService.obtenerAlumnosActivos(es_activo);
    }
}
