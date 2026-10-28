package com.prestamos.hipotecarios.infrastructure.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuración centralizada para la gestión de variables de entorno del sistema.
 * 
 * Esta clase proporciona:
 * - Validación de variables de entorno críticas al iniciar la aplicación
 * - Tipado seguro para configuración externa
 * - Valores por defecto sensatos para entornos de desarrollo
 * - Documentación de las variables requeridas
 */
@Configuration
@EnableConfigurationProperties
@Validated
@ConfigurationProperties(prefix = "app")
public class EnvConfig {

    private static final Logger logger = LoggerFactory.getLogger(EnvConfig.class);

    @NotBlank(message = "El nombre del entorno es obligatorio")
    private String environment;

    @NotBlank(message = "El modo de operación es obligatorio")
    @Pattern(regexp = "(development|staging|production)", 
              message = "El entorno debe ser: development, staging o production")
    private String mode;

    private DatabaseConfig database = new DatabaseConfig();
    private SecurityConfig security = new SecurityConfig();
    private ExternalServiceConfig externalServices = new ExternalServiceConfig();
    private PerformanceConfig performance = new PerformanceConfig();
    private FeatureFlags features = new FeatureFlags();

    public EnvConfig() {
        logger.info("Inicializando configuración de entorno");
    }

    @Bean
    public EnvConfig envConfig() {
        validarConfiguracion();
        logger.info("Configuración de entorno validada correctamente para modo: {}", mode);
        return this;
    }

    /**
     * Valida que la configuración crítica esté presente y sea correcta.
     * Lanza una excepción si la configuración es inválida, evitando un inicio inseguro.
     */
    private void validarConfiguracion() {
        Map<String, String> errores = new HashMap<>();

        if (environment == null || environment.isBlank()) {
            errores.put("environment", "No puede estar vacío");
        }

        if (mode == null || mode.isBlank()) {
            errores.put("mode", "No puede estar vacío");
        }

        if (!errores.isEmpty()) {
            throw new IllegalStateException("Configuración de entorno inválida: " + errores);
        }

        logger.info("Validación de configuración completada - Entorno: {}, Modo: {}", 
                environment, mode);
    }

    // Getters y Setters

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public DatabaseConfig getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseConfig database) {
        this.database = database;
    }

    public SecurityConfig getSecurity() {
        return security;
    }

    public void setSecurity(SecurityConfig security) {
        this.security = security;
    }

    public ExternalServiceConfig getExternalServices() {
        return externalServices;
    }

    public void setExternalServices(ExternalServiceConfig externalServices) {
        this.externalServices = externalServices;
    }

    public PerformanceConfig getPerformance() {
        return performance;
    }

    public void setPerformance(PerformanceConfig performance) {
        this.performance = performance;
    }

    public FeatureFlags getFeatures() {
        return features;
    }

    public void setFeatures(FeatureFlags features) {
        this.features = features;
    }

    /**
     * Verifica si la aplicación está ejecutándose en modo de producción.
     */
    public boolean isProduction() {
        return "production".equalsIgnoreCase(mode);
    }

    /**
     * Verifica si la aplicación está ejecutándose en modo de desarrollo.
     */
    public boolean isDevelopment() {
        return "development".equalsIgnoreCase(mode);
    }

    /**
     * Obtiene una representación segura de la configuración para logging.
     * No incluye información sensible como contraseñas o tokens.
     */
    public Map<String, Object> getPublicConfig() {
        Map<String, Object> publicConfig = new HashMap<>();
        publicConfig.put("environment", environment);
        publicConfig.put("mode", mode);
        publicConfig.put("database.host", database.getHost());
        publicConfig.put("database.port", database.getPort());
        publicConfig.put("database.name", database.getName());
        publicConfig.put("security.jwtEnabled", security.isJwtEnabled());
        publicConfig.put("externalServices.creditSystemUrl", externalServices.getCreditSystemUrl());
        publicConfig.put("performance.maxConnections", performance.getMaxConnections());
        publicConfig.put("features.asyncProcessing", features.isAsyncProcessing());
        return publicConfig;
    }

    /**
     * Configuración de base de datos.
     */
    public static class DatabaseConfig {
        @NotBlank(message = "El host de base de datos es obligatorio")
        private String host;

        @NotNull(message = "El puerto de base de datos es obligatorio")
        @Min(value = 1, message = "El puerto debe ser mayor a 0")
        private Integer port;

        @NotBlank(message = "El nombre de base de datos es obligatorio")
        private String name;

        private String username;
        private String password;
        private Integer poolSize = 10;
        private Integer timeout = 30;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getPoolSize() {
            return poolSize;
        }

        public void setPoolSize(Integer poolSize) {
            this.poolSize = poolSize;
        }

        public Integer getTimeout() {
            return timeout;
        }

        public void setTimeout(Integer timeout) {
            this.timeout = timeout;
        }

        /**
         * Obtiene la URL de conexión completa para JDBC.
         */
        public String getJdbcUrl() {
            return String.format("jdbc:postgresql://%s:%d/%s", host, port, name);
        }
    }

    /**
     * Configuración de seguridad.
     */
    public static class SecurityConfig {
        private boolean jwtEnabled = true;
        private String jwtSecret;
        private Long jwtExpirationMs = 3600000L; // 1 hora
        private Integer maxLoginAttempts = 5;
        private Integer lockoutDurationMinutes = 30;

        public boolean isJwtEnabled() {
            return jwtEnabled;
        }

        public void setJwtEnabled(boolean jwtEnabled) {
            this.jwtEnabled = jwtEnabled;
        }

        public String getJwtSecret() {
            return jwtSecret;
        }

        public void setJwtSecret(String jwtSecret) {
            this.jwtSecret = jwtSecret;
        }

        public Long getJwtExpirationMs() {
            return jwtExpirationMs;
        }

        public void setJwtExpirationMs(Long jwtExpirationMs) {
            this.jwtExpirationMs = jwtExpirationMs;
        }

        public Integer getMaxLoginAttempts() {
            return maxLoginAttempts;
        }

        public void setMaxLoginAttempts(Integer maxLoginAttempts) {
            this.maxLoginAttempts = maxLoginAttempts;
        }

        public Integer getLockoutDurationMinutes() {
            return lockoutDurationMinutes;
        }

        public void setLockoutDurationMinutes(Integer lockoutDurationMinutes) {
            this.lockoutDurationMinutes = lockoutDurationMinutes;
        }
    }

    /**
     * Configuración de servicios externos.
     */
    public static class ExternalServiceConfig {
        @NotBlank(message = "La URL del sistema de créditos es obligatoria")
        private String creditSystemUrl;

        private Integer connectionTimeout = 5000;
        private Integer readTimeout = 30000;
        private Integer maxRetries = 3;
        private Long retryDelayMs = 1000L;

        public String getCreditSystemUrl() {
            return creditSystemUrl;
        }

        public void setCreditSystemUrl(String creditSystemUrl) {
            this.creditSystemUrl = creditSystemUrl;
        }

        public Integer getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(Integer connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        public Integer getReadTimeout() {
            return readTimeout;
        }

        public void setReadTimeout(Integer readTimeout) {
            this.readTimeout = readTimeout;
        }

        public Integer getMaxRetries() {
            return maxRetries;
        }

        public void setMaxRetries(Integer maxRetries) {
            this.maxRetries = maxRetries;
        }

        public Long getRetryDelayMs() {
            return retryDelayMs;
        }

        public void setRetryDelayMs(Long retryDelayMs) {
            this.retryDelayMs = retryDelayMs;
        }
    }

    /**
     * Configuración de rendimiento y capacidad.
     */
    public static class PerformanceConfig {
        @Min(value = 1, message = "El número mínimo de conexiones es 1")
        private Integer maxConnections = 100;

        @Min(value = 1, message = "El timeout mínimo es 1 segundo")
        private Integer requestTimeout = 60;

        private Integer threadPoolSize = 10;
        private Integer queueCapacity = 100;
        private boolean asyncEnabled = true;

        public Integer getMaxConnections() {
            return maxConnections;
        }

        public void setMaxConnections(Integer maxConnections) {
            this.maxConnections = maxConnections;
        }

        public Integer getRequestTimeout() {
            return requestTimeout;
        }

        public void setRequestTimeout(Integer requestTimeout) {
            this.requestTimeout = requestTimeout;
        }

        public Integer getThreadPoolSize() {
            return threadPoolSize;
        }

        public void setThreadPoolSize(Integer threadPoolSize) {
            this.threadPoolSize = threadPoolSize;
        }

        public Integer getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(Integer queueCapacity) {
            this.queueCapacity = queueCapacity;
        }

        public boolean isAsyncEnabled() {
            return asyncEnabled;
        }

        public void setAsyncEnabled(boolean asyncEnabled) {
            this.asyncEnabled = asyncEnabled;
        }
    }

    /**
     * Feature flags para control de funcionalidades.
     */
    public static class FeatureFlags {
        private boolean asyncProcessing = false;
        private boolean cacheEnabled = true;
        private boolean auditEnabled = true;
        private boolean metricsEnabled = true;
        private boolean circuitBreakerEnabled = false;

        public boolean isAsyncProcessing() {
            return asyncProcessing;
        }

        public void setAsyncProcessing(boolean asyncProcessing) {
            this.asyncProcessing = asyncProcessing;
        }

        public boolean isCacheEnabled() {
            return cacheEnabled;
        }

        public void setCacheEnabled(boolean cacheEnabled) {
            this.cacheEnabled = cacheEnabled;
        }

        public boolean isAuditEnabled() {
            return auditEnabled;
        }

        public void setAuditEnabled(boolean auditEnabled) {
            this.auditEnabled = auditEnabled;
        }

        public boolean isMetricsEnabled() {
            return metricsEnabled;
        }

        public void setMetricsEnabled(boolean metricsEnabled) {
            this.metricsEnabled = metricsEnabled;
        }

        public boolean isCircuitBreakerEnabled() {
            return circuitBreakerEnabled;
        }

        public void setCircuitBreakerEnabled(boolean circuitBreakerEnabled) {
            this.circuitBreakerEnabled = circuitBreakerEnabled;
        }
    }
}