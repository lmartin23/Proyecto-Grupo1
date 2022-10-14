package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.TipoPago;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pago", indexes = {
        @Index(name = "idx_pago_id", columnList = "id")
})
@Getter
@Setter
@ToString
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    @Enumerated(EnumType.STRING)
    private TipoPago metodo;
    private boolean liberado;

    @OneToOne(optional = false)
    private Compra compra;
    public Pago() {
    }

    public Pago(Long id, Date fecha, TipoPago metodo, boolean liberado) {
        this.id = id;
        this.fecha = fecha;
        this.metodo = metodo;
        this.liberado = liberado;
    }
}
