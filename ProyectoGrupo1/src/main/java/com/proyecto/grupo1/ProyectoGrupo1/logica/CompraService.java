package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtPago;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;

public interface CompraService {
    public ObjResponse confirmarProductosCarrito(DtPago dtP);

    public ObjResponse comprasPendientesDeElegirEnrega(Long idC);

    public ObjResponse asignarMetodoEnrega(Long idCompra, String tipoEntrega, Long idDireccion);
    public ObjResponse confirmarCompraRecibida(Long idCompra);


}
