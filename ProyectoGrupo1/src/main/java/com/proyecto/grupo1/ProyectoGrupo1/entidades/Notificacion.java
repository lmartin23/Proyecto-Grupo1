package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import com.proyecto.grupo1.ProyectoGrupo1.dao.NotificacionDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.TipoNotificacion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Document("notificaciones")
@Getter @Setter @ToString
public class Notificacion {
    @Enumerated(EnumType.STRING)
    private TipoNotificacion tipo;
    private String descripcion;
    private Date fecha;
    private boolean vista;

    private Long idCliente;

    public Notificacion() {
    }

    public Notificacion(TipoNotificacion tipo, String descripcion, boolean vista, Long idCliente) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = Calendar.getInstance().getTime();
        this.vista = vista;
        this.idCliente = idCliente;
    }
}
