package com.alexjuep.microservicio.repository;

import com.alexjuep.microservicio.model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoRepository {
    Mono<Alumno> guardar(Alumno alumno);

    Flux<Alumno> obtenerTodos();

    Mono<Alumno> obtenerPorId(String id);
    Flux<Alumno> obtenerAlumnosActivos(boolean es_activo);
}
