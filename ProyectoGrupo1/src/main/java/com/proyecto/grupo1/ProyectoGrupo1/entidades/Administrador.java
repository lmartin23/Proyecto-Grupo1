package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.Rol;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Administrador extends Usuario {
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private boolean superAdmin;
    public Administrador() {
    }

    public Administrador(String documento, String nombre, String apellido, Date fechaNacimiento, String correo, String contrasena) {
        super(documento, nombre, apellido, fechaNacimiento, correo, contrasena);
        this.rol = Rol.ROL_ADMIN;
        superAdmin = false;
    }

    public Administrador( String correo, String contrasena) {
        super(null, null, null, null, correo, contrasena);
        this.rol = Rol.ROL_ADMIN;
        superAdmin = false;
    }
}
