package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministradorController {
    @Autowired
    AdministradorService admServ;

    @RequestMapping(value = "api/administrado/getPendientes", method = RequestMethod.GET)
    public ObjResponse listarVendedoresRegistradosPendientes(){
        return admServ.vendedoresPendientes();
    }

    @RequestMapping(value = "api/administrado/getVendedores", method = RequestMethod.GET)
    public ObjResponse listarVendedoresRegistrados(){
        return admServ.listadoVendedores();
    }

}
