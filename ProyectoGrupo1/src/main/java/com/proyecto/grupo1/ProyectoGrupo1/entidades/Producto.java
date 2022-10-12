package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.CategoProd;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Producto", indexes = {
        @Index(name = "idx_producto_id", columnList = "id")
})
@Getter @Setter @ToString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int moneda;
    private int stock;
    private CategoProd categoria;
    private boolean activo;

    @ManyToOne(optional = false)
    private Vendedor vendedor;

    @OneToMany(mappedBy = "producto", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private List<ProductoCarrito> productoCarritos = new ArrayList<ProductoCarrito>();

    @OneToMany(mappedBy = "producto", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private List<ImagenProducto> imagenProductos = new ArrayList<ImagenProducto>();


    public Producto() {
    }

    public Producto(Long id, String nombre, String descripcion, double precio, int moneda, int stock, CategoProd categoria, boolean activo, Vendedor vendedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.moneda = moneda;
        this.stock = stock;
        this.categoria = categoria;
        this.activo = activo;
        this.vendedor = vendedor;
    }

}
