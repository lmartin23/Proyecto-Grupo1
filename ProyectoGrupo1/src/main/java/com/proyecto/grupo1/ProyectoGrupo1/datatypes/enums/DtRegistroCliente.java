package com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums;

import lombok.Getter;

import java.util.Date;

@Getter
public class DtRegistroCliente {
    private String documento;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String correo;
    private String contrasena;

    public DtRegistroCliente(String documento, String nombre, String apellido, Date fechaNacimiento, String correo, String contrasena) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contrasena = contrasena;
    }
}
