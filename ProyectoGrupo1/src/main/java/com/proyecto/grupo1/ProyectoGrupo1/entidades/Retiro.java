package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter @Setter @ToString
public class Retiro extends MetodoEntrega{
    private boolean confirmado;

    public Retiro(String id, Date fechaDesde, Date fechaHasta, boolean confirmado) {
        super(id, fechaDesde, fechaHasta);
        this.confirmado = confirmado;
    }

    public Retiro(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Retiro() {

    }
}
