package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.DireccionDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroDireccion;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtRegistroVendedor;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.TipoUsuario;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Direccion;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    VendedorDao vendedorDao;

    @Autowired
    DireccionDao dirDao;

    @Override
    public ObjResponse ingresarDireccion(DtRegistroDireccion dt) {
        Cliente c = clienteDao.findClienteById(dt.getIdUsuario());
        if(c == null ){
            return new ObjResponse("Error, no existe el usuario ingresado", HttpStatus.BAD_REQUEST.value(), null);
        }
        try {
            for (DtDireccion dtDir : dt.getDirecciones()) {
                Direccion dir = new Direccion(dtDir.getCalle(), dtDir.getNumero(), dtDir.getApto(), dtDir.getBarrio(), dtDir.getCiudad(), dtDir.getDepartamento(), dtDir.isPrincipal(), TipoUsuario.VENDEDOR);
                dir.setCliente(c);
                dirDao.save(dir);
                //c..getDirecciones().add(dir);
            }
            return new ObjResponse("Se ha registrado la/s direccion/es", HttpStatus.OK.value(), null);
        }catch (Exception e){
            return new ObjResponse("Error al ingresar direccion", HttpStatus.BAD_REQUEST.value(), null);
        }
    }

    @Override
    public ObjResponse consultarDirecciones(Long idCliente){
        List<Direccion> direcciones = dirDao.getAllByCliente_Id(idCliente);
        List<DtDireccion> ret = new ArrayList<DtDireccion>();

        for(Direccion d : direcciones){
            DtDireccion aux = new DtDireccion(
                    d.getCalle(),
                    d.getNumero(),
                    d.getApto(),
                    d.getBarrio(),
                    d.getCiudad(),
                    d.getDepartamento(),
                    d.isPrincipal()
            );
            ret.add(aux);
        }

        try{
            return new ObjResponse("Exito", HttpStatus.OK.value(), ret);
        }catch (Exception e){
            return new ObjResponse("Error genérico", HttpStatus.BAD_REQUEST.value(), null);
        }
    }

}
