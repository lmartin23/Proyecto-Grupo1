package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;

public interface AdministradorService {

    public ObjResponse vendedoresPendientes();
    public ObjResponse vendedoresAprobados();
    public ObjResponse listadoVendedores();
    public ObjResponse cambiarEstadoVendedor(Long idVendedor, boolean aprobado);
    public ObjResponse listarUsuarios();

    public ObjResponse bloquearDesbloquerUsuerios(String correo, String rol, boolean bloqueado);

}
