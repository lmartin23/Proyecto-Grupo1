package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.CalificacionDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.CompraDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtCalificacion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Calificacion;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    @Autowired
    CalificacionDao calificacionDao;
    @Autowired
    CompraDao compraDao;
    @Autowired
    VendedorDao vendedorDao;
    @Autowired
    ClienteDao clienteDao;

    @Override
    public ObjResponse calificar(DtCalificacion dtC){
        Compra compra = compraDao.findCompraById(dtC.getIdCompra());

        Calificacion calificacion = new Calificacion(
                compra,
                dtC.getEstrellas(),
                dtC.getComentario()
        );

        if(dtC.getIdVendedor() != null){
            //es calificación para el vendedor
            calificacion.setVendedor(vendedorDao.findVendedorById(dtC.getIdVendedor()));

        } else if (dtC.getIdCliente() != null){
            //es calificación para el cliente
            calificacion.setCliente(clienteDao.findClienteById(dtC.getIdCliente()));
        }

        try {
            calificacionDao.save(calificacion);
            return new ObjResponse("Exito", HttpStatus.OK.value(), null);
        }catch (Exception e){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse modificar(DtCalificacion dtC){
        Compra compra = compraDao.findCompraById(dtC.getIdCompra());
        Calificacion calificacion = new Calificacion();

        if(dtC.getIdVendedor() != null){
            //es calificación para el vendedor
            calificacion = calificacionDao.findCalificacionByCompraAndVendedor(compra, vendedorDao.findVendedorById(dtC.getIdVendedor()));
        } else if (dtC.getIdCliente() != null){
            //es calificación para el cliente
            calificacion = calificacionDao.findCalificacionByCompraAndCliente(compra, clienteDao.findClienteById(dtC.getIdCliente()));
        }

        calificacion.setEstrellas(dtC.getEstrellas());
        calificacion.setComentario(dtC.getComentario());

        try {
            calificacionDao.save(calificacion);
            return new ObjResponse("Exito", HttpStatus.OK.value(), null);
        }catch (Exception e){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse eliminar(DtCalificacion dtC){
        Compra compra = compraDao.findCompraById(dtC.getIdCompra());
        Calificacion calificacion = new Calificacion();

        if(dtC.getIdVendedor() != null){
            //es calificación para el vendedor
            calificacion = calificacionDao.findCalificacionByCompraAndVendedor(compra, vendedorDao.findVendedorById(dtC.getIdVendedor()));
        } else if (dtC.getIdCliente() != null){
            //es calificación para el cliente
            calificacion = calificacionDao.findCalificacionByCompraAndCliente(compra, clienteDao.findClienteById(dtC.getIdCliente()));
        }

        try {
            calificacionDao.delete(calificacion);
            return new ObjResponse("Exito", HttpStatus.OK.value(), null);
        }catch (Exception e){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }
}
