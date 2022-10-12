package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoProdCarrito;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ProductoCarrito", indexes = {
        @Index(name = "idx_productocarrito_id", columnList = "id")
})
@Getter
@Setter
@ToString
public class ProductoCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private EstadoProdCarrito estado;
    private int moneda;
    private int cantidad;
    private double total;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    private Producto producto;

    @OneToOne(mappedBy = "productoCarrito", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private Compra compra;

    public ProductoCarrito() {
    }

    public ProductoCarrito(Long id, EstadoProdCarrito estado, int moneda, int cantidad, double total, Cliente cliente, Producto producto) {
        this.id = id;
        this.estado = estado;
        this.moneda = moneda;
        this.cantidad = cantidad;
        this.total = total;
        this.cliente = cliente;
        this.producto = producto;
    }
}
