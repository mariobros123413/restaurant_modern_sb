package com.restaurant.modern.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.modern.entity.Mesa;
import com.restaurant.modern.service.MesaService;
import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@RestController
public class MesaMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private MesaService mesaService;

    @MutationMapping
    public Mesa createMesa(@Argument("id_usuario") Long id_usuario, 
                           @Argument("nro") Integer nro, 
                           @Argument("capacidad") Integer capacidad,
                           @Argument("disponible") Boolean disponible) {
        return mesaService.createMesa(id_usuario, nro, capacidad, disponible);
    }

    @MutationMapping
    public Mesa updateMesa(@Argument("id") String id, 
                           @Argument("nro") Integer nro, 
                           @Argument("capacidad") Integer capacidad,
                           @Argument("disponible") Boolean disponible) {
        return mesaService.updateMesa(id, nro, capacidad, disponible);
    }

    @MutationMapping
    public Mesa updateMesaByNro(@Argument("nro") Integer nro, 
                                @Argument("capacidad") Integer capacidad, 
                                @Argument("disponible") Boolean disponible) {
        return mesaService.updateMesaByNro(nro, capacidad, disponible);
    }

    @MutationMapping
    public boolean deleteMesa(@Argument("id") String id) {
        mesaService.deleteMesa(id);
        return true;
    }
}
