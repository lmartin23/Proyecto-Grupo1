package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.AdministradorDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ProductoDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtUsuarioBO;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtVendedorBO;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.Rol;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService{
    final
    VendedorDao vendedorDao;
    final
    MailService mailService;

    final ClienteDao cliDao;

    final AdministradorDao administradorDao;
    final
    ProductoDao prodDao;

    public AdministradorServiceImpl(VendedorDao vendedorDao, MailService mailService, ClienteDao cliDao, AdministradorDao administradorDao, ProductoDao prodDao) {
        this.vendedorDao = vendedorDao;
        this.mailService = mailService;
        this.cliDao = cliDao;
        this.administradorDao = administradorDao;
        this.prodDao = prodDao;
    }


    @Override
    public ObjResponse vendedoresPendientes() {
        List<Vendedor> listaPendientes = vendedorDao.findAllByHabilitadoIsNull();

        if(listaPendientes.isEmpty()){
            return new ObjResponse("No se han encontrado usuarios para listar", HttpStatus.NO_CONTENT.value(), null);
        }
        List<DtVendedorBO> resultados= new ArrayList<>();
        for(Vendedor v : listaPendientes){
            DtVendedorBO dt = new DtVendedorBO(v.getId(),v.getNombreComercial(), v.getHabilitado());
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
            if(v.getHabilitado()== true) {
                DtVendedorBO dt = new DtVendedorBO(v.getId(), v.getNombreComercial(), v.getHabilitado());
                resultados.add(dt);
            }
        }
        return new ObjResponse("Exito", HttpStatus.OK.value(), resultados);
    }

    @Override
    public ObjResponse listadoVendedores() {
        List<Vendedor> listaVendedores = vendedorDao.findAllByHabilitadoIsNotNull();

        if(listaVendedores.isEmpty()){
            return new ObjResponse("No se han encontrado usuarios para listar", HttpStatus.NO_CONTENT.value(), null);
        }
        List<DtVendedorBO> resultados= new ArrayList<>();
        for(Vendedor v : listaVendedores){
            DtVendedorBO dt = new DtVendedorBO(v.getId(),v.getNombreComercial(), v.getHabilitado());
            resultados.add(dt);
        }
        return new ObjResponse("Exito", HttpStatus.OK.value(), resultados);
    }

    @Override
    public ObjResponse cambiarEstadoVendedor(Long idVendedor, boolean aprobado) {
        Vendedor v = vendedorDao.findVendedorById(idVendedor);
        if(v == null || (v.getHabilitado() != null && v.getHabilitado() == false)){
            return new ObjResponse("No se ha encontrado usuario", HttpStatus.BAD_REQUEST.value(), null);
        }else if(v!= null && (v.getHabilitado() != null && v.getHabilitado() == true)){
            return new ObjResponse("El vendedor ya se encuentra autorizado.", HttpStatus.BAD_REQUEST.value(), null);
        }
        try {
            //PARTE DE ENVIO DE MAIL
            String mensaje = "";
            String asuntoMail = "ClickShop - resultado de solicitud de registro";
            String bodyMail="";
            if(aprobado){
                v.setHabilitado(aprobado);
                vendedorDao.save(v);
                this.habilitarProductos(v.getId());
                mensaje = "Se ha habilitado al vendedor correctamente";
                bodyMail = "Bienvenido a CLickShop, le informamos que su solicitud de registro ha sido Aprobada :D.";
            }else{
                v.setHabilitado(false);
                vendedorDao.save(v);
                mensaje = "Se ha deshabilitado al vendedor correctamente";
                bodyMail = "CLickShop le informa que su solicitud de registro ha sido Rechazada :|.";
            }
            String msjOpc = enviarMailVendedor(v.getCliente().getCorreo(),asuntoMail, bodyMail );
            return new ObjResponse(mensaje /*+ msjOpc*/, HttpStatus.OK.value(), null);
        }catch (Exception e){
            return new ObjResponse("Error inesperado", HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
    }

    @Override
    public ObjResponse listarUsuarios() {
        List<Cliente> clientes = (List<Cliente>) cliDao.findAll();
        List<Vendedor> vendedores = (List<Vendedor>) vendedorDao.findAll();
        List<Administrador> admins = (List<Administrador>) administradorDao.findAll();
        List<DtUsuarioBO> listado = new ArrayList<>();
        ObjResponse obj = new ObjResponse("No se han encontrado usuarios por listar", HttpStatus.NO_CONTENT.value(),0L, null,"Error, nada que listar");
        if(!clientes.isEmpty()){
            for(Cliente c : clientes){
                listado.add(c.dtBackOfficeAdmin());
            }
        }
        if(!vendedores.isEmpty()){
            for(Vendedor v : vendedores){
                listado.add(v.dtBackOfficeAdmin());
            }
        }
        if (!admins.isEmpty()){
            for(Administrador a : admins){
                listado.add(a.dtBackOfficeAdmin());
            }
        }
        if(!listado.isEmpty()){
            obj.setMensaje("Exito");
            obj.setCodeHttp(HttpStatus.OK.value());
            obj.setObjeto(listado);
        }
        return obj;
    }

    @Override
    public ObjResponse bloquearDesbloquerUsuerios(String correo, String rol, boolean bloqueado) {
        ObjResponse obj = new ObjResponse("Exito",HttpStatus.OK.value(), 0L, null, "Exito" );
        try{
            String msjExt = ", se ha bloqueado la cuenta de usuario";
            if(!bloqueado)
                msjExt = ", se ha desbloqueado la cuenta usuario";
            obj.setMensaje(obj.getMensaje()+msjExt);

            if(rol.equals("ROL_CLIENTE")){
                Cliente c = cliDao.findClienteByCorreoIgnoreCase(correo);
                if(c == null)
                    return auxResponseNoResult();

                c.setBloqueado(bloqueado);
                cliDao.save(c);
            }else if(rol.equals("ROL_VENDEDOR")){
                Cliente c = cliDao.findClienteByCorreoIgnoreCase(correo);
                if(c == null)
                    return auxResponseNoResult();
                Vendedor v = vendedorDao.findVendedorByCliente(c);
                if(v == null)
                    return auxResponseNoResult();

                v.setHabilitado(false);
                if(bloqueado == false)
                    v.setHabilitado(true);
                vendedorDao.save(v);
            }else if(rol.equals("ROL_ADMIN")){
                Administrador adm = administradorDao.findAdministradorByCorreoIgnoreCase(correo);
                if(adm == null)
                    return auxResponseNoResult();
                adm.setBloqueado(bloqueado);
                administradorDao.save(adm);
            }else{
                obj = new ObjResponse("Error en los datos ingresados",HttpStatus.BAD_REQUEST.value(), 0L, null, "Error" );
            }
        }catch (Exception e){
            obj = new ObjResponse("Error inesperado",HttpStatus.INTERNAL_SERVER_ERROR.value(), 0L, null, "Error Inesperado" );
        }
        return obj;
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

    public void habilitarProductos(Long vId){
        List<Producto> prods = prodDao.getAllByVendedor_Id(vId);
        if(prods != null){
            System.out.println("\n\n\nHabilitar productos");
            for(Producto p : prods){
                p.setActivo(true);
            }
            prodDao.saveAll(prods);
        }
    }

    public ObjResponse auxResponseNoResult() {
        return new ObjResponse("No se ha encontrado el usuarios ha bloquear, error en los datos o rol", HttpStatus.BAD_REQUEST.value(),0L, null,"Error");
    }
}
