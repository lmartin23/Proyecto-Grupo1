package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter @Setter @ToString
public class Retiro extends MetodoEntrega{
    private boolean confirmado;

    public Retiro() {
    }

    public Retiro(boolean confirmado) {
        this.confirmado = confirmado;
    }
}
