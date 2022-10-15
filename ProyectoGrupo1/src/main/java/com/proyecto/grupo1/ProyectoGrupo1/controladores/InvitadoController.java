package com.proyecto.grupo1.ProyectoGrupo1.controladores;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtLogin;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroCliente;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import com.proyecto.grupo1.ProyectoGrupo1.logica.InvitadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvitadoController {

    @Autowired
    InvitadoService serviceInv;

    @RequestMapping(value = "api/invitado/registrar", method = RequestMethod.POST)
    public ObjResponse registrarCliente(@RequestBody DtRegistroCliente dt){
        return serviceInv.registrarCliente(dt);
    }

    @RequestMapping(value = "api/invitado/login", method = RequestMethod.POST)
    public ObjResponse login(@RequestBody DtLogin dtLogin){
        return serviceInv.login(dtLogin);
    }

    @RequestMapping(value = "api/invitado/getall", method = RequestMethod.GET)
    public List<Cliente> listarRegistrados(){
        return serviceInv.obtenerClientes();
    }

}