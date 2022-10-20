package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vendedor extends Cliente {

    private String nombreComercial;
    private boolean habilitado;
    private boolean habilitaEnvio;
    private double saldo;

    @OneToMany(mappedBy = "vendedor", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private List<Calificacion> calificacionesVendedor = new ArrayList<Calificacion>();

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direcciones = new ArrayList<Direccion>();

    public Vendedor() {
        super();
    }

    public Vendedor( String nombreComercial, boolean habilitado, boolean habilitaEnvio, double saldo, List<Calificacion> calificacionesVendedor) {
        this.nombreComercial = nombreComercial;
        this.habilitado = habilitado;
        this.habilitaEnvio = habilitaEnvio;
        this.saldo = saldo;
        this.calificacionesVendedor = calificacionesVendedor;
    }

    public Vendedor(Long idCliente, String nombreComercial, boolean habilitado, boolean habilitaEnvio) {
        this.setId(idCliente);
        this.nombreComercial = nombreComercial;
        this.habilitado = habilitado;
        this.habilitaEnvio = habilitaEnvio;
    }
}
