package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @ToString
public class Cliente  extends Usuario {
    private boolean envioDomicilio;

    @OneToMany(mappedBy = "cliente", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private List<Calificacion> calificacionesCliente = new ArrayList<Calificacion>();

    @OneToMany(mappedBy = "cliente", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private List<Notificacion> notificaciones = new ArrayList<Notificacion>();

    @OneToMany(mappedBy = "cliente", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private List<ProductoCarrito> productoCarritos = new ArrayList<ProductoCarrito>();

    public Cliente() { super(); }

    public Cliente(Long id, String documento, String nombre, String apellido, Date fechaNacimiento, String correo, String contrasena, boolean bloqueado, boolean correoValidado, boolean envioDomicilio, List<Calificacion> calificacionesCliente) {
        super(id, documento, nombre, apellido, fechaNacimiento, correo, contrasena, bloqueado, correoValidado);
        this.envioDomicilio = envioDomicilio;
        this.calificacionesCliente = calificacionesCliente;
    }

    public Cliente(String documento, String nombre, String apellido, Date fechaNacimiento, String correo, String contrasena) {
        super(documento, nombre, apellido, fechaNacimiento, correo, contrasena);
    }
}