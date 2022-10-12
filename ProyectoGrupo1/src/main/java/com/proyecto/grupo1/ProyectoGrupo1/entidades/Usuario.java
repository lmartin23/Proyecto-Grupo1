package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Usuario", indexes = {
        @Index(name = "idx_usuario_correo", columnList = "correo", unique= true),
        @Index(name = "idx_usuario_id", columnList = "id")
})
@Getter @Setter
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String documento;
    private String tipoDocumento;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String correo;
    private String contraseña;
    private boolean bloqueado;
    private boolean correoValidado;

    public Usuario(Long id, String documento, String tipoDocumento, String nombre, String apellido, Date fechaNacimiento, String correo, String contraseña, boolean bloqueado, boolean correoValidado) {
        this.id = id;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contraseña = contraseña;
        this.bloqueado = bloqueado;
        this.correoValidado = correoValidado;
    }

    public Usuario() {

    }
}
