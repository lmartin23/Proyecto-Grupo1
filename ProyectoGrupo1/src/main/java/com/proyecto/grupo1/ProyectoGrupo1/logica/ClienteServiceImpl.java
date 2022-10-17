package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.DireccionDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    DireccionDao dirDao;

    @Override
    public ObjResponse ingresarDireccion(Long idUsr, List<DtDireccion> direcciones) {
        try {
            Cliente cli = clienteDao.findClienteById(idUsr);
            if (cli != null && !direcciones.isEmpty()) {
                for (DtDireccion dt : direcciones) {
                    Direccion d = new Direccion(dt.getCalle(), dt.getNumero(), dt.getApto(), dt.getBarrio(), dt.getCiudad(), dt.getDepartamento(), dt.isPrincipal());
                    dirDao.save(d);
                    cli.getDirecciones().add(d);
                }
                clienteDao.save(cli);

                return new ObjResponse("OK, se han ingresado direcciones", HttpStatus.CREATED.value(),null);
            } else {
                return new ObjResponse("Error, id no encontrado o no se han ingresado direcciones", HttpStatus.BAD_REQUEST.value(), null);
            }
        } catch (Exception e) {
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(), null);
        }
    }
}
