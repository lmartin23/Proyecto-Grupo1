package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class Administrador extends Usuario {

    public Administrador(Long id, String documento, String tipoDocumento, String nombre, String apellido, Date fechaNacimiento, String correo, String contraseña, boolean bloqueado, boolean correoValidado) {
        super(id, documento, tipoDocumento, nombre, apellido, fechaNacimiento, correo, contraseña, bloqueado, correoValidado);
    }

    public Administrador() {

    }
}
