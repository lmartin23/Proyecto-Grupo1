package com.proyecto.grupo1.ProyectoGrupo1.dao;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoProdCarrito;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Producto;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.ProductoCarrito;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCarritoDao extends CrudRepository<ProductoCarrito, String> {

    ProductoCarrito findProductoCarritoById(Long id);

    ProductoCarrito findProductoCarritoByProductoAndEstado(Producto p, EstadoProdCarrito e);
}
