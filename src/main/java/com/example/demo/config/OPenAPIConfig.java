package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = @Tag(
                name = "Sergy Addam"
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
                        url = "",
                        description = "dev server"
                ),
                @Server(
                        url = "",
                        description = "prod server"
                )
        }
) // 27:53
public class OPenAPIConfig {}
