# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de un Backend con Framework y ORM**.

| | |
|---|---|
| Tema | Experiencia en Frameworks |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | capas estándar con separación de dominio e infraestructura (hexagonal light) |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-configuration-processor n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.projectlombok:lombok 1.18.34
- com.h2database:h2 2.3.230
- org.postgresql:postgresql 42.7.3
- org.springframework.boot:spring-boot-starter-test n/a
- org.junit.jupiter:junit-jupiter-api 5.11.0
- org.mockito:mockito-core 5.12.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración del Entorno**: Entorno de desarrollo configurado y enrutamiento de solicitudes funcional.
- **Fase 2 — Gestión de Logs y Control de Variables de Entorno**: Sistema con generación de logs funcional y control de variables de entorno.
- **Fase 3 — Conexión con Base de Datos y Uso de ORM**: Sistema conectado a la base de datos y utilizando un ORM para gestionar entidades.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (35)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/prestamos/hipotecarios/Application.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/config/LoggingConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCase.java` — `Prestamo.getId`
      Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCase.java` — `Prestamo.isPresent`
      Se invoca `isPresent` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getId`
      Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getClienteId`
      Se invoca `getClienteId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getMonto`
      Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getTasaInteres`
      Se invoca `getTasaInteres` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getPlazoMeses`
      Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getFechaInicio`
      Se invoca `getFechaInicio` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java` — `Prestamo.getEstado`
      Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.clienteId`
      Se invoca `clienteId` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.monto`
      Se invoca `monto` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.tasaInteres`
      Se invoca `tasaInteres` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.plazoMeses`
      Se invoca `plazoMeses` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `PrestamoRequest.fechaInicio`
      Se invoca `fechaInicio` sobre `PrestamoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getId`
      Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getClienteId`
      Se invoca `getClienteId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getMonto`
      Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getTasaInteres`
      Se invoca `getTasaInteres` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getPlazoMeses`
      Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getFechaInicio`
      Se invoca `getFechaInicio` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java` — `Prestamo.getEstado`
      Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `DatabaseConfig.getHost`
      Se invoca `getHost` sobre `DatabaseConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `DatabaseConfig.getPort`
      Se invoca `getPort` sobre `DatabaseConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `DatabaseConfig.getName`
      Se invoca `getName` sobre `DatabaseConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `SecurityConfig.isJwtEnabled`
      Se invoca `isJwtEnabled` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `ExternalServiceConfig.getCreditSystemUrl`
      Se invoca `getCreditSystemUrl` sobre `ExternalServiceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `PerformanceConfig.getMaxConnections`
      Se invoca `getMaxConnections` sobre `PerformanceConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java` — `FeatureFlags.isAsyncProcessing`
      Se invoca `isAsyncProcessing` sobre `FeatureFlags`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (13)

- `pom.xml`
- `src/main/java/com/prestamos/hipotecarios/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/prestamos/hipotecarios/domain/model/Prestamo.java`
- `src/main/java/com/prestamos/hipotecarios/domain/ports/PrestamoRepository.java`
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/JpaPrestamoRepository.java`
- `src/main/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCase.java`
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence/PrestamoEntity.java`
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoController.java`
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/LoggingConfig.java`
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config/EnvConfig.java`
- `src/test/java/com/prestamos/hipotecarios/application/usecase/CrearPrestamoUseCaseTest.java`
- `src/test/java/com/prestamos/hipotecarios/infrastructure/web/PrestamoControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/prestamos/hipotecarios`
- `src/main/java/com/prestamos/hipotecarios/application`
- `src/main/java/com/prestamos/hipotecarios/domain`
- `src/main/java/com/prestamos/hipotecarios/infrastructure`
- `src/main/java/com/prestamos/hipotecarios/infrastructure/config`
- `src/main/java/com/prestamos/hipotecarios/infrastructure/persistence`
- `src/main/java/com/prestamos/hipotecarios/infrastructure/web`
- `src/main/resources`
- `src/test/java/com/prestamos/hipotecarios`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar con separación de dominio e infraestructura (hexagonal light)**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).
- Mision: El candidato es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
