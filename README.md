# TaskTraker

**Descripción**
- **Proyecto**: Aplicación de escritorio desarrollada con Java, JavaFX y SQLite.
- **Propósito**: Llevar un registro (contador) de las horas dedicadas a proyectos de programación para imputarlas en el programa de trabajo a fin de mes. Permite crear, editar y eliminar tareas y sumar horas trabajadas por tarea.

**Tecnologías**
- **Lenguaje**: Java
- **UI**: JavaFX (FXML)
- **Base de datos**: SQLite
- **Build**: Maven

**Instalación y ejecución**
- Abrir el proyecto en IntelliJ IDEA y ejecutar la clase `MainApp`.
- Alternativamente, desde la raíz del proyecto con Maven:

```bash
mvn clean package
mvn javafx:run
```

Si hay problemas con JavaFX al ejecutar desde línea de comandos, ejecutar desde IntelliJ es lo más sencillo.

**Uso**
- Crear una nueva tarea desde la interfaz (`new-task.fxml`).
- Editar o eliminar tareas existentes desde la vista principal (`main-view.fxml`).
- Añadir o restar tiempo a una tarea para llevar el conteo de horas dedicadas.

**Estructura relevante del proyecto**
- **Código fuente**: [src/main/java](src/main/java)
- **Clase principal**: [src/main/java/org/PabloGonzalez/TasksTraker/MainApp.java](src/main/java/org/PabloGonzalez/TasksTraker/MainApp.java)
- **Controlador principal**: [src/main/java/org/PabloGonzalez/TasksTraker/controller/MainViewController.java](src/main/java/org/PabloGonzalez/TasksTraker/controller/MainViewController.java)
- **Modelo de datos**: [src/main/java/org/PabloGonzalez/TasksTraker/model/Task.java](src/main/java/org/PabloGonzalez/TasksTraker/model/Task.java)
- **Repositorio**: [src/main/java/org/PabloGonzalez/TasksTraker/repository/TaskRepository.java](src/main/java/org/PabloGonzalez/TasksTraker/repository/TaskRepository.java)
- **Inicializador de BD**: [src/main/java/org/PabloGonzalez/TasksTraker/database/DatabaseInitializer.java](src/main/java/org/PabloGonzalez/TasksTraker/database/DatabaseInitializer.java)
- **Vistas (FXML)**: [src/main/resources/views/main-view.fxml](src/main/resources/views/main-view.fxml), [src/main/resources/views/new-task.fxml](src/main/resources/views/new-task.fxml)

**Base de datos**
- La aplicación usa SQLite y crea/abre la base de datos localmente al iniciarse. No se requiere configuración adicional; el archivo SQLite se gestiona automáticamente por la aplicación.

**Contribuciones**
- Si quieres mejorar funcionalidades (exportar horas, informes mensuales, sincronización), abre un issue o aporta un pull request.

**Contacto / Soporte**
- Autor: Pablo González García (Junior Developer)
- Para cambios o dudas abre un issue en el repositorio o contáctame directamente.


