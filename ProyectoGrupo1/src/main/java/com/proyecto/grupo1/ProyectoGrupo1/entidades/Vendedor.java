package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "idx_vendedor_idvendedor", columnList = "id")
})
@Getter @Setter @ToString
@PrimaryKeyJoinColumn(name="cliente_id", referencedColumnName = "id")
public class Vendedor extends Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreComercial;
    private boolean habilitado;
    private boolean habilitaEnvio;
    private double saldo;

    @OneToMany(mappedBy = "vendedor", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
    private List<Calificacion> calificacionesVendedor = new ArrayList<Calificacion>();;

    public Vendedor() {
        super();
    }

    public Vendedor(Long idVendedor, String nombreComercial, boolean habilitado, boolean habilitaEnvio, double saldo, List<Calificacion> calificacionesVendedor) {
        this.id = idVendedor;
        this.nombreComercial = nombreComercial;
        this.habilitado = habilitado;
        this.habilitaEnvio = habilitaEnvio;
        this.saldo = saldo;
        this.calificacionesVendedor = calificacionesVendedor;
    }
}
