package com.restaurant.modern.config;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import graphql.schema.idl.RuntimeWiring.Builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.core.io.Resource;

import com.restaurant.modern.resolver.FacturaResolver;
import com.restaurant.modern.resolver.MesaResolver;
import com.restaurant.modern.resolver.UsuarioResolver;

import org.springframework.graphql.execution.GraphQlSource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;
import graphql.scalars.ExtendedScalars;

@Configuration
public class GraphQLConfig {

	@Autowired
	private FacturaResolver facturaResolver;
	@Autowired
	private UsuarioResolver usuarioResolver;
	@Autowired
	private MesaResolver mesaResolver;

	@Bean
	public RuntimeWiring.Builder runtimeWiringBuilder() {
		return RuntimeWiring.newRuntimeWiring();
	}

	@Bean
    public GraphQlSource graphQlSource(RuntimeWiring.Builder runtimeWiringBuilder) throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();

        // Cargar y combinar los esquemas de los archivos .graphqls
        Resource[] schemaResources = new Resource[] {
            new ClassPathResource("graphql/schemaUsuario.graphqls"),
            new ClassPathResource("graphql/schemaMesa.graphqls"),
            new ClassPathResource("graphql/schemaFactura.graphqls")
        };

        for (Resource schemaResource : schemaResources) {
            typeRegistry.merge(schemaParser.parse(schemaResource.getInputStream()));
        }

        // Configurar el RuntimeWiring
        RuntimeWiring runtimeWiring = runtimeWiringBuilder
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("facturas", environment -> facturaResolver.getFacturas())
                        .dataFetcher("factura", environment -> facturaResolver.getFactura(environment.getArgument("nro")))
                        .dataFetcher("usuarios", environment -> usuarioResolver.getUsuarios())
                        .dataFetcher("usuario", environment -> usuarioResolver.getUsuario(environment.getArgument("id")))
                        .dataFetcher("mesas", environment -> mesaResolver.getMesas())
                        .dataFetcher("mesaByNro", environment -> mesaResolver.getMesaByNro(environment.getArgument("nro")))
                        .dataFetcher("mesa", environment -> mesaResolver.getMesa(environment.getArgument("id"))))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("createFactura", environment -> facturaResolver.createFactura(
                                environment.getArgument("id_usuario"), environment.getArgument("total"),
                                environment.getArgument("fecha"), environment.getArgument("pedido")))
                        .dataFetcher("createMesa", environment -> mesaResolver.createMesa(
                                environment.getArgument("id_usuario"), environment.getArgument("nro"),
                                environment.getArgument("capacidad"), environment.getArgument("disponible")))
                        .dataFetcher("updateMesa", environment -> mesaResolver.updateMesa(
                                environment.getArgument("id"), environment.getArgument("nro"),
                                environment.getArgument("capacidad"), environment.getArgument("disponible")))
                        .dataFetcher("updateMesaByNro", environment -> mesaResolver.updateMesaByNro(
                                environment.getArgument("nro"),
                                environment.getArgument("capacidad"), environment.getArgument("disponible")))
                        .dataFetcher("deleteMesa", environment -> mesaResolver.deleteMesa(
                                environment.getArgument("id")))
                        .dataFetcher("updateFactura", environment -> facturaResolver.updateFactura(
                                environment.getArgument("nro"),
                                environment.getArgument("total"), environment.getArgument("fecha")))
                        .dataFetcher("deleteFactura", environment -> facturaResolver.deleteFactura(
                                environment.getArgument("nro")))
                        .dataFetcher("createUsuario", environment -> usuarioResolver.createUsuario(
                                environment.getArgument("nombreUsuario"),
                                environment.getArgument("password"), environment.getArgument("admin")))
                        .dataFetcher("updateUsuario", environment -> usuarioResolver.updateUsuario(
                                environment.getArgument("id"),
                                environment.getArgument("nombre_usuario"), environment.getArgument("password"),
                                environment.getArgument("isAdmin")))
                        .dataFetcher("deleteUsuario", environment -> usuarioResolver.deleteUsuario(
                                environment.getArgument("id"))))
                .build();

        GraphQLSchema schema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
        return GraphQlSource.builder(schema).build();
    }

	private String loadSchema(String path) throws IOException {
		Resource resource = new ClassPathResource(path);
		return new String(Files.readAllBytes(resource.getFile().toPath()));
	}
}
