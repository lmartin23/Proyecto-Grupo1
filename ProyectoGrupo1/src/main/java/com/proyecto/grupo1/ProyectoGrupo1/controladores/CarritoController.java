package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.exception.NoHayStockException;
import com.proyecto.grupo1.ProyectoGrupo1.logica.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    @RequestMapping(value = "/ingresar", method = RequestMethod.POST)
    public ObjResponse ingresarProductoCarrito(
            @RequestParam Long idProducto,
            @RequestParam Long idCliente,
            @RequestParam int cantidad) throws NoHayStockException {
        return carritoService.ingresarProductoCarrito(idProducto, idCliente, cantidad);
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.PUT)
    public ObjResponse eliminarProductoCarrito(
            @RequestBody Long idProducto,
            @RequestBody Long idCliente){
        return carritoService.eliminarProductoCarrito(idProducto, idCliente);
    }
}
