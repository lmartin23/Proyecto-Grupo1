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
    private Long id;
    private Date fecha;
    private boolean pagoConfirmado;
    private EstadoCompra estado;

    @OneToOne(mappedBy = "compra", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private Reclamo reclamo;

    @OneToOne(mappedBy = "compra", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private Retiro retiro;

    @OneToOne(mappedBy = "compra", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private Envio envio;

    @OneToOne(mappedBy = "compra", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private Pago pago;

    @OneToOne
    private ProductoCarrito productoCarrito;

    public Compra() {
    }

    public Compra(Long id, Date fecha, boolean pagoConfirmado, EstadoCompra estado, ProductoCarrito productoCarrito, Reclamo reclamo, Pago pago) {
        this.id = id;
        this.fecha = fecha;
        this.pagoConfirmado = pagoConfirmado;
        this.estado = estado;
        this.productoCarrito = productoCarrito;
        this.reclamo = reclamo;
        this.pago = pago;
    }
}