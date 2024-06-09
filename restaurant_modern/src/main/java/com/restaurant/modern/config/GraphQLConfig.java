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
import com.restaurant.modern.resolver.UsuarioResolver;

import org.springframework.graphql.execution.GraphQlSource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

@Configuration
public class GraphQLConfig {

	@Autowired
	private FacturaResolver facturaResolver;
	@Autowired
	private UsuarioResolver usuarioResolver;

	@Bean
	public RuntimeWiring.Builder runtimeWiringBuilder() {
		return RuntimeWiring.newRuntimeWiring();
	}

	@Bean
	public GraphQlSource graphQlSource(RuntimeWiring.Builder runtimeWiringBuilder) throws IOException {
		SchemaParser schemaParser = new SchemaParser();
		SchemaGenerator schemaGenerator = new SchemaGenerator();

		TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        Resource schemaResourceFactura = new ClassPathResource("graphql/schema.graphqls");
        

        typeRegistry.merge(schemaParser.parse(schemaResourceFactura.getInputStream()));
        

		// Configurar el RuntimeWiring
		RuntimeWiring runtimeWiring = runtimeWiringBuilder
				.type("Query",
						typeWiring -> typeWiring.dataFetcher("facturas",environment -> facturaResolver.getFacturas())
												 .dataFetcher("factura", environment -> facturaResolver.getFactura(environment.getArgument("nro")))
							                     .dataFetcher("usuarios", environment -> usuarioResolver.getUsuarios())
							                     .dataFetcher("usuario", environment -> usuarioResolver.getUsuario(environment.getArgument("id")
										)))
				.type("Mutation", 
						typeWiring -> typeWiring
                        .dataFetcher("createFactura", environment -> facturaResolver.createFactura(
                                environment.getArgument("id_usuario"),
                                environment.getArgument("total"),
                                environment.getArgument("fecha")))
                        .dataFetcher("createUsuario", environment -> usuarioResolver.createUsuario(
                                environment.getArgument("nombreUsuario"),
                                environment.getArgument("password"),
                                environment.getArgument("admin"))))
				.build();

		GraphQLSchema schema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
		return GraphQlSource.builder(schema).build();
	}

	private String loadSchema(String path) throws IOException {
		Resource resource = new ClassPathResource(path);
		return new String(Files.readAllBytes(resource.getFile().toPath()));
	}
}
