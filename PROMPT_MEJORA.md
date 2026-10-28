# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/prestamos/hipotecarios/Application.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/LoggingConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCase.java` — `Prestamo.getId`: Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCase.java` — `Prestamo.isPresent`: Se invoca `isPresent` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getId`: Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getClienteId`: Se invoca `getClienteId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getMonto`: Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getTasaInteres`: Se invoca `getTasaInteres` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getPlazoMeses`: Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getFechaInicio`: Se invoca `getFechaInicio` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getEstado`: Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.clienteId`: Se invoca `clienteId` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.monto`: Se invoca `monto` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.tasaInteres`: Se invoca `tasaInteres` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.plazoMeses`: Se invoca `plazoMeses` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.fechaInicio`: Se invoca `fechaInicio` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getId`: Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getClienteId`: Se invoca `getClienteId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getMonto`: Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getTasaInteres`: Se invoca `getTasaInteres` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getPlazoMeses`: Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getFechaInicio`: Se invoca `getFechaInicio` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getEstado`: Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `DatabaseConfig.getHost`: Se invoca `getHost` sobre `DatabaseConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `DatabaseConfig.getPort`: Se invoca `getPort` sobre `DatabaseConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `DatabaseConfig.getName`: Se invoca `getName` sobre `DatabaseConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `SecurityConfig.isJwtEnabled`: Se invoca `isJwtEnabled` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `ExternalServiceConfig.getCreditSystemUrl`: Se invoca `getCreditSystemUrl` sobre `ExternalServiceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `PerformanceConfig.getMaxConnections`: Se invoca `getMaxConnections` sobre `PerformanceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `FeatureFlags.isAsyncProcessing`: Se invoca `isAsyncProcessing` sobre `FeatureFlags`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Misión / candidato
El candidato es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

### Reto
- Tema: Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Implementación de un Backend con Framework y ORM
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno — objetivo: Configurar el entorno de desarrollo y enrutar solicitudes básicas. — entregable (NO resolver): Entorno de desarrollo configurado y enrutamiento de solicitudes funcional.
- Fase 2: Gestión de Logs y Control de Variables de Entorno — objetivo: Implementar la generación de logs y el control de variables de entorno. — entregable (NO resolver): Sistema con generación de logs funcional y control de variables de entorno.
- Fase 3: Conexión con Base de Datos y Uso de ORM — objetivo: Conectar el sistema a una base de datos y utilizar un ORM para gestionar las entidades. — entregable (NO resolver): Sistema conectado a la base de datos y utilizando un ORM para gestionar entidades.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.prestamos.hipotecarios</groupId>
    <artifactId>prestamos-hipotecarios</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>prestamos-hipotecarios</name>
    <description>Sistema de gestión de préstamos hipotecarios</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>

        <!-- Base de datos -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.3.230</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.3</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.11.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/prestamos/hipotecarios/Application.java ===
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

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: prestamos-hipotecarios
  profiles:
    active: dev
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/prestamos_hipotecarios}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:postgres}
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      maximum-pool-size: 10
      minimum-idle: 2
      idle-timeout: 600000
      max-lifetime: 1800000
      pool-name: PrestamosHikariPool
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        jdbc:
          time_zone: UTC
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: true

logging:
  level:
    root: INFO
    com.prestamos.hipotecarios: DEBUG
    org.springframework.web: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/${spring.application.name}.log

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,env,beans
  endpoint:
    health:
      show-details: always
  info:
    env:
      enabled: true

server:
  port: ${SERVER_PORT:8080}
  servlet:
    context-path: /api/prestamos
  error:
    include-message: always
    include-binding-errors: always
    include-stacktrace: never
    include-exception: false

app:
  external:
    credit-system:
      url: ${CREDIT_SYSTEM_URL:http://localhost:8081/api/creditos}
      timeout: 5000
      retries: 3

// === ARCHIVO: src/main/java/com/prestamos/hipotecarios/domain/model/Prestamo.java ===
package com.prestamos.hipotecarios.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prestamo {
    private UUID id;

    @NotNull(message = "El cliente es obligatorio")
    private UUID clienteId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    private BigDecimal monto;

    @NotNull(message = "La tasa de interés es obligatoria")
    @Positive(message = "La tasa de interés debe ser positiva")
    private BigDecimal tasaInteres;

    @NotNull(message = "El plazo en meses es obligatorio")
    @Positive(message = "El plazo en meses debe ser positivo")
    private Integer plazoMeses;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "El estado es obligatorio")
    @Size(min = 3, max = 20, message = "El estado debe tener entre 3 y 20 caracteres")
    private String estado;

    public Prestamo() {
        this.id = UUID.randomUUID();
        this.estado = "PENDIENTE";
    }

    public Prestamo(UUID clienteId, BigDecimal monto, BigDecimal tasaInteres, Integer plazoMeses, LocalDate fechaInicio) {
        this();
        this.clienteId = clienteId;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.plazoMeses = plazoMeses;
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal calcularCuotaMensual() {
        BigDecimal tasaMensual = tasaInteres.divide(new BigDecimal("12"), 10, BigDecimal.ROUND_HALF_UP)
                .divide(new BigDecimal("100"), 10, BigDecimal.ROUND_HALF_UP);
        BigDecimal plazo = new BigDecimal(plazoMeses);
        BigDecimal factor = tasaMensual.add(BigDecimal.ONE).pow(plazoMeses);
        return monto.multiply(tasaMensual).multiply(factor)
                .divide(factor.subtract(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);
    }

    public boolean esActivo() {
        return "ACTIVO".equals(estado);
    }

    public boolean puedeSerAprobado() {
        return "PENDIENTE".equals(estado);
    }
}

// === ARCHIVO: src/main/java/com/prestamos/hipotecarios/domain/ports/PrestamoRepository.java ===
package com.prestamos.hipotecarios.domain.ports;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(UUID id);
    List<Prestamo> findByClienteId(UUID clienteId);
    List<Prestamo> findByEstado(String estado);
    void deleteById(UUID id);
}

// === ARCHIVO: src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/JpaPrestamoRepository.java ===
package com.prestamos.hipotecarios.infrastructure.persistence;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import com.prestamos.hipotecarios.domain.ports.PrestamoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaPrestamoRepository implements PrestamoRepository {
    private final SpringDataPrestamoRepository repository;

    public JpaPrestamoRepository(SpringDataPrestamoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        PrestamoEntity entity = PrestamoEntity.fromDomain(prestamo);
        PrestamoEntity savedEntity = repository.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Prestamo> findById(UUID id) {
        return repository.findById(id).map(PrestamoEntity::toDomain);
    }

    @Override
    public List<Prestamo> findByClienteId(UUID clienteId) {
        return repository.findByClienteId(clienteId).stream()
                .map(PrestamoEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Prestamo> findByEstado(String estado) {
        return repository.findByEstado(estado).stream()
                .map(PrestamoEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}

// === ARCHIVO: src/main/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCase.java ===
package com.prestamos.hipotecarios.application.usecase;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import com.prestamos.hipotecarios.domain.ports.PrestamoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CrearPrestamoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CrearPrestamoUseCase.class);
    private static final BigDecimal MONTO_MINIMO = new BigDecimal("10000.00");
    private static final BigDecimal MONTO_MAXIMO = new BigDecimal("5000000.00");
    private static final BigDecimal TASA_MINIMA = new BigDecimal("0.05");
    private static final BigDecimal TASA_MAXIMA = new BigDecimal("0.25");
    private static final Integer PLAZO_MINIMO = 12;
    private static final Integer PLAZO_MAXIMO = 360;

    private final PrestamoRepository prestamoRepository;

    public CrearPrestamoUseCase(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Transactional
    public Prestamo ejecutar(UUID clienteId, BigDecimal monto, BigDecimal tasaInteres, 
                             Integer plazoMeses, LocalDate fechaInicio) {
        logger.info("Iniciando proceso de creación de préstamo para cliente: {}", clienteId);
        
        validarParametros(clienteId, monto, tasaInteres, plazoMeses, fechaInicio);
        
        Prestamo prestamo = new Prestamo(clienteId, monto, tasaInteres, plazoMeses, fechaInicio);
        
        if (!puedeSerAprobado(monto, tasaInteres, plazoMeses)) {
            logger.warn("Préstamo no aprobado para cliente {}: no cumple criterios de riesgo", clienteId);
            throw new IllegalArgumentException("El préstamo no cumple los criterios de aprobación");
        }
        
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);
        logger.info("Préstamo creado exitosamente con ID: {} para cliente: {}", 
                   prestamoGuardado.getId(), clienteId);
        
        return prestamoGuardado;
    }

    private void validarParametros(UUID clienteId, BigDecimal monto, BigDecimal tasaInteres, 
                                   Integer plazoMeses, LocalDate fechaInicio) {
        if (clienteId == null) {
            logger.error("Error de validación: el ID del cliente no puede ser nulo");
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }
        
        if (monto == null || monto.compareTo(MONTO_MINIMO) < 0 || monto.compareTo(MONTO_MAXIMO) > 0) {
            logger.error("Error de validación: monto {} fuera de rango permitido [{}, {}]", 
                        monto, MONTO_MINIMO, MONTO_MAXIMO);
            throw new IllegalArgumentException(
                String.format("El monto debe estar entre %s y %s", MONTO_MINIMO, MONTO_MAXIMO));
        }
        
        if (tasaInteres == null || tasaInteres.compareTo(TASA_MINIMA) < 0 || 
            tasaInteres.compareTo(TASA_MAXIMA) > 0) {
            logger.error("Error de validación: tasa de interés {} fuera de rango permitido [{}, {}]", 
                        tasaInteres, TASA_MINIMA, TASA_MAXIMA);
            throw new IllegalArgumentException(
                String.format("La tasa de interés debe estar entre %s y %s", TASA_MINIMA, TASA_MAXIMA));
        }
        
        if (plazoMeses == null || plazoMeses < PLAZO_MINIMO || plazoMeses > PLAZO_MAXIMO) {
            logger.error("Error de validación: plazo {} meses fuera de rango permitido [{}, {}]", 
                        plazoMeses, PLAZO_MINIMO, PLAZO_MAXIMO);
            throw new IllegalArgumentException(
                String.format("El plazo debe estar entre %d y %d meses", PLAZO_MINIMO, PLAZO_MAXIMO));
        }
        
        if (fechaInicio == null || fechaInicio.isBefore(LocalDate.now())) {
            logger.error("Error de validación: fecha de inicio {} inválida", fechaInicio);
            throw new IllegalArgumentException("La fecha de inicio debe ser hoy o una fecha futura");
        }
        
        logger.debug("Todos los parámetros validados correctamente para cliente: {}", clienteId);
    }

    private boolean puedeSerAprobado(BigDecimal monto, BigDecimal tasaInteres, Integer plazoMeses) {
        BigDecimal cuotaMensual = monto.multiply(tasaInteres)
            .divide(BigDecimal.valueOf(12), 2, java.math.RoundingMode.HALF_UP)
            .multiply(BigDecimal.ONE.add(tasaInteres.divide(BigDecimal.valueOf(12), 10, 
                java.math.RoundingMode.HALF_UP)).pow(plazoMeses))
            .divide(BigDecimal.ONE.add(tasaInteres.divide(BigDecimal.valueOf(12), 10, 
                java.math.RoundingMode.HALF_UP)).pow(plazoMeses).subtract(BigDecimal.ONE), 
                2, java.math.RoundingMode.HALF_UP);
        
        BigDecimal ingresoEstimado = monto.divide(BigDecimal.valueOf(plazoMeses), 2, 
            java.math.RoundingMode.HALF_UP);
        
        return cuotaMensual.compareTo(ingresoEstimado.multiply(new BigDecimal("0.35"))) < 0;
    }

    public Optional<Prestamo> buscarPorId(UUID id) {
        logger.info("Buscando préstamo con ID: {}", id);
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if (prestamo.isPresent()) {
            logger.debug("Préstamo encontrado: {}", id);
        } else {
            logger.warn("No se encontró préstamo con ID: {}", id);
        }
        return prestamo;
    }

    public List<Prestamo> buscarPorCliente(UUID clienteId) {
        logger.info("Buscando préstamos para cliente: {}", clienteId);
        List<Prestamo> prestamos = prestamoRepository.findByClienteId(clienteId);
        logger.debug("Se encontraron {} préstamos para cliente: {}", prestamos.size(), clienteId);
        return prestamos;
    }

    public List<Prestamo> buscarPorEstado(String estado) {
        logger.info("Buscando préstamos con estado: {}", estado);
        List<Prestamo> prestamos = prestamoRepository.findByEstado(estado);
        logger.debug("Se encontraron {} préstamos con estado: {}", prestamos.size(), estado);
        return prestamos;
    }

    public void eliminarPrestamo(UUID id) {
        logger.info("Eliminando préstamo con ID: {}", id);
        if (prestamoRepository.findById(id).isEmpty()) {
            logger.error("No se puede eliminar: préstamo con ID {} no existe", id);
            throw new IllegalArgumentException("El préstamo no existe");
        }
        prestamoRepository.deleteById(id);
        logger.info("Préstamo eliminado exitosamente: {}", id);
    }
}

// === ARCHIVO: src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java ===
package com.prestamos.hipotecarios.infrastructure.persistence;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestamos", indexes = {
    @Index(name = "idx_prestamo_cliente_id", columnList = "cliente_id"),
    @Index(name = "idx_prestamo_estado", columnList = "estado"),
    @Index(name = "idx_prestamo_fecha_inicio", columnList = "fecha_inicio")
})
public class PrestamoEntity {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoEntity.class);

    @Id
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "cliente_id", nullable = false, columnDefinition = "UUID")
    private UUID clienteId;

    @Column(name = "monto", nullable = false, precision = 19, scale = 2)
    private BigDecimal monto;

    @Column(name = "tasa_interes", nullable = false, precision = 5, scale = 4)
    private BigDecimal tasaInteres;

    @Column(name = "plazo_meses", nullable = false)
    private Integer plazoMeses;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "cuota_mensual", precision = 19, scale = 2)
    private BigDecimal cuotaMensual;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    public PrestamoEntity() {
    }

    public static PrestamoEntity fromDomain(Prestamo prestamo) {
        logger.debug("Convirtiendo dominio Prestamo a entidad: {}", prestamo.getId());
        PrestamoEntity entity = new PrestamoEntity();
        entity.setId(prestamo.getId());
        entity.setClienteId(prestamo.getClienteId());
        entity.setMonto(prestamo.getMonto());
        entity.setTasaInteres(prestamo.getTasaInteres());
        entity.setPlazoMeses(prestamo.getPlazoMeses());
        entity.setFechaInicio(prestamo.getFechaInicio());
        entity.setEstado(prestamo.getEstado());
        entity.setCuotaMensual(prestamo.calcularCuotaMensual());
        entity.setFechaCreacion(LocalDate.now());
        entity.setFechaActualizacion(LocalDate.now());
        logger.debug("Entidad creada exitosamente para préstamo: {}", entity.getId());
        return entity;
    }

    public Prestamo toDomain() {
        logger.debug("Convirtiendo entidad a dominio Prestamo: {}", this.id);
        Prestamo prestamo = new Prestamo(
            this.clienteId,
            this.monto,
            this.tasaInteres,
            this.plazoMeses,
            this.fechaInicio
        );
        logger.debug("Dominio creado exitosamente para préstamo: {}", this.id);
        return prestamo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(BigDecimal cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        return "PrestamoEntity{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", monto=" + monto +
                ", tasaInteres=" + tasaInteres +
                ", plazoMeses=" + plazoMeses +
                ", fechaInicio=" + fechaInicio +
                ", estado='" + estado + '\'' +
                ", cuotaMensual=" + cuotaMensual +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java ===
package com.prestamos.hipotecarios.infrastructure.web;

import com.prestamos.hipotecarios.application.usecase.CrearPrestamoUseCase;
import com.prestamos.hipotecarios.domain.model.Prestamo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoController.class);

    private final CrearPrestamoUseCase crearPrestamoUseCase;

    public PrestamoController(CrearPrestamoUseCase crearPrestamoUseCase) {
        this.crearPrestamoUseCase = crearPrestamoUseCase;
    }

    @PostMapping
    public ResponseEntity<PrestamoResponse> crearPrestamo(@Valid @RequestBody PrestamoRequest request) {
        logger.info("Recibida solicitud de creación de préstamo para cliente: {}", request.clienteId());
        
        Prestamo prestamo = crearPrestamoUseCase.ejecutar(
            request.clienteId(),
            request.monto(),
            request.tasaInteres(),
            request.plazoMeses(),
            request.fechaInicio()
        );
        
        logger.info("Préstamo creado exitosamente con ID: {}", prestamo.getId());
        PrestamoResponse response = PrestamoResponse.fromDomain(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> obtenerPrestamo(@PathVariable UUID id) {
        logger.info("Consultando préstamo con ID: {}", id);
        
        return crearPrestamoUseCase.buscarPorId(id)
            .map(prestamo -> {
                logger.debug("Préstamo encontrado: {}", id);
                return ResponseEntity.ok(PrestamoResponse.fromDomain(prestamo));
            })
            .orElseGet(() -> {
                logger.warn("Préstamo no encontrado: {}", id);
                return ResponseEntity.notFound().build();
            });
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PrestamoResponse>> obtenerPrestamosPorCliente(@PathVariable UUID clienteId) {
        logger.info("Consultando préstamos para cliente: {}", clienteId);
        
        List<PrestamoResponse> prestamos = crearPrestamoUseCase.buscarPorCliente(clienteId)
            .stream()
            .map(PrestamoResponse::fromDomain)
            .toList();
        
        logger.debug("Se encontraron {} préstamos para cliente: {}", prestamos.size(), clienteId);
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> obtenerPrestamosPorEstado(@RequestParam String estado) {
        logger.info("Consultando préstamos con estado: {}", estado);
        
        List<PrestamoResponse> prestamos = crearPrestamoUseCase.buscarPorEstado(estado)
            .stream()
            .map(PrestamoResponse::fromDomain)
            .toList();
        
        logger.debug("Se encontraron {} préstamos con estado: {}", prestamos.size(), estado);
        return ResponseEntity.ok(prestamos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable UUID id) {
        logger.info("Solicitud de eliminación de préstamo: {}", id);
        
        try {
            crearPrestamoUseCase.eliminarPrestamo(id);
            logger.info("Préstamo eliminado exitosamente: {}", id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            logger.error("Error al eliminar préstamo {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    public record PrestamoRequest(
        @NotNull(message = "El ID del cliente es obligatorio")
        UUID clienteId,
        
        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser positivo")
        BigDecimal monto,
        
        @NotNull(message = "La tasa de interés es obligatoria")
        @Positive(message = "La tasa de interés debe ser positiva")
        BigDecimal tasaInteres,
        
        @NotNull(message = "El plazo en meses es obligatorio")
        @Positive(message = "El plazo debe ser positivo")
        @Size(min = 12, max = 360, message = "El plazo debe estar entre 12 y 360 meses")
        Integer plazoMeses,
        
        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate fechaInicio
    ) {}

    public record PrestamoResponse(
        UUID id,
        UUID clienteId,
        BigDecimal monto,
        BigDecimal tasaInteres,
        Integer plazoMeses,
        LocalDate fechaInicio,
        String estado,
        BigDecimal cuotaMensual
    ) {
        public static PrestamoResponse fromDomain(Prestamo prestamo) {
            return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getClienteId(),
                prestamo.getMonto(),
                prestamo.getTasaInteres(),
                prestamo.getPlazoMeses(),
                prestamo.getFechaInicio(),
                prestamo.getEstado(),
                prestamo.calcularCuotaMensual()
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/prestamos/hipotecarios/infrastructure/config/LoggingConfig.java ===
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

// === ARCHIVO: src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java ===
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

// === ARCHIVO: src/test/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCaseTest.java ===
package com.prestamos.hipotecarios.application.usecase;

import com.prestamos.hipotecarios.domain.model.Prestamo;
import com.prestamos.hipotecarios.domain.ports.PrestamoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para CrearPrestamoUseCase")
class CrearPrestamoUseCaseTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private CrearPrestamoUseCase crearPrestamoUseCase;

    private UUID clienteId;
    private BigDecimal monto;
    private BigDecimal tasaInteres;
    private Integer plazoMeses;
    private LocalDate fechaInicio;

    @BeforeEach
    void setUp() {
        clienteId = UUID.randomUUID();
        monto = new BigDecimal("250000.00");
        tasaInteres = new BigDecimal("0.065");
        plazoMeses = 240;
        fechaInicio = LocalDate.now();
    }

    @Test
    @DisplayName("Debe crear un préstamo exitosamente cuando los datos son válidos")
    void debeCrearPrestamoExitosamente() {
        Prestamo prestamoGuardado = new Prestamo(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );
        prestamoGuardado.esActivo();

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamoGuardado);

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.isPresent(), "El préstamo debe ser creado exitosamente");
        assertEquals(clienteId, resultado.get().getClienteId(), "El ID del cliente debe coincidir");
        assertEquals(monto, resultado.get().getMonto(), "El monto debe coincidir");
        assertEquals(tasaInteres, resultado.get().getTasaInteres(), "La tasa de interés debe coincidir");
        assertEquals(plazoMeses, resultado.get().getPlazoMeses(), "El plazo en meses debe coincidir");
        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe calcular correctamente la cuota mensual del préstamo")
    void debeCalcularCuotaMensualCorrectamente() {
        Prestamo prestamo = new Prestamo(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamo);

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        BigDecimal cuotaMensual = resultado.get().calcularCuotaMensual();
        assertNotNull(cuotaMensual, "La cuota mensual no debe ser nula");
        assertTrue(cuotaMensual.compareTo(BigDecimal.ZERO) > 0, "La cuota mensual debe ser mayor a cero");
    }

    @Test
    @DisplayName("Debe validar que el préstamo puede ser aprobado con datos válidos")
    void debeValidarAprobacionDelPrestamo() {
        Prestamo prestamo = new Prestamo(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamo);

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.get().puedeSerAprobado(), "El préstamo debe poder ser aprobado");
    }

    @Test
    @DisplayName("Debe marcar el préstamo como activo después de ser creado")
    void debeMarcarPrestamoComoActivo() {
        Prestamo prestamo = new Prestamo(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(prestamo);

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.get().esActivo(), "El préstamo debe estar activo");
    }

    @Test
    @DisplayName("Debe rechazar monto cero o negativo")
    void debeRechazarMontoCeroONegativo() {
        BigDecimal montoInvalido = BigDecimal.ZERO;

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, montoInvalido, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo con monto cero");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe rechazar tasa de interés negativa")
    void debeRechazarTasaInteresNegativa() {
        BigDecimal tasaNegativa = new BigDecimal("-0.05");

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaNegativa, plazoMeses, fechaInicio
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo con tasa negativa");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe rechazar plazo menor a un mes")
    void debeRechazarPlazoMenorAMes() {
        Integer plazoInvalido = 0;

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoInvalido, fechaInicio
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo con plazo cero");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe rechazar cliente nulo")
    void debeRechazarClienteNulo() {
        UUID clienteNulo = null;

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteNulo, monto, tasaInteres, plazoMeses, fechaInicio
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo sin cliente");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Debe rechazar fecha de inicio nula")
    void debeRechazarFechaInicioNula() {
        LocalDate fechaNula = null;

        Optional<Prestamo> resultado = crearPrestamoUseCase.ejecutar(
            clienteId, monto, tasaInteres, plazoMeses, fechaNula
        );

        assertTrue(resultado.isEmpty(), "No debe crear préstamo sin fecha de inicio");
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }
}

// === ARCHIVO: src/test/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoControllerTest.java ===
package com.prestamos.hipotecarios.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prestamos.hipotecarios.application.usecase.CrearPrestamoUseCase;
import com.prestamos.hipotecarios.domain.model.Prestamo;
import com.prestamos.hipotecarios.domain.ports.PrestamoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestamoController.class)
@DisplayName("Tests de integración para PrestamoController")
class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CrearPrestamoUseCase crearPrestamoUseCase;

    @MockBean
    private PrestamoRepository prestamoRepository;

    private UUID prestamoId;
    private UUID clienteId;
    private BigDecimal monto;
    private BigDecimal tasaInteres;
    private Integer plazoMeses;
    private LocalDate fechaInicio;
    private Prestamo prestamo;

    @BeforeEach
    void setUp() {
        prestamoId = UUID.randomUUID();
        clienteId = UUID.randomUUID();
        monto = new BigDecimal("300000.00");
        tasaInteres = new BigDecimal("0.0725");
        plazoMeses = 360;
        fechaInicio = LocalDate.now();

        prestamo = new Prestamo(clienteId, monto, tasaInteres, plazoMeses, fechaInicio);
    }

    @Test
    @DisplayName("Debe retornar 201 Created cuando el préstamo se crea exitosamente")
    void debeRetornar201CuandoPrestamoSeCrea() throws Exception {
        when(crearPrestamoUseCase.ejecutar(any(), any(), any(), any(), any()))
            .thenReturn(Optional.of(prestamo));

        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"monto\":300000.00,\"tasaInteres\":0.0725,\"plazoMeses\":360}"))
            .andExpect(status().isCreated())
            .andExpect(header().exists("Location"))
            .andExpect(jsonPath("$.clienteId").value(clienteId.toString()))
            .andExpect(jsonPath("$.monto").value(300000.00))
            .andExpect(jsonPath("$.tasaInteres").value(0.0725))
            .andExpect(jsonPath("$.plazoMeses").value(360));
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el monto es negativo")
    void debeRetornar400CuandoMontoEsNegativo() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"monto\":-1000,\"tasaInteres\":0.05,\"plazoMeses\":12}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el clienteId está vacío")
    void debeRetornar400CuandoClienteIdEstaVacio() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"monto\":100000,\"tasaInteres\":0.05,\"plazoMeses\":24}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el plazo es cero")
    void debeRetornar400CuandoPlazoEsCero() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"monto\":100000,\"tasaInteres\":0.05,\"plazoMeses\":0}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando la tasa de interés es negativa")
    void debeRetornar400CuandoTasaInteresEsNegativa() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"monto\":100000,\"tasaInteres\":-0.01,\"plazoMeses\":24}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando falta el campo monto")
    void debeRetornar400QuandoFaltaMonto() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"tasaInteres\":0.05,\"plazoMeses\":24}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 200 OK y el préstamo cuando se busca por ID")
    void debeRetornarPrestamoPorId() throws Exception {
        when(prestamoRepository.findById(prestamoId)).thenReturn(Optional.of(prestamo));

        mockMvc.perform(get("/api/v1/prestamos/" + prestamoId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.clienteId").value(clienteId.toString()))
            .andExpect(jsonPath("$.monto").value(300000.00))
            .andExpect(jsonPath("$.tasaInteres").value(0.0725))
            .andExpect(jsonPath("$.plazoMeses").value(360));
    }

    @Test
    @DisplayName("Debe retornar 404 Not Found cuando el préstamo no existe")
    void debeRetornar404CuandoPrestamoNoExiste() throws Exception {
        when(prestamoRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/prestamos/" + UUID.randomUUID()))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Debe retornar 200 OK y lista de préstamos por cliente")
    void debeRetornarPrestamosPorCliente() throws Exception {
        when(prestamoRepository.findByClienteId(clienteId)).thenReturn(java.util.List.of(prestamo));

        mockMvc.perform(get("/api/v1/prestamos").param("clienteId", clienteId.toString()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].clienteId").value(clienteId.toString()));
    }

    @Test
    @DisplayName("Debe retornar 200 OK y lista vacía cuando el cliente no tiene préstamos")
    void debeRetornarListaVaciaCuandoClienteSinPrestamos() throws Exception {
        when(prestamoRepository.findByClienteId(any())).thenReturn(java.util.List.of());

        mockMvc.perform(get("/api/v1/prestamos").param("clienteId", UUID.randomUUID().toString()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("Debe retornar 204 No Content cuando se elimina un préstamo exitosamente")
    void debeEliminarPrestamoExitosamente() throws Exception {
        mockMvc.perform(delete("/api/v1/prestamos/" + prestamoId))
            .andExpect(status().isNoContent());
    }
}
```
