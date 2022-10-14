package com.proyecto.grupo1.ProyectoGrupo1.logica;


import com.proyecto.grupo1.ProyectoGrupo1.dao.AdministradorDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.DtLogin;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.DtRegistroCliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean registrarCliente(DtRegistroCliente dt) {
        boolean existeCorreo = correoRegistrado(dt.getCorreo());
        if(!existeCorreo){
            String pass = passService.hashearPassword(dt.getContrasena());
            Cliente cli = new Cliente(dt.getDocumento(), dt.getNombre(), dt.getApellido(), dt.getFechaNacimiento(), dt.getCorreo(), pass);
            clienteDao.save(cli);
            return true;
        }
        return false;
    }

    @Override
    public boolean login(DtLogin dtLogin) {
        Cliente c = clienteDao.findClienteByCorreoIgnoreCase(dtLogin.getCorreo());
        if(c!=null){
            if (passService.verificarHash(c.getContrasena(), dtLogin.getContrasena())){
                return true;
            }
        }
        return false;
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
