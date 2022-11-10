package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.logica.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministradorController {
    @Autowired
    AdministradorService admServ;

    @RequestMapping(value = "api/administrador/getPendientes", method = RequestMethod.GET)
    public ObjResponse listarVendedoresRegistradosPendientes(){
        return admServ.vendedoresPendientes();
    }

    @RequestMapping(value = "api/administrador/getAprobados", method = RequestMethod.GET)
    public ObjResponse listarVendedoresRegistradosAprobados(){
        return admServ.vendedoresAprobados();
    }

    @RequestMapping(value = "api/administrador/getVendedores", method = RequestMethod.GET)
    public ObjResponse listarVendedoresRegistrados(){
        return admServ.listadoVendedores();
    }

    @RequestMapping(value = "api/administrador/cambiarEstadoPendientes", method = RequestMethod.POST)
    public ObjResponse cambiarEstadoVendedoresPendientes(@RequestParam Long idVendedor,
                                                   @RequestParam boolean aprobado){
        return admServ.cambiarEstadoVendedor(idVendedor, aprobado);
    }
}
