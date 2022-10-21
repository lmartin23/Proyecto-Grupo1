package com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class DtCliente {
    private Long id;
    private String documento;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String correo;
    private String contrasena;
    private boolean bloqueado;
    private boolean correoValidado;

    public DtCliente(Long id, String documento, String nombre, String apellido, Date fechaNacimiento, String correo, String contrasena, boolean bloqueado, boolean correoValidado) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contrasena = contrasena;
        this.bloqueado = bloqueado;
        this.correoValidado = correoValidado;
    }

    public DtCliente() {

    }
}
