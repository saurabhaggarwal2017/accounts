package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "MyAudit")
@EnableConfigurationProperties(AccountsContactInfoDto.class)
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "Account-Microservice",
                description = "Account microservice for creating account for a customers",
                contact = @Contact(
                        name = "Saurabh Kumar",
                        url = "https://github.com/saurabhaggarwal2017",
                        email = "saurabhaggarwal@gmail.com"
                ),
                version = "V1"
        ),
        externalDocs = @ExternalDocumentation(
                description = "for more information do check out tutor github",
                url = "https://github.com/eazybytes"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
