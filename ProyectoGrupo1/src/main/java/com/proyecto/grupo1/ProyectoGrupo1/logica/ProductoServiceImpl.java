package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.ProductoDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtProducto;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.CategoProd;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.ImagenProducto;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Producto;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    VendedorDao vendedorDao;
    @Autowired
    ProductoDao productoDao;

    @Override
    public ObjResponse altaProducto(DtProducto dtP){
        Vendedor vendedor = vendedorDao.findVendedorById(dtP.getIdVendedor());
        List<ImagenProducto> imagenes = new ArrayList<>();

        Producto producto = new Producto(
                dtP.getNombre(),
                dtP.getDescripcion(),
                dtP.getPrecio(),
                dtP.getMoneda(),
                dtP.getStock(),
                CategoProd.valueOf(dtP.getCategoria()),
                dtP.isActivo(),
                vendedor
        );

        for(String url : dtP.getImagenesUrl()){
            ImagenProducto aux = new ImagenProducto(url, producto);
            imagenes.add(aux);
        }

        producto.setImagenesProducto(imagenes);

        try {
            productoDao.save(producto);
            Producto p = productoDao.findProductoById(producto.getId());
            return new ObjResponse("Exito", HttpStatus.CREATED.value(),p.getId());
        }catch (Exception e) {
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }
}