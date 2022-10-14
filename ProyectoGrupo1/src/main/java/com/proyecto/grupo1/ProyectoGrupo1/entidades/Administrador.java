package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class Administrador extends Usuario {

    public Administrador(Long id, String documento, String nombre, String apellido, Date fechaNacimiento, String correo, String contrasena, boolean bloqueado, boolean correoValidado) {
        super(id, documento, nombre, apellido, fechaNacimiento, correo, contrasena, bloqueado, correoValidado);
    }

    public Administrador() {

    }
}
