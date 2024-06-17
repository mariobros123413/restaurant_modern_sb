package com.restaurant.modern.resolver;


import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.modern.entity.Factura;
import com.restaurant.modern.repository.FacturaRepository;
import java.util.List;

@Component @RestController
public class FacturaQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private FacturaRepository facturaRepository;

    @QueryMapping 
    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }
    
    @QueryMapping
    public Factura getFactura(String nro) {
        return facturaRepository.getFacturaByNro(nro);
    }
}