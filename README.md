# TaskTraker

**Descripción**
- **Proyecto**: Aplicación para gestionar y registrar horas dedicadas a proyectos de programación. Facilita crear, editar, eliminar tareas y contabilizar horas trabajadas para imputarlas al programa de trabajo a fin de mes.
- **Versión actual**: v1.0 (JavaFX) | **En desarrollo**: v2.0.0 (Spring Boot + Vue)

---

## 📋 Versión 1.0 - JavaFX (Actual)

**Descripción**: Aplicación de escritorio monolítica con JavaFX.

**Stack tecnológico**
- **Backend/UI**: Java 21 + JavaFX
- **Base de datos**: SQLite (local)
- **Build**: Maven

**Instalación y ejecución**
```bash
# Opción 1: Desde IntelliJ IDEA
# Ejecutar la clase MainApp directamente

# Opción 2: Desde línea de comandos
cd TasksTraker
mvn clean package
mvn javafx:run
```

**Estructura del proyecto (v1.0)**
```
TasksTraker/
├── src/main/java/org/PabloGonzalez/TasksTraker/
│   ├── MainApp.java                          # Punto de entrada de la aplicación
│   ├── Main.java                             # Lanzador alternativo
│   ├── Lanzador.java                         # Configuración de lanzamiento
│   ├── controller/
│   │   └── MainViewController.java           # Controlador principal (FXML)
│   ├── model/
│   │   └── Task.java                         # Modelo de datos (Tarea)
│   ├── repository/
│   │   └── TaskRepository.java               # Operaciones CRUD sobre BD
│   ├── database/
│   │   ├── DatabaseInitializer.java          # Inicialización de BD
│   │   └── DatabaseManager.java              # Gestión de conexiones
│   └── service/                              # (Vacío, preparado para lógica)
├── src/main/resources/
│   └── views/
│       ├── main-view.fxml                    # Vista principal (listado de tareas)
│       └── new-task.fxml                     # Vista para crear/editar tareas
├── pom.xml                                   # Configuración Maven
└── tracker.db                                # Base de datos SQLite (generada en runtime)
```

---

## 🚀 Versión 2.0.0 - Spring Boot + Vue (Planificado)

**Objetivo**: Modernizar la arquitectura separando backend (API REST) y frontend (SPA), mejorando la estética y la escalabilidad.

**Stack tecnológico planificado**
- **Backend**: Java 21 + Spring Boot 3.x (API REST)
- **Frontend**: Vue 3 + JavaScript (SPA moderna)
- **Base de datos**: SQLite (inicialmente, escalable a PostgreSQL)
- **Build**: Maven (backend) + Vite (frontend)
- **Estructura**: Monorepo

**Cambios principales**
- ✅ **Separación de responsabilidades**: Backend proporciona API REST, frontend consume la API
- ✅ **Mejora visual**: Interfaz moderna y responsiva con Vue 3
- ✅ **Mejor mantenibilidad**: Código más limpio y modular en ambas capas
- ✅ **Facilidad de despliegue**: Backend y frontend pueden desplegarse independientemente

**Estructura planificada (v2.0.0)**
```
TaskTraker/
├── backend/                                  # Spring Boot REST API
│   ├── src/main/java/org/PabloGonzalez/...
│   │   ├── controller/
│   │   │   └── TaskController.java           # Endpoints REST
│   │   ├── service/
│   │   │   └── TaskService.java              # Lógica de negocio
│   │   ├── repository/
│   │   │   └── TaskRepository.java           # Acceso a datos (Spring Data JPA)
│   │   ├── model/
│   │   │   └── Task.java                     # Entidad JPA
│   │   └── TaskTrackerApplication.java       # Punto de entrada
│   ├── src/main/resources/
│   │   └── application.properties            # Configuración Spring Boot
│   ├── pom.xml
│   └── tracker.db                            # Base de datos SQLite
│
├── frontend/                                 # Vue 3 SPA
│   ├── src/
│   │   ├── components/
│   │   │   ├── TaskList.vue                  # Listado de tareas
│   │   │   ├── TaskForm.vue                  # Formulario crear/editar
│   │   │   └── TaskCard.vue                  # Componente de tarea
│   │   ├── views/
│   │   │   └── MainView.vue                  # Vista principal
│   │   ├── api/
│   │   │   └── taskService.ts                # Cliente HTTP para API REST
│   │   ├── App.vue
│   │   └── main.ts
│   ├── vite.config.ts
│   ├── package.json
│   └── tsconfig.json
│
├── README.md                                 # Este archivo
└── pom.xml                                   # POM padre (opcional, para monorepo Maven)
```

**Próximos pasos para v2.0.0**
1. Crear estructura Maven con módulos backend y frontend
2. Configurar Spring Boot con dependencias iniciales (Web, Data JPA, SQLite)
3. Generar controladores REST para operaciones CRUD de tareas
4. Crear proyecto Vue 3 con Vite
5. Implementar cliente HTTP para consumir la API REST
6. Migrar componentes visuales a componentes Vue modernos
7. Configurar CORS en Spring Boot
8. Testing (JUnit para backend, Vitest para frontend)

---

## 📊 Comparativa v1.0 vs v2.0.0

| Aspecto | v1.0 (JavaFX) | v2.0.0 (Spring Boot + Vue) |
|--------|---------------|--------------------------|
| **UI** | JavaFX (escritorio) | Vue 3 (web, responsiva) |
| **Estética** | Antigua, estándar de sistemas | Moderna, personalizable |
| **Backend** | Monolítico | API REST independiente |
| **Base de datos** | SQLite directo | SQLite + Spring Data JPA |
| **Escalabilidad** | Limitada | Alta (separación de capas) |
| **Deployment** | JAR ejecutable | Backend JAR + Frontend assets |
| **Testing** | Manual | Automatizado (JUnit + Vitest) |

---

## 📝 Uso

### v1.0 (Actual)
- Crear nueva tarea desde la interfaz (`new-task.fxml`)
- Editar o eliminar tareas desde la vista principal (`main-view.fxml`)
- Sumar/restar tiempo para contabilizar horas

### v2.0.0 (Planificado)
- Interfaz web moderna y responsiva
- API REST para CRUD de tareas
- Mejor UX/UI con componentes Vue reutilizables

---

## 🤝 Contribuciones

Mejoras sugeridas para futuras versiones:
- Exportar horas a CSV/Excel
- Generación de informes mensuales
- Sincronización en la nube
- Autenticación de usuarios
- Categorías/proyectos

---

## 👤 Contacto / Soporte

- **Autor**: Pablo González García (Junior Developer)
- **Para cambios/dudas**: Abre un issue en el repositorio o contáctame directamente


