package com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class DtProductoCarrito implements Serializable {
    private Long id;
    private Long idProducto;
    private String nombreProducto;
    private int cantidad;
    private double total;

    public DtProductoCarrito() {
    }

    public DtProductoCarrito(Long id, Long idProducto, String nombreProducto, int cantidad, double total) {
        this.id = id;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.total = total;
    }
}
