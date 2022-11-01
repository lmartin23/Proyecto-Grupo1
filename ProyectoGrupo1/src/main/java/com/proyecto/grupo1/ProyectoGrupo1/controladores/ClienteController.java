package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroVendedor;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    ClienteService cliService;

    @RequestMapping(value = "api/cliente/altadireccion", method = RequestMethod.POST)
    public ObjResponse insertar(@RequestBody DtRegistroDireccion dt) {
        return cliService.ingresarDireccion(dt);
    }

    @RequestMapping(value = "api/cliente/consultarDirecciones", method = RequestMethod.GET)
    public ObjResponse insertar(@RequestParam Long idCliente) {
        return cliService.consultarDirecciones(idCliente);
    }
}
