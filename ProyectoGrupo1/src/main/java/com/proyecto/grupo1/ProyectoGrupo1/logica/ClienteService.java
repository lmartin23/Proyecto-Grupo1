package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;

import java.util.List;

public interface ClienteService {
    public ObjResponse ingresarDireccion(Long idUsr, List<DtDireccion> direcciones);
}
