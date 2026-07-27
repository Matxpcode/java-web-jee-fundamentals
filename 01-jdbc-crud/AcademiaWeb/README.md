# Sistema de Gestión de Docentes — Mantenimiento CRUD

<img width=50% alt="parte1-crud" src="https://github.com/user-attachments/assets/158574d7-de1a-4b05-9ce1-b24f3aa4a8a8" />

<img width=50% alt="parte2-crud" src="https://github.com/user-attachments/assets/0d044729-381a-43e5-be8b-030dd0a7f6bc" />

<img width=50% alt="parte3-crud" src="https://github.com/user-attachments/assets/07a0ba07-bad1-44d8-8269-5fa27e6828d7" />

<img width=50% alt="parte4-crud" src="https://github.com/user-attachments/assets/918d7826-a346-45ff-82ee-41dd9cf0328b" />

<img width=50% alt="parte5-crud" src="https://github.com/user-attachments/assets/8e0e2362-2a76-4e55-aaa1-41abf04a0294" />

<img width=50% alt="parte6-crud" src="https://github.com/user-attachments/assets/842a3c34-d9f8-4618-9fec-d54c08e8ce83" />

### El Problema
Las instituciones educativas que gestionan la información de su personal docente mediante procesos manuales o archivos descentralizados enfrentan inconsistencia en los datos, riesgo de duplicidad, falta de validación de correos/identificadores y pérdida de información al no contar con un repositorio centralizado.

### La Solución
Se desarrolló una **aplicación web centralizada para la administración de profesores (CRUD)**. La solución automatiza el ciclo de vida completo de los registros de docentes (Crear, Leer, Actualizar y Eliminar) asegurando la persistencia en una base de datos relacional y ofreciendo una interfaz clara y segura para el usuario administrativo.

---

## 🛠️ Tecnologías y Herramientas

* **Lenguaje de Programación:** Java 21 (Java EE / Jakarta EE)
* **Arquitectura & Patrones:** MVC (Modelo-Vista-Controlador) y Patrón DAO (Data Access Object)
* **Controlador Web:** Java Servlets (`HttpServlet` con enrutamiento dinámico por `getPathInfo()`)
* **Acceso a Datos:** JDBC puro (`PreparedStatement`, `ResultSet`, `DriverManager`)
* **Base de Datos:** MySQL Server
* **Interfaz de Usuario (Vistas):** JSP (JavaServer Pages/Scriptlets/Expresiones) & Bootstrap 5
* **Servidor de Aplicaciones:** Apache Tomcat 9.0+
* **IDE & Control de Versiones:** Eclipse IDE & Git / GitHub

---

## ⚙️ Funcionalidades Implementadas

* **Listado de Profesores:** Visualización en tiempo real de todos los docentes registrados en el sistema.
* **Registro de Nuevos Docentes:** Formulario de captura con redirección segura tras el guardado.
* **Edición de Información:** Búsqueda previa por ID para precargar datos en el formulario y actualizar nombres, apellidos y correo.
* **Eliminación Segura:** Borrado de registros mediante confirmación obligatoria en interfaz para evitar eliminaciones accidentales.
* **Enrutamiento Centralizado:** Un único Servlet (`ProfesorServlet`) actúa como controlador frontal administrando todas las peticiones bajo la ruta `/profesor/*`.

---

## 🛡️ Aspectos Destacados de la Implementación

* **Seguridad en Consultas:** Uso estricto de `PreparedStatement` para parametrizar valores y prevenir ataques de **Inyección SQL**.
* **Protección de Vistas:** Los archivos `.jsp` están ubicados dentro de `/WEB-INF/`, impidiendo que el usuario acceda directamente a las páginas sin pasar por la validación del Servlet.
* **Gestión Eficiente de Recursos:** Uso de bloques `try-with-resources` para garantizar el cierre automático de conexiones a la base de datos y evitar fugas de memoria (*Memory Leaks*).

---
