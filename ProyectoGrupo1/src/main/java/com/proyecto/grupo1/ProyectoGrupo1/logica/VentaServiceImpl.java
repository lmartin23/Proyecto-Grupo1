package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.CompraDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.NotificacionDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.VendedorDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtCompra;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtEntregaCompra;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoCompra;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.TipoNotificacion;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Compra;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.MailRequest;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Notificacion;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService{
    @Autowired
    CompraDao compraDao;
    @Autowired
    VendedorDao vendedorDao;
    @Autowired
    MailService mailService;
    @Autowired
    NotificacionDao notificacionDao;

    @Override
    public ObjResponse listarVentasEnvioPendiente(Long idVendedor) {
        Vendedor vendedor = vendedorDao.findVendedorById(idVendedor);
        List<Compra> compras = compraDao.findCompraByEstadoAndProductoCarrito_Producto_Vendedor(EstadoCompra.NO_ENTREGADA, vendedor);
        List<DtCompra> ret = new ArrayList<DtCompra>();

        for (Compra c : compras) {
            List<String> tiposEntrega = new ArrayList<String>();

            if(c.getEnvio() == null){
                tiposEntrega.add("RETIRO");
            } else {
                tiposEntrega.add("ENVIO");
            }

            DtCompra dtC = new DtCompra(
                    c.getId(),
                    c.getFecha(),
                    c.getProductoCarrito().getProducto().getNombre(),
                    c.getProductoCarrito().getCantidad(),
                    c.getProductoCarrito().getTotal(),
                    tiposEntrega
            );
            ret.add(dtC);
        }
        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(), ret);
        }catch (Exception e){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse setearEntrega(DtEntregaCompra dtEC){
        Compra compra = compraDao.findCompraById(dtEC.getIdCompra());

        if(compra.getEnvio() == null){
            compra.getRetiro().setFechaDesde(dtEC.getFechaHoraDesde());
            compra.getRetiro().setFechaHasta(dtEC.getFechaHoraHasta());
        } else {
            compra.getEnvio().setFechaDesde(dtEC.getFechaHoraDesde());
            compra.getEnvio().setFechaHasta(dtEC.getFechaHoraHasta());
        }
        compra.setEstado(EstadoCompra.ENVIO_PENDIENTE);

        if(dtEC.getFechaHoraDesde().isAfter(dtEC.getFechaHoraHasta())){
            return new ObjResponse("Error. La fecha inicial debe ser mayor a la final", HttpStatus.BAD_REQUEST.value(), null);
        }

        String mensaje = "";

        if(compra.getEnvio() == null) {
            mensaje = "Su compra fue despachada por el vendedor. Podrá pasar a retirarla entre: "
                    + dtEC.getFechaHoraDesde()
                    + " y "
                    + dtEC.getFechaHoraHasta() + ".";
        } else {
            mensaje = "Su compra fue despachada por el vendedor. Será enviada entre: "
                    + compra.getEnvio().getFechaDesde()
                    + " y "
                    + compra.getEnvio().getFechaHasta() + ".";
        }

        MailRequest mail = new MailRequest();
        mail.setTo(compra.getProductoCarrito().getCliente().getCorreo());
        mail.setSubject("Compra lista para ser recibida. Id: "+compra.getId());
        mail.setText(mensaje);
        //Mail nofificación fin

        Notificacion push = new Notificacion(
                TipoNotificacion.MENSAJE,
                mensaje,
                false,
                compra.getProductoCarrito().getCliente().getId()
        );

        try {
            compraDao.save(compra);
            mailService.sendMail(mail);
            notificacionDao.save(push);
            return new ObjResponse("Exito", HttpStatus.OK.value(), null);
        }catch (Exception e){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }
}
