package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoReclamo;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoResolucion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reclamo", indexes = {
        @Index(name = "idx_reclamo_id", columnList = "id")
})
@Getter @Setter @ToString
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private EstadoReclamo estado;
    private EstadoResolucion resolucion;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaUltEstado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Compra compra;

    public Reclamo() {
    }

    public Reclamo(String id, EstadoReclamo estado, EstadoResolucion resolucion, String descripcion, Date fechaInicio, Date fechaUltEstado) {
        this.id = id;
        this.estado = estado;
        this.resolucion = resolucion;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaUltEstado = fechaUltEstado;
    }

}
