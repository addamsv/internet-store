package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = @Tag(
                name = "/api/v1/",
                description = "endpoints"
        ),
        info = @Info(
                contact = @Contact(
                        name = "Sergy",
                        email = "sergyaddam@gmail.com",
                        url = "https://github.com/addamsv"
                ),
                title = "The Internet Store",
                description = "Enhance my spring boot && nest tech skills",
                version = "1.0.0",
                license = @License(
                        name = "MIT",
                        url = ""
                ),
                termsOfService = "Terms of Service"
        ),
        servers = {
                @Server(
                        url = "http://localhost:5500",
                        description = "dev server"
                ),
                @Server(
                        url = "http://localhost:5500",
                        description = "prod server"
                )
        }
//        security = {
//                @SecurityRequirement(
//                        name = "BearerAuth"
//                )
//        }
)
@SecurityScheme(
        name = "BearerAuth",
        description = "JWT Auth desc",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@SecurityScheme(
        name = "BasicAuth",
        description = "Login Auth",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.QUERY
)
public class SwaggerConfig {}
