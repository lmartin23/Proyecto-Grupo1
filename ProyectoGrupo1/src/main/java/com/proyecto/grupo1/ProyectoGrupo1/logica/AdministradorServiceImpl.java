package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtVendedorBO;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.MailRequest;
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
    @Autowired
    MailService mailService;

    @Override
    public ObjResponse vendedoresPendientes() {
        List<Vendedor> listaPendientes = vendedorDao.findAllByHabilitado(false);

        if(listaPendientes.isEmpty()){
            return new ObjResponse("No se han encontrado usuarios para listar", HttpStatus.NO_CONTENT.value(), null);
        }
        List<DtVendedorBO> resultados= new ArrayList<>();
        for(Vendedor v : listaPendientes){
            DtVendedorBO dt = new DtVendedorBO(v.getId(),v.getNombreComercial(), v.isHabilitado());
            resultados.add(dt);
        }
        return new ObjResponse("Exito", HttpStatus.OK.value(), resultados);

    }

    @Override
    public ObjResponse vendedoresAprobados() {
        List<Vendedor> listaPendientes = vendedorDao.findAllByHabilitado(true);

        if(listaPendientes.isEmpty()){
            return new ObjResponse("No se han encontrado usuarios para listar", HttpStatus.NO_CONTENT.value(), null);
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
            return new ObjResponse("No se han encontrado usuarios para listar", HttpStatus.NO_CONTENT.value(), null);
        }
        List<DtVendedorBO> resultados= new ArrayList<>();
        for(Vendedor v : listaVendedores){
            DtVendedorBO dt = new DtVendedorBO(v.getId(),v.getNombreComercial(), v.isHabilitado());
            resultados.add(dt);
        }
        return new ObjResponse("Exito", HttpStatus.OK.value(), resultados);
    }

    @Override
    public ObjResponse cambiarEstadoVendedor(Long idVendedor, boolean aprobado) {
        Vendedor v = vendedorDao.findVendedorById(idVendedor);
        if(v == null){
            return new ObjResponse("No se ha encontrado usuario", HttpStatus.BAD_REQUEST.value(), null);
        }
        try {
            //PARTE DE ENVIO DE MAIL
            String mensaje = "";
            String asuntoMail = "ClickShop - resultado de solicitud de registro";
            String bodyMail="";
            if(aprobado){
                v.setHabilitado(aprobado);
                vendedorDao.save(v);
                mensaje = "Se ha habilitado al vendedor correctamente";
                bodyMail = "Bienvenido a CLickShop, le informamos que su solicitud de registro ha sido Aprobada :D.";
            }else{
                vendedorDao.delete(v);
                mensaje = "Se ha deshabilitado al vendedor correctamente";
                bodyMail = "CLickShop le informa que su solicitud de registro ha sido Rechazada :|.";
            }
            String msjOpc = enviarMailVendedor(v.getCliente().getCorreo(),asuntoMail, bodyMail );
            return new ObjResponse(mensaje + msjOpc, HttpStatus.OK.value(), null);
        }catch (Exception e){
            return new ObjResponse("Error inesperado", HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
    }

    public String enviarMailVendedor(String correo, String asunto, String mensaje){
        MailRequest mail = new MailRequest();
        mail.setTo(correo);
        mail.setSubject(asunto);
        mail.setText(mensaje);
        try {
            mailService.sendMail(mail);
            return "";
        }catch (Exception e){
            return "\n No se ha podido enviar correo al vendedor para notificarlo.";
        }

    }

}
