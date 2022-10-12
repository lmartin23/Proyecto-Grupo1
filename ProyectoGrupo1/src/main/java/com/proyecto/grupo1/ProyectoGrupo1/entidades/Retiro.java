package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter @Setter @ToString
public class Retiro extends MetodoEntrega{
    private boolean confirmado;

    @OneToOne(optional = false)
    private Compra compra;

    public Retiro(Long id, Date fechaDesde, Date fechaHasta, boolean confirmado) {
        super(id, fechaDesde, fechaHasta);
        this.confirmado = confirmado;
    }

    public Retiro(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Retiro() {

    }
}
