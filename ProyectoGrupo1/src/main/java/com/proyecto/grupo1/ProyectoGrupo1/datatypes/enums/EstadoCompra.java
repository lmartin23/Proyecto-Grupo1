package com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums;

public enum EstadoCompra {

    CANCELADA("Cancelada"),
    NO_ENTREGADA("No entregada"),
    ENVIO_PENDIENTE("Envío seteado o a la espera de ser retirada"),

    ENTREGADA("Entregada"),
    PROCESANDO("Procesando");

    private final String descripcion;

    private EstadoCompra(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
