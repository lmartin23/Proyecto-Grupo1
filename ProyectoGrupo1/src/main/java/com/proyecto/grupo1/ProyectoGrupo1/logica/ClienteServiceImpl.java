package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.DireccionDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroVendedor;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
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
    DireccionDao dirDao;

    @Autowired
    VendedorDao vendedorDao;
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

    @Override
    public ObjResponse registrarVendedor(DtRegistroVendedor dt){
        try{
            if(clienteDao.findClienteById(dt.getIdCliente()) !=null){
                Vendedor v = new Vendedor(dt.getIdCliente(), dt.getNombreComercial(), false,false);
                try {
                    vendedorDao.save(v);
                    try {
                        for (DtDireccion dtDir : dt.getDirecciones()) {
                            Direccion dir = new Direccion(dtDir.getCalle(), dtDir.getNumero(), dtDir.getApto(), dtDir.getBarrio(), dtDir.getCiudad(), dtDir.getDepartamento(), dtDir.isPrincipal());
                            dir.setVendedor(v);
                            dirDao.save(dir);
                            //v.getDirecciones().add(dir)
                        }
                        try {
                            v = vendedorDao.findVendedorById(dt.getIdCliente());
                            return new ObjResponse("Exito, se ha registrado al nuevo Vendedor", HttpStatus.CREATED.value(), v.getId());
                        }catch (Exception e){
                            return new ObjResponse("Error inesperado al FINAL al buscar VENDEDOR por idCliente", HttpStatus.BAD_REQUEST.value(), null);
                        }
                    }catch (Exception e){
                        return new ObjResponse("Error inesperado alta DIRECCION", HttpStatus.BAD_REQUEST.value(), null);
                    }
                } catch (Exception e) {
                    return new ObjResponse("Error inesperado alta VENDEDOR", HttpStatus.BAD_REQUEST.value(), null);
                }
            }else{
                return new ObjResponse("No se ha encontrado el id ingresado", HttpStatus.BAD_REQUEST.value(), null);
            }

        }catch (Exception e){

        }
        return null;
    }

}
