package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtEntregaCompra;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    @Autowired
    VentaService ventaService;

    @RequestMapping(value = "/listarVentasEnvioPendiente", method = RequestMethod.GET)
    public ObjResponse listarVentasEnvioPendiente(@RequestParam Long idVendedor){
        return ventaService.listarVentasEnvioPendiente(idVendedor);
    }

    @RequestMapping(value = "/setearEntrega", method = RequestMethod.PUT)
    public ObjResponse setearEntrega(@RequestBody DtEntregaCompra dtEC){
        return ventaService.setearEntrega(dtEC);
    }

}
