package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.DireccionDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroVendedor;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.TipoUsuario;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Direccion;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    VendedorDao vendedorDao;

    @Autowired
    DireccionDao dirDao;

    @Override
    public ObjResponse ingresarDireccion(Long idUsr, List<DtDireccion> direcciones) {
        try {
            Cliente cli = clienteDao.findClienteById(idUsr);
            if (cli != null && !direcciones.isEmpty()) {
                for (DtDireccion dt : direcciones) {
                    Direccion d = new Direccion(dt.getCalle(), dt.getNumero(), dt.getApto(), dt.getBarrio(), dt.getCiudad(), dt.getDepartamento(), dt.isPrincipal(), TipoUsuario.CLIENTE);
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

    @Override
    public ObjResponse registrarseComoVendedor(DtRegistroVendedor dt) {
        Cliente c = clienteDao.findClienteById(dt.getIdUsr());
        Vendedor v = new Vendedor(c, dt.getNombreComercial(), dt.isHabilitaEnvio());
        try {
            vendedorDao.save(v);
            try {
                for (DtDireccion dtDir : dt.getDirecciones()) {
                    Direccion dir = new Direccion(dtDir.getCalle(), dtDir.getNumero(), dtDir.getApto(), dtDir.getBarrio(), dtDir.getCiudad(), dtDir.getDepartamento(), dtDir.isPrincipal(), TipoUsuario.VENDEDOR);
                    dir.setVendedor(v);
                    dirDao.save(dir);
                    //cli.getDirecciones().add(dir); Probar getDirecciones
                }
            }catch (Exception e){
                return new ObjResponse("Error al ingresar direccion, debe dar de alta por datos USR", HttpStatus.BAD_REQUEST.value(), null);
            }
            return new ObjResponse("Vendedor registrado", HttpStatus.BAD_REQUEST.value(), vendedorDao.findVendedorByCliente(c).getNombreComercial());
        }catch (Exception e){
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(), null);
        }

    }


}
