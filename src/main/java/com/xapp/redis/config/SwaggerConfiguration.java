package com.xapp.redis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Redis Chace supported API",
        description = "Swagger documentation for different CRUD operations available in this Application",
        version = "v1"),
    security = {
        @SecurityRequirement(name = "JWT"),
        @SecurityRequirement(name = "Authorizations")
    }
)

@SecurityScheme(
    name = "JWT",
    description = "Token issued by AWS Cognito",
    scheme = "bearer",
    bearerFormat = "JWT",
    type = SecuritySchemeType.HTTP
)
@SecurityScheme(
    name = "Authorizations",
    description = "Authorization context as JSON",
    paramName = "authorized_entities",
    in = SecuritySchemeIn.HEADER,
    type = SecuritySchemeType.APIKEY
)
@Configuration
public class SwaggerConfiguration {

}

