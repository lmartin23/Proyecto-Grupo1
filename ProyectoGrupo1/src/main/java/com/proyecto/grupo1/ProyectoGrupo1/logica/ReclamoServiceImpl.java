package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.CompraDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ReclamoDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtReclamo;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Compra;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.MailRequest;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Reclamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReclamoServiceImpl implements ReclamoService{

    @Autowired
    CompraDao compraDao;
    @Autowired
    ReclamoDao reclamoDao;

    @Autowired
    MailService mailService;

    @Override
    public ObjResponse iniciar (DtReclamo dtR){
        Compra compra = compraDao.findCompraById(dtR.getIdCompra());
        Reclamo reclamo = new Reclamo(
            dtR.getDescripcion(),
            compra
        );

        //armo notificación mail
        MailRequest mail = new MailRequest();
        String mensaje = "Se ha iniciado reclamo por la compra con id: " + compra.getId() + "."
                + "Por favor póngase en contacto con el cliente: " + compra.getProductoCarrito().getCliente().getCorreo() + "\r\n\r\n"
                + "Mensaje del cliente: \r\n" + "'" + dtR.getDescripcion() + "'";

        mail.setTo(compra.getProductoCarrito().getProducto().getVendedor().getCliente().getCorreo());
        mail.setSubject("Reclamo iniciado. Compra Id: "+compra.getId());
        mail.setText(mensaje);

        try {
            reclamoDao.save(reclamo);
            mailService.sendMail(mail);
            DtReclamo ret = reclamoDao.findByCompra(compra).obtenerDtReclamo();
            return new ObjResponse("Exito", HttpStatus.OK.value(), ret);
        }catch (Exception e){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse listarReclamosVendedor (Long idVendedor){
        List<Reclamo> reclamos = reclamoDao.findByCompra_ProductoCarrito_Producto_Vendedor_IdOrderByFechaUltEstadoDesc(idVendedor);
        List<DtReclamo> ret = new ArrayList<DtReclamo>();

        for (Reclamo r : reclamos){
            DtReclamo dtR = r.obtenerDtReclamo();
            ret.add(dtR);
        }

        try {
            return new ObjResponse("Exito", HttpStatus.OK.value(), ret);
        }catch (Exception e){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }
}
