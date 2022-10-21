package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.AdministradorDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.DireccionDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.*;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.TipoUsuario;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class InvitadoServiceImpl implements InvitadoService{

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    AdministradorDao adminDao;

    @Autowired
    PasswordSevice passService;

    @Autowired
    DireccionDao dirDao;
    @Override
    public ObjResponse registrarCliente(DtRegistroCliente dt) {

        if (!correoRegistrado(dt.getCorreo()) && !documentoRegistrado(dt.getDocumento())) {
            if (!dt.getDirecciones().isEmpty()) {
                String pass = passService.hashearPassword(dt.getContrasena());
                Cliente cli = new Cliente(dt.getDocumento(), dt.getNombre(), dt.getApellido(), dt.getFechaNacimiento(), dt.getCorreo(), pass);
                try {
                    clienteDao.save(cli);
                    for (DtDireccion dtDir : dt.getDirecciones()) {
                        Direccion dir = new Direccion(dtDir.getCalle(), dtDir.getNumero(), dtDir.getApto(), dtDir.getBarrio(), dtDir.getCiudad(), dtDir.getDepartamento(), dtDir.isPrincipal(), TipoUsuario.CLIENTE);
                        dir.setCliente(cli);
                        dirDao.save(dir);
                        //cli.getDirecciones().add(dir); Probar getDirecciones
                    }

                    Cliente c = clienteDao.findClienteByCorreoIgnoreCase(cli.getCorreo());
                    return new ObjResponse("Exito", HttpStatus.CREATED.value(), c.getId());
                } catch (Exception e) {
                    return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(), null);
                }
            }
            return new ObjResponse("Error, no se ha ingresado direccion", HttpStatus.BAD_REQUEST.value(), null);
        }

        return new ObjResponse("Error, ya existe un usuario registrado con los datos ingresados documento o correo", HttpStatus.BAD_REQUEST.value(), null);
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
    public DtCliente obtenerCliente(Long id) {
        Cliente c = clienteDao.findClienteById(id);
        if (c != null) {
            return c.obtenerDt();
        }
        return null;
    }

    public boolean correoRegistrado(String correo){
        return clienteDao.findClienteByCorreoIgnoreCase(correo) != null || adminDao.findClienteByCorreoIgnoreCase(correo) != null;
    }
    public boolean documentoRegistrado(String documento) {
        return clienteDao.findClienteByDocumentoIgnoreCase(documento) != null || adminDao.findAdministradorByDocumentoIgnoreCase(documento) != null;
    }
}
