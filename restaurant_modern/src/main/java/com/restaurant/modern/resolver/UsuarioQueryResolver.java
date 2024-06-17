package com.restaurant.modern.resolver;


import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.repository.UsuarioRepository;

import java.util.List;

@Component @RestController
@Secured("ADMIN")
public class UsuarioQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UsuarioRepository userRepository;
    @QueryMapping 
    public List<Usuario> getUsers() {
        return userRepository.findAll();
    }
}