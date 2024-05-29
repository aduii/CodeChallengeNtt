package com.alexjuep.microservicio.repository;

import com.alexjuep.microservicio.model.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AlumnoRepositoryImpl implements AlumnoRepository{

    @Autowired
    private ReactiveRedisTemplate<String,Alumno> reactiveRedisTemplate;

    private final String ALUMNO_KEY = "Alumno";

    @Override
    public Mono<Alumno> guardar(Alumno alumno) {
        return reactiveRedisTemplate.<String, Alumno>opsForHash()
                .put(ALUMNO_KEY, alumno.getId(),alumno)
                .thenReturn(alumno);
    }

    @Override
    public Flux<Alumno> obtenerTodos() {
        return reactiveRedisTemplate.<String, Alumno>opsForHash().values(ALUMNO_KEY);
    }

    @Override
    public Mono<Alumno> obtenerPorId(String id) {
        return reactiveRedisTemplate.<String, Alumno>opsForHash().get(ALUMNO_KEY, id);
    }

    @Override
    public Flux<Alumno> obtenerAlumnosActivos(boolean es_activo) {
        return obtenerTodos()
                .filter(alumno -> alumno.getEs_activo().equals(es_activo));
    }
}
