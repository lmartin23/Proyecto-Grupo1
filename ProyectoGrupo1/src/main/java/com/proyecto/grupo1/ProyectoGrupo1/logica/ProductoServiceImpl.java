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

    @Override
    public ObjResponse bajaProducto(Long idProducto, Long idVendedor){
        Producto producto = productoDao.findProductoById(idProducto);

        if(producto.getVendedor().getId() == idVendedor){
            producto.setActivo(false);
        } else {
            return new ObjResponse("Error: El Vendedor no tiene permisos para modificar este Producto", HttpStatus.BAD_REQUEST.value(),null);
        }

        try {
            productoDao.save(producto);
            Producto p = productoDao.findProductoById(producto.getId());
            return new ObjResponse("Producto dado de baja", HttpStatus.OK.value(),p.getId());
        }catch (Exception e) {
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse listar(){
        List<DtProducto> ret = new ArrayList<DtProducto>();
        List<Producto> productos = productoDao.getAllByActivo(true);


        for(Producto p : productos){
            List<String> urls = new ArrayList<String>();

            for(ImagenProducto img : p.getImagenesProducto()){
                urls.add(img.getUrl());
            }

            DtProducto dtP = new DtProducto(
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getPrecio(),
                    p.getStock(),
                    p.getCategoria().toString(),
                    p.isActivo(),
                    p.getVendedor().getId(),
                    urls
            );

            ret.add(dtP);
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(),ret);
        }catch (Exception e) {
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse listarTodos(){

        List<DtProducto> ret = new ArrayList<DtProducto>();
        List<Producto> productos = productoDao.getAllBy();

        for(Producto p : productos){
            List<String> urls = new ArrayList<String>();

            for(ImagenProducto img : p.getImagenesProducto()){
                urls.add(img.getUrl());
            }

            DtProducto dtP = new DtProducto(
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getPrecio(),
                    p.getStock(),
                    p.getCategoria().toString(),
                    p.isActivo(),
                    p.getVendedor().getId(),
                    urls
            );

            ret.add(dtP);
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(),ret);
        }catch (Exception e) {
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse listarPorVendedor(Long idVendedor){
        List<DtProducto> ret = new ArrayList<DtProducto>();
        List<Producto> productos = productoDao.getAllByVendedor_Id(idVendedor);


        for(Producto p : productos){
            List<String> urls = new ArrayList<String>();

            for(ImagenProducto img : p.getImagenesProducto()){
                urls.add(img.getUrl());
            }

            DtProducto dtP = new DtProducto(
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getPrecio(),
                    p.getStock(),
                    p.getCategoria().toString(),
                    p.isActivo(),
                    p.getVendedor().getId(),
                    urls
            );

            ret.add(dtP);
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(),ret);
        }catch (Exception e) {
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse listarPorCategoria(CategoProd categoria){
        List<DtProducto> ret = new ArrayList<DtProducto>();
        List<Producto> productos = productoDao.getAllByCategoriaAndActivoIsTrue(categoria);


        for(Producto p : productos){
            List<String> urls = new ArrayList<String>();

            for(ImagenProducto img : p.getImagenesProducto()){
                urls.add(img.getUrl());
            }

            DtProducto dtP = new DtProducto(
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getPrecio(),
                    p.getStock(),
                    p.getCategoria().toString(),
                    p.isActivo(),
                    p.getVendedor().getId(),
                    urls
            );

            ret.add(dtP);
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(),ret);
        }catch (Exception e) {
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }
    @Override
    public ObjResponse buscarPorNombre(String nombre){
        List<DtProducto> ret = new ArrayList<DtProducto>();
        List<Producto> productos = productoDao.findProductoByNombreContainingAndActivoIsTrue(nombre);


        for(Producto p : productos){
            List<String> urls = new ArrayList<String>();

            for(ImagenProducto img : p.getImagenesProducto()){
                urls.add(img.getUrl());
            }

            DtProducto dtP = new DtProducto(
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getPrecio(),
                    p.getStock(),
                    p.getCategoria().toString(),
                    p.isActivo(),
                    p.getVendedor().getId(),
                    urls
            );

            ret.add(dtP);
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(),ret);
        }catch (Exception e) {
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse buscarPorNombreYVendedor(String nombre, Long idVendedor){
        List<DtProducto> ret = new ArrayList<DtProducto>();
        List<Producto> productos = productoDao.findProductoByNombreContainingAndVendedor_Id(nombre, idVendedor);


        for(Producto p : productos){
            List<String> urls = new ArrayList<String>();

            for(ImagenProducto img : p.getImagenesProducto()){
                urls.add(img.getUrl());
            }

            DtProducto dtP = new DtProducto(
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getPrecio(),
                    p.getStock(),
                    p.getCategoria().toString(),
                    p.isActivo(),
                    p.getVendedor().getId(),
                    urls
            );

            ret.add(dtP);
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(),ret);
        }catch (Exception e) {
            return new ObjResponse("Error inesperado", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

}