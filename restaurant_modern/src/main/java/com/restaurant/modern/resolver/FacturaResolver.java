package com.restaurant.modern.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import com.restaurant.modern.entity.Bebida;
import com.restaurant.modern.entity.Factura;
import com.restaurant.modern.entity.Pedido;
import com.restaurant.modern.entity.Plato;
import com.restaurant.modern.service.FacturaService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class FacturaResolver implements GraphQLQueryResolver {
    @Autowired
    private FacturaService facturaService;

    @QueryMapping
    public List<?> getFacturas() {
        return facturaService.getAllFacturas();
    }
    
    @QueryMapping
    public Factura getFactura(String nro) {
        return facturaService.getFactura(nro);
    }
    
    @QueryMapping
    public Factura createFactura(String id_usuario, Double total, String fecha, Map<String, Object> pedidoInput) {
        Pedido pedido = convertMapToPedido(pedidoInput);

        return facturaService.createFactura(id_usuario, total, fecha, pedido);
    }
    
    private Pedido convertMapToPedido(Map<String, Object> pedidoInput) {
        Pedido pedido = new Pedido();
        pedido.setNro_pedido((String) pedidoInput.get("nro_pedido"));
        pedido.setId_mesero((String) pedidoInput.get("id_mesero"));
        pedido.setNro_mesa((Integer) pedidoInput.get("nro_mesa"));
        pedido.setNombre_comensal((String) pedidoInput.get("nombre_comensal"));
        pedido.setFecha((String) pedidoInput.get("fecha"));
        pedido.setHora((String) pedidoInput.get("hora"));
        pedido.setEstado((Boolean) pedidoInput.get("estado"));
        pedido.setExtras((String) pedidoInput.get("extras"));

        List<Map<String, Object>> platoInputs = (List<Map<String, Object>>) pedidoInput.get("plato");
        List<Plato> platos = new ArrayList<>();
        for (Map<String, Object> platoInput : platoInputs) {
            Plato plato = new Plato();
            plato.setCantidad((Integer) platoInput.get("cantidad"));
            plato.setNombre((String) platoInput.get("nombre"));
            platos.add(plato);
        }
        pedido.setPlato(platos);

        List<Map<String, Object>> bebidaInputs = (List<Map<String, Object>>) pedidoInput.get("bebida");
        List<Bebida> bebidas = new ArrayList<>();
        for (Map<String, Object> bebidaInput : bebidaInputs) {
            Bebida bebida = new Bebida();
            bebida.setCantidad((Integer) bebidaInput.get("cantidad"));
            bebida.setNombre((String) bebidaInput.get("nombre"));
            bebidas.add(bebida);
        }
        pedido.setBebida(bebidas);

        return pedido;
    }
    
    @MutationMapping
    public Factura updateFactura(String nro, Double total, String fecha) {
        return facturaService.updateFactura(nro, total, fecha);
    }
	
    @MutationMapping
    public boolean deleteFactura(String nro) {
        facturaService.deleteFactura(nro);
        return true;
    }
}
