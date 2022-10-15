package com.proyecto.grupo1.ProyectoGrupo1.logica;


import com.proyecto.grupo1.ProyectoGrupo1.dao.AdministradorDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtLogin;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroCliente;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitadoServiceImpl implements InvitadoService{

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    AdministradorDao adminDao;

    @Autowired
    PasswordSevice passService;

    @Override
    public ObjResponse registrarCliente(DtRegistroCliente dt) {
        boolean existeCorreo = correoRegistrado(dt.getCorreo());
        if(!existeCorreo){
            String pass = passService.hashearPassword(dt.getContrasena());
            Cliente cli = new Cliente(dt.getDocumento(), dt.getNombre(), dt.getApellido(), dt.getFechaNacimiento(), dt.getCorreo(), pass);
            try{
                clienteDao.save(cli);
                Cliente c = clienteDao.findClienteByCorreoIgnoreCase(cli.getCorreo());
                return new ObjResponse("Exito", HttpStatus.CREATED.value(),c.getId());
            }catch (Exception e){
                return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
            }
        }
        return new ObjResponse("Error, ya existe un usuario registrado con los datos ingresados", HttpStatus.BAD_REQUEST.value(),null);
    }

    @Override
    public ObjResponse login(DtLogin dtLogin) {
        Cliente c = clienteDao.findClienteByCorreoIgnoreCase(dtLogin.getCorreo());
        if(c!=null){
            if (passService.verificarHash(c.getContrasena(), dtLogin.getContrasena())){
                return new ObjResponse("Exito", HttpStatus.OK.value(), c.getId());
            }
        }
        return new ObjResponse("Error, usuario o contrasena incorrecto", HttpStatus.BAD_REQUEST.value(),null);
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

    public boolean correoRegistrado(String correo){
        if(clienteDao.findClienteByCorreoIgnoreCase(correo) != null || adminDao.findClienteByCorreoIgnoreCase(correo) != null){
            return true;
        }
        return false;
    }

}
