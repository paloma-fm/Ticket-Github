# Sistema de Gestión de Tickets - DevOps

## Estrategia de Ramificación 
Se implementó el modelo **GitFlow** para asegurar la estabilidad del código:
* **main**: Código productivo y estable.
* **develop**: Rama de integración para nuevas funcionalidades.

## Trabajo Colaborativo 
El proyecto cuenta con la participación de múltiples desarrolladores, utilizando **Pull Requests** para la revisión e integración de código y resolviendo conflictos de merge de forma técnica.

## Automatización (CI/CD) 
Se configuró **GitHub Actions** para automatizar la construcción del proyecto con Maven. 
* **Pipeline**: Se ejecuta en cada `push` y `pull_request`.
* **Optimización**: Se utiliza `-DskipTests` para garantizar una compilación exitosa en entornos de nube sin base de datos activa.

## Buenas Prácticas 
* Uso de **Conventional Commits** (`feat:`, `fix:`, `docs:`).
* Documentación técnica clara y estructura de carpetas estandarizada.
