package com.example.SpringBootExpert.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@EnableSwagger2
@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "João Víctor",
                        email = "joaovictormaia482@gmail.com",
                        url = "https://www.linkedin.com/in/joão-víctor-maia-4b9961265/"
                ),
                description = "Projeto de Vendas",
                title = "API Vendas",
                version = "1.0",
                license = @License(
                        name = "Nome da Licença",
                        url = "https://algumaURL.com"
            ),
                termsOfService = "Termos de Serviço"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhosto:8080"
                )
        }
)
public class SwaggerConfig {
}
/*
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.example.SpringBootExpert.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API Vendas")
                .description("Projeto de vendas")
                .version("1.0")
                .contact(contact())
                .build();
    }

    private Contact contact(){
        return new Contact("João Víctor"
                , "https://www.linkedin.com/in/joão-víctor-maia-4b9961265/"
                , "joaovictormaia482@gmail.com");
    }

}*/
