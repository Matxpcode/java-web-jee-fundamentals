# 🎓 AcademiaWeb - CRUD Java EE

Sistema web académico con CRUD completo de **Profesores** y **Cursos** (relación 1:N), desarrollado en Java EE con patrón MVC y DAO.

---

## Capturas del Modulo Profesor

<table>
  <tr>
    <td width="50%">
      <img src="https://github.com/user-attachments/assets/158574d7-de1a-4b05-9ce1-b24f3aa4a8a8" alt="parte1-crud">
      <p align="center"><b>1. Listado de Profesores</b></p>
    </td>
    <td width="50%">
      <img src="https://github.com/user-attachments/assets/0d044729-381a-43e5-be8b-030dd0a7f6bc" alt="parte2-crud">
      <p align="center"><b>2. Registrar Profesor</b></p>
    </td>
  </tr>
  <tr>
    <td width="50%">
      <img src="https://github.com/user-attachments/assets/918d7826-a346-45ff-82ee-41dd9cf0328b" alt="parte4-crud">
      <p align="center"><b>3. Editar Profesor</b></p>
    </td>
    <td width="50%">
      <img src="https://github.com/user-attachments/assets/842a3c34-d9f8-4618-9fec-d54c08e8ce83" alt="parte6-crud"/>
      <p align="center"><b>4. Eliminar Profesor</b></p>
    </td>
  </tr>
</table>

---

## Capturas del Modulo Curso

<table>
  <tr>
    <td width="50%">
      <img src="https://github.com/user-attachments/assets/85a64c4c-6d8e-41d4-b4a8-72910df5ce40" alt="curso_listado"/>
      <p align="center"><b>1. Listado de Cursos</b></p>
    </td>
    <td width="50%">
      <img src="https://github.com/user-attachments/assets/91d9b8f4-a4e6-46ed-9957-b4af7bf8e556" alt="curso_registro"/>
      <p align="center"><b>2. Registrar Curso</b></p>
    </td>
  </tr>
  <tr>
    <td width="50%">
      <img src="https://github.com/user-attachments/assets/a6bee6fc-4f66-46ed-9449-c1156f1fe909" alt="curso_editar"/>
      <p align="center"><b>3. Editar Curso</b></p>
    </td>
    <td width="50%">
      <img src="https://github.com/user-attachments/assets/b32c0061-9f8e-48de-8e4a-a6130090e92d" alt="curso_eliminar_listado"/>
      <p align="center"><b>4. Eliminar Curso (Preserva Historial)</b></p>
    </td>
  </tr>
</table>

---

## 🛠 Tecnologías

| Backend | Frontend | Base de Datos |
|---------|----------|---------------|
| Java 21 / 8+ | Bootstrap 5 | MySQL 8.0 |
| Servlets | | |
| JSP | | |
| JDBC | | |
| Tomcat 9/10 | | |

---

## ✨ Funcionalidades

### Cursos
- ✅ Listar con JOIN (trae datos del profesor)
- ✅ Crear asignando profesor, nivel (B/I/A), precio
- ✅ Editar (precarga datos y profesor seleccionado)
- ✅ Desactivar (baja lógica con confirmación)

### Profesores
- ✅ CRUD completo (Crear, Leer, Actualizar, Eliminar)

---

## 🏗️ Arquitectura

**Patrones:**
- **MVC:** Separación Vista/Controlador/Modelo
- **DAO:** Interfaz + Implementación (desacoplamiento)

---

## 📁 Estructura del Proyecto

```text
src/main/java/
├── model/          > Profesor.java, Curso.java
├── dao/            > Interfaces + Impl
├── servlet/        > Controladores
└── util/           > MySQLConexion.java

src/main/webapp/WEB-INF/
├── profesor/       > listar.jsp, nuevo.jsp, editar.jsp
└── curso/          > listar.jsp, nuevo.jsp, editar.jsp
```
