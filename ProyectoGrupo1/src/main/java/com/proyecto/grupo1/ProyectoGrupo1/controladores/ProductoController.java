package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtProducto;
import com.proyecto.grupo1.ProyectoGrupo1.logica.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @RequestMapping(value = "api/producto/alta", method = RequestMethod.POST)
    public boolean insertar(@RequestBody DtProducto dtP){
            return productoService.altaProducto(dtP);
    }
}