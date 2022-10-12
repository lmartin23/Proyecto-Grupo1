package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Calificacion", indexes = {
        @Index(name = "idx_calificacion_id", columnList = "id")
})
@Getter @Setter @ToString
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int estrellas;
    private String comentario;
    private Date fecha;

    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Vendedor vendedor;

    public Calificacion() {
    }

    public Calificacion(Long id, int estrellas, String comentario, Date fecha, Cliente cliente, Vendedor vendedor) {
        this.id = id;
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.fecha = fecha;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

}

