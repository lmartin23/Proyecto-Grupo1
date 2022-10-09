package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter @Setter @ToString
public class Cliente  extends Usuario {
    private boolean envioDomicilio;

    public Cliente(String id, String documento, String tipoDocumento, String nombre, String apellido, Date fechaNacimiento, String correo, String contraseña, boolean bloqueado, boolean correoValidado, boolean envioDomicilio) {
        super(id, documento, tipoDocumento, nombre, apellido, fechaNacimiento, correo, contraseña, bloqueado, correoValidado);
        this.envioDomicilio = envioDomicilio;
    }

    public Cliente() {
        super();
    }
}
