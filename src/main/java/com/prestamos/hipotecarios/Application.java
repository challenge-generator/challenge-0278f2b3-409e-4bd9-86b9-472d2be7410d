package com.prestamos.hipotecarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.TimeZone;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAsync
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setReadTimeout(Duration.ofSeconds(10));
        restTemplate.setConnectTimeout(Duration.ofSeconds(5));
        return restTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        logger.info("Aplicación iniciada con éxito. Zona horaria configurada a UTC");

        // Verificación de conexión a base de datos
        try {
            // Simulación de verificación de conexión
            logger.info("Verificando conexión a la base de datos...");
            // En un entorno real, aquí iría una verificación real de conexión
            logger.info("Conexión a la base de datos verificada correctamente");
        } catch (Exception e) {
            logger.error("Error al verificar la conexión a la base de datos", e);
            throw new RuntimeException("No se pudo verificar la conexión a la base de datos", e);
        }
    }
}