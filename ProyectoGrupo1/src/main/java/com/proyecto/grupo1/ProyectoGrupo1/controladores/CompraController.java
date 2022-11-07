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

    @RequestMapping(value = "/pendientesDeElegirEntrega", method = RequestMethod.GET)
    public ObjResponse listarComprasPendientesDeAsignarEntrega(@RequestParam Long idC){
        return compraService.listarComprasPendientesDeAsignarEntrega(idC);
    }

    @RequestMapping(value = "/asignarMetodoEntrega", method = RequestMethod.POST)
    public ObjResponse asignarMetodoEnrega(
            @RequestParam Long idCompra,
            @RequestParam String tipoEntrega,
            @RequestParam(required = false) Long idDireccion){
        return compraService.asignarMetodoEntrega(idCompra, tipoEntrega, idDireccion);
    }

    @RequestMapping(value = "/listarComprasEntregaPendiente", method = RequestMethod.GET)
    public ObjResponse comprasPendientesDeRecibir(@RequestParam Long idCliente){
        return compraService.listarComprasPendientesDeRecibir(idCliente);
    }

    @RequestMapping(value = "/confirmarCompraRecibida", method = RequestMethod.POST)
    public ObjResponse confirmarRecibo(
            @RequestParam Long idCompra){
        return compraService.confirmarCompraRecibida(idCompra);
    }

    @RequestMapping(value = "/listarComprasFinalizadas", method = RequestMethod.GET)
    public ObjResponse listarComprasFinalizadas(@RequestParam Long idCliente){
        return compraService.listarComprasFinalizadas(idCliente);
    }
}
