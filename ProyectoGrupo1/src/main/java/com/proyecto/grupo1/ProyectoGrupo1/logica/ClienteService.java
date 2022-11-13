package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroVendedor;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;

import java.util.List;

public interface ClienteService {
    public ObjResponse ingresarDireccion(DtRegistroDireccion dt);
    public ObjResponse consultarDirecciones(Long id);
    public ObjResponse modificarDireccion(DtDireccion dtD);


}
