package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.*;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtCompra;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtPago;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoCompra;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoProdCarrito;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    ProductoCarritoDao pcDao;
    @Autowired
    CompraDao compraDao;
    @Autowired
    ClienteDao clienteDao;
    @Autowired
    PagoDao pagoDao;
    @Autowired
    EnvioDao envioDao;
    @Autowired
    RetiroDao retiroDao;

    @Override
    public ObjResponse confirmarProductosCarrito(DtPago dtP){
        Cliente cliente = clienteDao.findClienteById(dtP.getIdCliente());
        List<ProductoCarrito> prods = pcDao.findProductoCarritoByClienteAndEstado(cliente, EstadoProdCarrito.AGREGADO);

        Pago pago = new Pago(dtP.getFecha(), dtP.getMetodo(),false, dtP.getReferenciaExterna());
        List<Compra> compras = new ArrayList<Compra>();
        pagoDao.save(pago);

        for(ProductoCarrito pc : prods){
            Compra c = new Compra(true, EstadoCompra.PROCESANDO, pc, pago);
            pc.getProducto().setStock(pc.getProducto().getStock()-pc.getCantidad());
            pc.setEstado(EstadoProdCarrito.COMPRADO);
            c.setPago(pago);
            compras.add(c);
            compraDao.save(c);
        }

        pago.setCompras(compras);

        try {
            return new ObjResponse("Exito al procesar el pago", HttpStatus.OK.value(),pago.getId());
        }catch (Exception e){
            return new ObjResponse("Error al procesar el pago", HttpStatus.BAD_REQUEST.value(),null);
        }
    }

    @Override
    public ObjResponse comprasPendientesDeElegirEnrega(Long idC){
        Cliente cliente = clienteDao.findClienteById(idC);
        List<Compra> comprasPendientes = compraDao.findCompraByEstadoAndProductoCarrito_Cliente(EstadoCompra.PROCESANDO, cliente);
        List<DtCompra> ret = new ArrayList<DtCompra>();

        for (Compra c : comprasPendientes){
            List<String> tiposEntrega = new ArrayList<String>();
            tiposEntrega.add("RETIRO");

            if(c.getProductoCarrito().getProducto().getVendedor().isHabilitaEnvio()){
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
    public ObjResponse asignarMetodoEnrega(Long idCompra, String tipoEntrega){
        Compra compra = compraDao.findCompraById(idCompra);
        Envio e = new Envio();
        Retiro r = new Retiro();

        if(tipoEntrega.equals("ENVIO")){
            e.setCompra(compra);
        } else if(tipoEntrega.equals("RETIRO")){
            r.setCompra(compra);
        }

        compra.setEstado(EstadoCompra.NO_ENTREGADA);

        try {
             if(tipoEntrega.equals("ENVIO")){
                envioDao.save(e);
                return new ObjResponse("Exito", HttpStatus.OK.value(), envioDao.findAllByCompra(compra).getId());
            } else{
                 retiroDao.save(r);
                return new ObjResponse("Exito", HttpStatus.OK.value(), retiroDao.findAllByCompra(compra).getId());
            }
        }catch (Exception exception){
            return new ObjResponse("Error", HttpStatus.BAD_REQUEST.value(),null);
        }
    }
}
