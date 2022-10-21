package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtProducto;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;

import java.util.List;

public interface ProductoService {

    public ObjResponse altaProducto(DtProducto dtP);

    public ObjResponse listar();

    public ObjResponse listarTodos();

}