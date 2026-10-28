package com.prestamos.hipotecarios.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Configuración centralizada del sistema de logging para el módulo de préstamos hipotecarios.
 * 
 * Esta clase establece los parámetros de logging requeridos por el negocio:
 * - Registro de operaciones críticas con nivel INFO
 * - Captura de errores y excepciones con nivel ERROR
 * - Logs estructurados para auditoría y trazabilidad
 * - Rotación diaria de archivos de log
 */
@Configuration
public class LoggingConfig {

    private static final String LOG_DIRECTORY = "logs";
    private static final String APPLICATION_LOG_FILE = "prestamos-hipotecarios.log";
    private static final String ERROR_LOG_FILE = "prestamos-errors.log";
    private static final String AUDIT_LOG_FILE = "prestamos-audit.log";
    private static final int MAX_LOG_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int MAX_HISTORY = 30;

    private final Logger applicationLogger;
    private final Logger errorLogger;
    private final Logger auditLogger;

    public LoggingConfig() {
        initializeLogDirectory();
        this.applicationLogger = createLogger(APPLICATION_LOG_FILE, "application");
        this.errorLogger = createLogger(ERROR_LOG_FILE, "error");
        this.auditLogger = createLogger(AUDIT_LOG_FILE, "audit");
    }

    private void initializeLogDirectory() {
        try {
            Path logPath = Paths.get(LOG_DIRECTORY);
            if (!Files.exists(logPath)) {
                Files.createDirectories(logPath);
            }
        } catch (IOException e) {
            System.err.println("No se pudo crear el directorio de logs: " + e.getMessage());
        }
    }

    private Logger createLogger(String fileName, String loggerName) {
        Logger logger = LoggerFactory.getLogger(loggerName);
        logSystemInfo(logger, fileName);
        return logger;
    }

    private void logSystemInfo(Logger logger, String fileName) {
        String logPath = LOG_DIRECTORY + File.separator + fileName;
        logger.info("========================================");
        logger.info("Configuración de logging inicializada");
        logger.info("Archivo de log: {}", logPath);
        logger.info("Máximo tamaño por archivo: {} bytes", MAX_LOG_FILE_SIZE);
        logger.info("Retención de archivos: {} días", MAX_HISTORY);
        logger.info("========================================");
    }

    @Bean
    @Profile("!test")
    public Logger applicationLogger() {
        return applicationLogger;
    }

    @Bean
    @Profile("!test")
    public Logger errorLogger() {
        return errorLogger;
    }

    @Bean
    @Profile("!test")
    public Logger auditLogger() {
        return auditLogger;
    }

    /**
     * Registra una operación crítica del sistema.
     * Este método centraliza el registro de operaciones importantes para auditoría.
     * 
     * @param operacion Descripción de la operación realizada
     * @param resultado Resultado de la operación (SUCCESS, FAILURE, PENDING)
     * @param detalles Información adicional relevante
     */
    public void registrarOperacion(String operacion, String resultado, String detalles) {
        String timestamp = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String mensaje = String.format("[%s] OPERACION: %s | RESULTADO: %s | DETALLES: %s", 
                timestamp, operacion, resultado, detalles);
        
        if ("FAILURE".equals(resultado)) {
            errorLogger.error(mensaje);
        } else {
            applicationLogger.info(mensaje);
        }
    }

    /**
     * Registra un error o excepción del sistema.
     * 
     * @param contexto Contexto donde ocurrió el error
     * @param exception Excepción capturada
     * @param datosAdicionales Información adicional para debugging
     */
    public void registrarError(String contexto, Throwable exception, String datosAdicionales) {
        String timestamp = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String mensaje = String.format("[%s] ERROR en %s: %s | Datos: %s", 
                timestamp, contexto, exception.getMessage(), datosAdicionales);
        
        errorLogger.error(mensaje, exception);
        
        // También registrar en auditoría para trazabilidad
        auditLogger.warn("ERROR REGISTRADO - Contexto: {}, Excepción: {}, Trazabilidad: {}",
                contexto, exception.getClass().getSimpleName(), datosAdicionales);
    }

    /**
     * Registra una operación de auditoría para cumplimiento regulatorio.
     * 
     * @param usuario Identificador del usuario que realizó la acción
     * @param accion Acción realizada (CREATE, UPDATE, DELETE, READ)
     * @param recurso Recurso afectado
     * @param resultado Outcome de la operación
     */
    public void registrarAuditoria(String usuario, String accion, String recurso, String resultado) {
        String timestamp = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String mensaje = String.format("[%s] AUDITORIA | Usuario: %s | Acción: %s | Recurso: %s | Resultado: %s",
                timestamp, usuario, accion, recurso, resultado);
        
        auditLogger.info(mensaje);
    }

    /**
     * Registra métricas operativas del sistema.
     * Útil para monitoreo de rendimiento y capacidad.
     * 
     * @param metricName Nombre de la métrica
     * @param value Valor de la métrica
     * @param unidad Unidad de medida
     */
    public void registrarMetrica(String metricName, double value, String unidad) {
        String timestamp = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String mensaje = String.format("[%s] METRICA | %s: %.2f %s",
                timestamp, metricName, value, unidad);
        applicationLogger.info(mensaje);
    }

    /**
     * Verifica si el sistema de logging está correctamente configurado.
     * 
     * @return true si los directorios y archivos están accesibles
     */
    public boolean verificarConfiguracion() {
        Path logPath = Paths.get(LOG_DIRECTORY);
        boolean directorioValido = Files.isDirectory(logPath) && Files.isWritable(logPath);
        
        if (!directorioValido) {
            errorLogger.error("Configuración de logging inválida: directorio {} no accesible", LOG_DIRECTORY);
        }
        
        return directorioValido;
    }

    /**
     * Limpia archivos de log antiguos según la política de retención.
     * Este método debería ejecutarse como tarea programada.
     */
    public void limpiarLogsAntiguos() {
        try {
            Path logPath = Paths.get(LOG_DIRECTORY);
            long cutoffTime = System.currentTimeMillis() - (MAX_HISTORY * 24L * 60 * 60 * 1000);
            
            Files.list(logPath)
                .filter(path -> path.toString().endsWith(".log"))
                .filter(path -> {
                    try {
                        return Files.getLastModifiedTime(path).toMillis() < cutoffTime;
                    } catch (IOException e) {
                        return false;
                    }
                })
                .forEach(path -> {
                    try {
                        Files.delete(path);
                        applicationLogger.info("Archivo de log eliminado: {}", path.getFileName());
                    } catch (IOException e) {
                        errorLogger.error("Error al eliminar archivo de log antiguo: {}", path, e);
                    }
                });
        } catch (IOException e) {
            errorLogger.error("Error durante la limpieza de logs antiguos", e);
        }
    }
}