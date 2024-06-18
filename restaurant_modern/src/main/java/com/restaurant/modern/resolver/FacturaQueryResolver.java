package com.restaurant.modern.resolver;


import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.modern.entity.Factura;
import com.restaurant.modern.entity.FacturaPagination;
import com.restaurant.modern.entity.PaginaInfo;
import com.restaurant.modern.repository.FacturaRepository;


@Component @RestController
public class FacturaQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private FacturaRepository facturaRepository;

    @QueryMapping 
    public FacturaPagination getFacturasS(@Argument int page,@Argument int size) {
    	 Pageable pageable = PageRequest.of(page, size);
    	 Page<Factura> facturaPage = facturaRepository.findAll(pageable);
    	 PaginaInfo paginaInfo = new PaginaInfo(facturaPage.getTotalPages(), facturaPage.getTotalElements(), page, size);
    	 return new FacturaPagination(paginaInfo, facturaPage.getContent());
    }
    
    @QueryMapping 
    public List<Factura> getFacturas() {
    	 return facturaRepository.findAll();
    }
    
    @QueryMapping
    public Factura getFactura(String nro) {
        return facturaRepository.getFacturaByNro(nro);
    }
}