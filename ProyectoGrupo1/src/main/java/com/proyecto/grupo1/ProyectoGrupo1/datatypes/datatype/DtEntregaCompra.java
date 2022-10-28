package com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DtEntregaCompra {
    private Long idCompra;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime fechaHoraDesde;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime fechaHoraHasta;


}
