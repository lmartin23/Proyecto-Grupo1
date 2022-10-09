package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Calificacion", indexes = {
        @Index(name = "idx_calificacion_id", columnList = "id")
})
@Getter
@Setter
@ToString
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int estrellas;
    private String comentario;
    private Date fecha;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Vendedor vendedor;
}

