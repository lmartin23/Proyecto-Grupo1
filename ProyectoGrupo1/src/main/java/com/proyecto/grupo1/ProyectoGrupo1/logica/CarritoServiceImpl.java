package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ProductoCarritoDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ProductoDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtProductoCarrito;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoProdCarrito;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.exception.NoHayStockException;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Producto;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.ProductoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    ProductoDao productoDao;

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    ProductoCarritoDao pcDao;

    @Override
    public ObjResponse ingresarProductoCarrito(Long idP, Long idC, int cant) throws NoHayStockException{
        Producto p = productoDao.findProductoById(idP);
        Cliente c = clienteDao.findClienteById(idC);

        ProductoCarrito pc = null;
        pc = pcDao.findProductoCarritoByProductoAndClienteAndEstado(p, c, EstadoProdCarrito.AGREGADO);

        if (pc != null){
            pc.setCantidad(pc.getCantidad()+cant);
            pc.setTotal(pc.getCantidad()*p.getPrecio());
        } else {
            pc = new ProductoCarrito(cant, c, p);
        }

        if(p.getStock() < cant)
            throw new NoHayStockException("No hay stock disponible");

        try {
            pcDao.save(pc);
            ProductoCarrito ret = pcDao.findProductoCarritoById(pc.getId());
            return new ObjResponse("Exito", HttpStatus.CREATED.value(),ret.getEstado());
        } catch (Exception e){
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }
    @Override
    public ObjResponse eliminarProductoCarrito(Long idP, Long idC)   {
        Producto p = productoDao.findProductoById(idP);
        Cliente c = clienteDao.findClienteById(idC);
        ProductoCarrito pc = pcDao.findProductoCarritoByProductoAndClienteAndEstado(p, c, EstadoProdCarrito.AGREGADO);
        pc.setEstado(EstadoProdCarrito.ELIMINADO);

        try {
            pcDao.save(pc);
            ProductoCarrito ret = pcDao.findProductoCarritoById(pc.getId());
            return new ObjResponse("Exito", HttpStatus.OK.value(), ret.getEstado());
        } catch (Exception e){
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse consultarCarrito(Long idC)   {
        Cliente c = clienteDao.findClienteById(idC);
        List<ProductoCarrito> carrito = pcDao.findProductoCarritoByClienteAndEstado(c, EstadoProdCarrito.AGREGADO);
        List<DtProductoCarrito> ret = new ArrayList<DtProductoCarrito>();

        for( ProductoCarrito pc : carrito){
            DtProductoCarrito aux = new DtProductoCarrito(
                    pc.getId(),
                    pc.getProducto().getId(),
                    pc.getProducto().getNombre(),
                    pc.getCantidad(),
                    pc.getTotal()
            );
            ret.add(aux);
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(), ret);
        } catch (Exception e){
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }
}
