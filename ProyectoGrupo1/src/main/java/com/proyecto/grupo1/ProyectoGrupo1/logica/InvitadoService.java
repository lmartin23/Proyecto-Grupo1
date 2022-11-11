package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtCliente;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtLogin;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroCliente;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;

import java.util.List;

public interface InvitadoService {

    public ObjResponse registrarCliente(DtRegistroCliente cliente);
    public boolean login(DtLogin dtLogin);
    public DtCliente obtenerCliente(Long id);
    public ObjResponse recuperarContrasena(String correo);
    public ObjResponse registrarAdministrador(String correo, String contra);
}
