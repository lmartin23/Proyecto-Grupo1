package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Direccion", indexes = {
        @Index(name = "idx_direccion_id", columnList = "id")
})
@Getter
@Setter
@ToString
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String calle;
    private int numero;
    private String apto;
    private String barrio;
    private String ciudad;
    private String departamento;
    private boolean principal;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Vendedor vendedor;

    public Direccion() {
    }

    public Direccion(String id, String calle, int numero, String apto, String barrio, String ciudad, String departamento, boolean principal, Cliente cliente, Vendedor vendedor) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.apto = apto;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.principal = principal;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }
}
