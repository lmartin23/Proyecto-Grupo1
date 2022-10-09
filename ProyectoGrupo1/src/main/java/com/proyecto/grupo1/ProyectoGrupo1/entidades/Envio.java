package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter @Setter @ToString
public class Envio extends MetodoEntrega{
    private boolean confirmado;

    public Envio() {
    }

    public Envio(boolean confirmado) {
        this.confirmado = confirmado;
    }
}
