package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtVendedorBO;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService{
    @Autowired
    VendedorDao vendedorDao;

    @Override
    public ObjResponse vendedoresPendientes() {
        List<Vendedor> listaPendientes = vendedorDao.findAllByHabilitado(false);

        if(listaPendientes.isEmpty()){
            return new ObjResponse("No se han encontrado usuarios para listar", HttpStatus.BAD_REQUEST.value(), null);
        }
        List<DtVendedorBO> resultados= new ArrayList<>();
        for(Vendedor v : listaPendientes){
            DtVendedorBO dt = new DtVendedorBO(v.getId(),v.getNombreComercial(), v.isHabilitado());
            resultados.add(dt);
        }
        return new ObjResponse("Exito", HttpStatus.OK.value(), resultados);

    }

    @Override
    public ObjResponse listadoVendedores() {
        List<Vendedor> listaVendedores = vendedorDao.findAll();

        if(listaVendedores.isEmpty()){
            return new ObjResponse("No se han encontrado usuarios para listar", HttpStatus.BAD_REQUEST.value(), null);
        }
        List<DtVendedorBO> resultados= new ArrayList<>();
        for(Vendedor v : listaVendedores){
            DtVendedorBO dt = new DtVendedorBO(v.getId(),v.getNombreComercial(), v.isHabilitado());
            resultados.add(dt);
        }
        return new ObjResponse("Exito", HttpStatus.OK.value(), resultados);
    }
}
