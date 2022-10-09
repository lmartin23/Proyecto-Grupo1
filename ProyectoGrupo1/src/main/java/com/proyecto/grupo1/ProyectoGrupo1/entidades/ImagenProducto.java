package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ImagenProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String url;

    @ManyToOne
    private Producto producto;

    public ImagenProducto(String id, String url, Producto producto) {
        this.id = id;
        this.url = url;
        this.producto = producto;
    }

}
