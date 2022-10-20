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

    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direcciones  = new ArrayList<Direccion>();

    @OneToOne(mappedBy = "cliente", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private Vendedor vendedor;
    public Cliente() { super(); }

    public Cliente(String documento, String nombre, String apellido, Date fechaNacimiento, String correo, String contrasena) {
        super(documento, nombre, apellido, fechaNacimiento, correo, contrasena);
    }
}
