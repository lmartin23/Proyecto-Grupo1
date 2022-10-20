package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Administrador extends Usuario {

    public Administrador() {
    }

    public Administrador(String documento, String nombre, String apellido, Date fechaNacimiento, String correo, String contrasena) {
        super(documento, nombre, apellido, fechaNacimiento, correo, contrasena);
    }
}
