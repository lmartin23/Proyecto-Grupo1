package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtProducto;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @RequestMapping(value = "/alta", method = RequestMethod.POST)
    public ObjResponse insertar(@RequestBody DtProducto dtP){
            return productoService.altaProducto(dtP);
    }
}