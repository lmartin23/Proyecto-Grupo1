package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoCompra;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "Compra", indexes = {
        @Index(name = "idx_compra_id", columnList = "id")
})
@Getter @Setter @ToString
public class Compra {
    @Id
    @SequenceGenerator(name = "compra_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_id_seq")
    private Long id;
    private Date fecha;
    private boolean pagoConfirmado;
    @Enumerated(EnumType.STRING)
    private EstadoCompra estado;

    @OneToOne(mappedBy = "compra", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private Reclamo reclamo;

    @OneToOne(mappedBy = "compra", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private Retiro retiro;

    @OneToOne(mappedBy = "compra", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private Envio envio;

    @ManyToOne
    private Pago pago;

    @OneToOne
    private ProductoCarrito productoCarrito;

    public Compra() {
    }

    public Compra(boolean pagoConfirmado, EstadoCompra estado, ProductoCarrito productoCarrito, Pago pago) {
        this.fecha = Calendar.getInstance().getTime();
        this.pagoConfirmado = pagoConfirmado;
        this.estado = estado;
        this.productoCarrito = productoCarrito;
        this.pago = pago;
    }
}
