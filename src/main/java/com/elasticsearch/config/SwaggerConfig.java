package com.elasticsearch.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Elastic Search API")
                        .version("1.0.0")
                        .description("Spring Boot Elasticsearch Swagger Documentation")
                        .termsOfService("https://your-company.com/terms")
                        .contact(new Contact()
                                .name("Anku")
                                .url("https://your-github-or-portfolio.com")
                                .email("ankit763880@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub Repo")
                        .url("https://github.com/your-repo/elastic-search-springboot"))
                .servers(List.of(
                        new Server().url("/api/elastic_search").description("Context path"),
                        new Server().url("http://localhost:8282").description("Localhost Server")
                ));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}
