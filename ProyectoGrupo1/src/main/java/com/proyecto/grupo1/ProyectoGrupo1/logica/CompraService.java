package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtPago;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;

public interface CompraService {
    public ObjResponse confirmarProductosCarrito(DtPago dtP);

}
