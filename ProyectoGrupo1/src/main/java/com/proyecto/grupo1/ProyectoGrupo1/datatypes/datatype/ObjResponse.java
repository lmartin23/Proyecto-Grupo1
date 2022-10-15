package com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ObjResponse {
    private String mensaje;
    private Integer codeHttp;
    private Object objeto;

    public ObjResponse(String mensaje, Integer codigo, Object objeto) {
        this.mensaje = mensaje;
        this.codeHttp = codigo;
        this.objeto = objeto;
    }

    public ObjResponse() {
    }
}
