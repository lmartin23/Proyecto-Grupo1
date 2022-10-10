package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MetodoEntrega", indexes = {
        @Index(name = "idx_metodoentrega_id", columnList = "id")
})
@Getter
@Setter
@ToString
public abstract class MetodoEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date fechaDesde;
    private Date fechaHasta;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Compra compra;

}
