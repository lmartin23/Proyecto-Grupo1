package com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class DtCompra {
    private Long id;
    private Date fecha;
    private String nombreProducto;
    private int cantidad;
    private double total;
    private List<String> metodosEntrega;

    private DtEntregaCompra entrega;

    public DtCompra(Long id, Date fecha, String nombreProducto, int cantidad, double total, List<String> metodosEntrega) {
        this.id = id;
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.total = total;
        this.metodosEntrega = metodosEntrega;
    }

    public DtCompra(Long id, Date fecha, String nombreProducto, int cantidad, double total, DtEntregaCompra entrega) {
        this.id = id;
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.total = total;
        this.entrega = entrega;
    }

    public DtCompra(Long id, Date fecha, String nombreProducto, int cantidad, double total) {
        this.id = id;
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.total = total;
    }
}
