package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtPago;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    @Autowired
    CompraService compraService;

    @RequestMapping(value = "/confirmarCompra", method = RequestMethod.POST)
    public ObjResponse confirmarProductosCarrito(@RequestBody DtPago dtP){
        return compraService.confirmarProductosCarrito(dtP);
    }

    @RequestMapping(value = "/pendientesDeElegirEnrega", method = RequestMethod.GET)
    public ObjResponse comprasPendientesDeElegirEnrega(@RequestParam Long idC){
        return compraService.comprasPendientesDeElegirEnrega(idC);
    }

    @RequestMapping(value = "/asignarMetodoEnrega", method = RequestMethod.POST)
    public ObjResponse asignarMetodoEnrega(
            @RequestParam Long idCompra,
            @RequestParam String tipoEntrega,
            @RequestParam(required = false) Long idDireccion){
        System.out.println("tipoentrega = "+tipoEntrega);
        return compraService.asignarMetodoEnrega(idCompra, tipoEntrega, idDireccion);
    }
}
