package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.ProductoDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtProducto;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.ImagenProducto;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Producto;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    VendedorDao vendedorDao;
    @Autowired
    ProductoDao productoDao;

    @Override
    public boolean altaProducto(DtProducto dtP){
        Optional<Vendedor> vendedor = vendedorDao.findById(dtP.getIdVendedor());
        List<ImagenProducto> imagenes = new ArrayList<>();

        for(String url : dtP.getImagenesUrl()){
            ImagenProducto aux = new ImagenProducto(url);
            imagenes.add(aux);
        }

        Producto producto = new Producto(
                dtP.getNombre(),
                dtP.getDescripcion(),
                dtP.getPrecio(),
                dtP.getMoneda(),
                dtP.getStock(),
                dtP.getCategoria(),
                dtP.isActivo(),
                vendedor.get(),
                imagenes
        );

        try {
            productoDao.save(producto);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}