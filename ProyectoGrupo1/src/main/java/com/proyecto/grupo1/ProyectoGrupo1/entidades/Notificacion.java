package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.TipoNotificacion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notificacion", indexes = {
        @Index(name = "idx_notificacion_id", columnList = "id")
})
@Getter @Setter @ToString
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private TipoNotificacion tipo;
    private String descripcion;
    private Date fecha;

    @ManyToOne
    private Usuario usuario;

    public Notificacion() {
    }

    public Notificacion(String id, TipoNotificacion tipo, String descripcion, Date fecha, Usuario usuario) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.usuario = usuario;
    }
}
