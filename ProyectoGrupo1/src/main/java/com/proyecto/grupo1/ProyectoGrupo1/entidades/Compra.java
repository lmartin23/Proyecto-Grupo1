package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoCompra;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Compra", indexes = {
        @Index(name = "idx_compra_id", columnList = "id")
})
@Getter @Setter @ToString
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date fecha;
    private boolean pagoConfirmado;
    private EstadoCompra estado;

    @OneToOne
    private ProductoCarrito productoCarrito;

    @OneToOne
    private Reclamo reclamo;

    @OneToOne
    private Pago pago;

    @OneToOne
    private MetodoEntrega metodoEntrega;

    public Compra() {
    }

    public Compra(String id, Date fecha, boolean pagoConfirmado, EstadoCompra estado, ProductoCarrito productoCarrito, Reclamo reclamo, Pago pago, MetodoEntrega metodoEntrega) {
        this.id = id;
        this.fecha = fecha;
        this.pagoConfirmado = pagoConfirmado;
        this.estado = estado;
        this.productoCarrito = productoCarrito;
        this.reclamo = reclamo;
        this.pago = pago;
        this.metodoEntrega = metodoEntrega;
    }
}
