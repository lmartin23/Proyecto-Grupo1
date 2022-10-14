package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.DtLogin;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.DtRegistroCliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;

import java.util.List;

public interface InvitadoService {

    public boolean registrarCliente(DtRegistroCliente cliente);
    public boolean login(DtLogin dtLogin);
    public List<Cliente> obtenerClientes();
}
