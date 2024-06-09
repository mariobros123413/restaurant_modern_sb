package com.restaurant.modern.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.service.UsuarioService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class UsuarioResolver implements GraphQLQueryResolver {
    @Autowired
    private UsuarioService usuarioService;

    @QueryMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @QueryMapping
    public Usuario getUsuario(String id) {
        return usuarioService.getUsuario(id);
    }

    @MutationMapping
    public Usuario createUsuario(String nombreUsuario, String password, Boolean admin) {
        return usuarioService.createUsuario(nombreUsuario, password, admin);
    }
}
