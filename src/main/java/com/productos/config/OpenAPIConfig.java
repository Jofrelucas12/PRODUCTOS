package com.productos.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${app.description}")
    String descripcion;

    @Value("${app.version}")
    String version;
    @Value("${app.nombre}")
    String nombre;
    @Value("${contacto.nombre}")
    String nombreContacto;

    @Bean
    public OpenAPI customOpenAPI() {

        Contact contact = new Contact()
                .name(nombreContacto);

        Info info = new Info()
                .title(nombre)
                .description(descripcion)
                .version(version)
                .contact(contact);

        Components components = new Components();

        return new OpenAPI()
                .info(info)
                .addServersItem(new Server().url("/"))
                .components(components);
    }
}
