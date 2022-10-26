package com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtVendedorBO {
    private Long idVendedor;
    private String nombreComercial;
    private boolean habilitado;

    public DtVendedorBO(Long idVendedor, String nombreComercial, boolean habilitado) {
        this.idVendedor = idVendedor;
        this.nombreComercial = nombreComercial;
        this.habilitado = habilitado;
    }
}
