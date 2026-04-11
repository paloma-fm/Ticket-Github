# Sistema de Gestión de Tickets - DevOps

Este proyecto es un sistema de gestión de tickets desarrollado bajo prácticas de **Integración Continua (CI)**. Esta guía sirve como manual de buenas prácticas para asegurar un desarrollo colaborativo ordenado y eficiente.

---

## 1. Estrategia de Ramificación (GitFlow)

Para mantener la estabilidad del código, utilizamos el modelo **GitFlow**. Es fundamental respetar el propósito de cada rama:

| Rama | Propósito | ¿De dónde nace? | ¿Hacia dónde vuelve? |
| :--- | :--- | :--- | :--- |
| `main` | Código productivo y estable (Producción). | - | - |
| `develop` | Integración de todas las funciones nuevas. | `main` | `main` |
| `feature/` | Desarrollo de nuevas características. | `develop` | `develop` |
| `hotfix/` | Reparaciones urgentes de errores en producción. | `main` | `main` y `develop` |

---

## 2. Convenciones de Trabajo

### Mensajes de Commit (Conventional Commits)
Utilizamos estándares para que el historial sea legible. El formato es: `tipo: descripción`
* **`feat:`** Una nueva funcionalidad (Ej: `feat: agregar buscador de tickets`).
* **`fix:`** Corrección de un error (Ej: `fix: resolver error en fecha de creación`).
* **`docs:`** Cambios en la documentación (Ej: `docs: actualizar guía de instalación`).
* **`style:`** Cambios de formato (espacios, puntos y comas) que no afectan el código.

### Naming de Ramas
Las ramas deben nombrarse siguiendo el esquema: `tipo/breve-descripcion`
* Correcto: `feature/sistema-login`, `hotfix/error-db`.
* Incorrecto: `cambios-paloma`, `fix1`.

---

## 3. Flujo de Revisión (Pull Requests)

Nadie integra código directamente a las ramas principales. Seguimos este proceso:
1. **Crear rama:** Se trabaja en una rama `feature/` o `hotfix/`.
2. **Sincronizar:** Se suben los cambios al repositorio remoto (`git push origin feature/nombre`).
3. **Pull Request (PR):** Se solicita la revisión mediante un PR hacia la rama `develop`.
4. **Revisión:** Al menos un compañero debe revisar el código para detectar posibles errores antes de aceptar el merge.

---

## 4. Estructura de Carpetas y Versiones

El proyecto sigue una estructura estandarizada para equipos DevOps:
* `src/main/java`: Lógica de negocio y controladores.
* `src/test/java`: Pruebas de software.
* `.github/workflows`: Archivos de configuración de automatización (YAML).
* `pom.xml`: Gestión de dependencias y versión del proyecto (Maven).

---

## 5. Automatización (CI/CD)

Contamos con un pipeline automatizado en **GitHub Actions** para garantizar la calidad del código.

* **Ejecución:** Se activa automáticamente con cada `push` a la rama `develop` y con cada `pull_request` hacia `main`.
* **Proceso:** El servidor compila el código con Maven para verificar que no existan errores de sintaxis o dependencias rotas.
* **Optimización:** Usamos el flag `-DskipTests` en la nube para asegurar una compilación rápida cuando no hay bases de datos externas conectadas.

---

## Requisitos de Instalación
Si quieres ejecutar este proyecto localmente:
1. Clonar el repositorio: `git clone [url-del-repo]`
2. Asegúrate de tener **Java 17** instalado.
3. Ejecutar `./mvnw clean install` para descargar las dependencias.
