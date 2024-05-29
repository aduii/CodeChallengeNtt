package com.alexjuep.microservicio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("alumno")
public class Alumno implements Serializable {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private Boolean es_activo;
    private Integer edad;

    public Alumno() {
    }

    public Alumno(String id, String nombre, String apellido, Boolean es_activo, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.es_activo = es_activo;
        this.edad = edad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Boolean getEs_activo() {
        return es_activo;
    }

    public void setEs_activo(Boolean es_activo) {
        this.es_activo = es_activo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
