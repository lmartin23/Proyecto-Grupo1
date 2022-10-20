package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "/**")
public class ClienteController {
    @Autowired
    ClienteService cliService;

    @RequestMapping(value = "api/cliente/altadireccion", method = RequestMethod.POST)
    public ObjResponse insertar(@RequestBody Long idCli, List<DtDireccion> direcciones) {
        return cliService.ingresarDireccion(idCli, direcciones);
    }

    @RequestMapping(value = "api/cliente/registrarVendedor", method = RequestMethod.POST)
    public ObjResponse registrarVendedor(@RequestParam Long idUsr,@RequestParam String nombreComercial, @RequestParam boolean habilitaEnvio) {
        return cliService.registrarseComoVendedor(idUsr, nombreComercial, habilitaEnvio);
    }
}
