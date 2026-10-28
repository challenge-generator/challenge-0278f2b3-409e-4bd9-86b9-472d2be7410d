# Implementación de un Backend con Framework y ORM

En el contexto de una plataforma de préstamos hipotecarios, debes implementar un backend que gestione las solicitudes de préstamos. El sistema debe enrutar solicitudes, controlar variables de entorno, generar logs y conectarse a una base de datos utilizando un ORM. Los actores involucrados son Clientes, Administradores y el Sistema de Créditos Externo. El sistema debe manejar volúmenes pico de 1000 solicitudes por hora y garantizar una disponibilidad del 99.9%.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del Entorno

**Objetivo:** Configurar el entorno de desarrollo y enrutar solicitudes básicas.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configura el entorno de desarrollo con las variables de entorno necesarias.
- Implementa el enrutamiento de solicitudes para las operaciones básicas de creación y consulta de préstamos.

**Entregable:** Entorno de desarrollo configurado y enrutamiento de solicitudes funcional.

<details>
<summary>Pistas de conocimiento</summary>

- Configuración de variables de entorno.
- Uso de anotaciones para enrutamiento.

</details>

### Fase 2: Gestión de Logs y Control de Variables de Entorno

**Objetivo:** Implementar la generación de logs y el control de variables de entorno.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementa la generación de logs para registrar las operaciones realizadas.
- Asegura que el sistema pueda leer y utilizar las variables de entorno de forma segura.

**Entregable:** Sistema con generación de logs funcional y control de variables de entorno.

<details>
<summary>Pistas de conocimiento</summary>

- Uso de librerías para generación de logs.
- Prácticas seguras para el manejo de variables de entorno.

</details>

### Fase 3: Conexión con Base de Datos y Uso de ORM

**Objetivo:** Conectar el sistema a una base de datos y utilizar un ORM para gestionar las entidades.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Configura la conexión a la base de datos.
- Utiliza un ORM para mapear las entidades del dominio a tablas de la base de datos.

**Entregable:** Sistema conectado a la base de datos y utilizando un ORM para gestionar entidades.

<details>
<summary>Pistas de conocimiento</summary>

- Configuración de la conexión a la base de datos.
- Uso de anotaciones y métodos del ORM para mapear entidades.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un Framework y por qué se utiliza en desarrollo backend?
- **paraQueSirve**: ¿Para qué sirve el enrutamiento de solicitudes en un backend?
- **comoSeUsa**: ¿Cómo se utiliza un ORM para mapear entidades a tablas de base de datos?
- **erroresComunes**: ¿Cuáles son los errores comunes al configurar variables de entorno y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de un Framework para un proyecto backend?

## Criterios de Evaluacion

- Configuración correcta del entorno de desarrollo y enrutamiento de solicitudes.
- Implementación de generación de logs funcional.
- Control seguro y eficaz de variables de entorno.
- Conexión exitosa a la base de datos y uso correcto de ORM para gestionar entidades.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
