package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtPago;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.CarritoService;
import com.proyecto.grupo1.ProyectoGrupo1.logica.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    @Autowired
    CompraService compraService;

    @RequestMapping(value = "/confirmarCompra", method = RequestMethod.POST)
    public ObjResponse confirmarProductosCarrito(@RequestBody DtPago dtP){
        return compraService.confirmarProductosCarrito(dtP);
    }
}
