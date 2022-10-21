package com.proyecto.grupo1.ProyectoGrupo1.logica;

import com.proyecto.grupo1.ProyectoGrupo1.dao.ClienteDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.CompraDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.PagoDao;
import com.proyecto.grupo1.ProyectoGrupo1.dao.ProductoCarritoDao;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.DtPago;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.datatype.ObjResponse;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoCompra;
import com.proyecto.grupo1.ProyectoGrupo1.datatypes.enums.EstadoProdCarrito;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Cliente;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Compra;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.Pago;
import com.proyecto.grupo1.ProyectoGrupo1.entidades.ProductoCarrito;
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
}
