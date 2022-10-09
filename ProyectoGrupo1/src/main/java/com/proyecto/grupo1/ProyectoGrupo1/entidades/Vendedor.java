package com.proyecto.grupo1.ProyectoGrupo1.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = {
        @Index(name = "idx_vendedor_id", columnList = "id")
})
@Getter @Setter @ToString
public class Vendedor extends Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nombreComercial;
    private boolean habilitado;
    private boolean habilitaEnvio;
    private double saldo;

    public Vendedor(String id, String documento, String tipoDocumento, String nombre, String apellido, Date fechaNacimiento, String correo, String contraseña, boolean bloqueado, boolean correoValidado, boolean envioDomicilio) {
        super(id, documento, tipoDocumento, nombre, apellido, fechaNacimiento, correo, contraseña, bloqueado, correoValidado, envioDomicilio);
    }

    public Vendedor() {
        super();
    }
}
