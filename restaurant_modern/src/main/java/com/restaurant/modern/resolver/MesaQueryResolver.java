package com.restaurant.modern.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.modern.entity.Mesa;
import com.restaurant.modern.service.MesaService;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@RestController
public class MesaQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private MesaService mesaService;

    @QueryMapping
    public List<Mesa> getMesas() {
        return mesaService.getAllMesas();
    }

    @QueryMapping
    public Mesa getMesa(@Argument("id") String id) {
        return mesaService.getMesa(id);
    }

    @QueryMapping
    public Mesa getMesaByNro(@Argument("nro") Integer nro) {
        return mesaService.getMesaByNro(nro);
    }
}
