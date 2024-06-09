package com.restaurant.modern.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import com.restaurant.modern.entity.Factura;
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
    public Factura createFactura(String id_usuario, Double total, String fecha) {
        return facturaService.createFactura(id_usuario, total, fecha);
    }
}
