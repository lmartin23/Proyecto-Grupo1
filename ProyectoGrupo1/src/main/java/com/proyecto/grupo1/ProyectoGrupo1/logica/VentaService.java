package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtEntregaCompra;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;

public interface VentaService {

    public ObjResponse listarVentasEntregaPendienteSinMarcar(Long idVendedor);
    public ObjResponse listarVentasEntregaPendienteYMarcada(Long idVendedor);
    public ObjResponse listarVentasFinalizadas(Long idVendedor);
    public ObjResponse setearEntrega(DtEntregaCompra dtEC);
    public ObjResponse listarVentasVendedor(Long idVendedor);

}
