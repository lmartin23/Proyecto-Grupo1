package com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DtRegistroVendedor {
    private Long idCliente;
    private String nombreComercial;
    List<DtDireccion> direcciones;

    public DtRegistroVendedor(Long idCliente, String nombreComercial, List<DtDireccion> direcciones) {
        this.idCliente = idCliente;
        this.nombreComercial = nombreComercial;
        this.direcciones = direcciones;
    }

    public DtRegistroVendedor() {
    }

}
