package com.alexjuep.microservicio.service;

import com.alexjuep.microservicio.model.Alumno;
import com.alexjuep.microservicio.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    public Mono<Alumno> guardar(Alumno alumno){
        return alumnoRepository.guardar(alumno);
    }

    public Flux<Alumno> obtenerAlumnosActivos(boolean es_activo){
        return alumnoRepository.obtenerAlumnosActivos(es_activo);
    }

    public Mono<Alumno> obtenerPorId(String id){
        return alumnoRepository.obtenerPorId(id);
    }
}
