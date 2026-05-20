package org.PabloGonzalez.TasksTraker.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.PabloGonzalez.TasksTraker.model.Task;
import org.PabloGonzalez.TasksTraker.repository.TaskRepository;

import java.util.List;

public class MainViewController {

    @FXML
    private TableView<Task> tableView;

    @FXML
    private TableColumn<Task, String> nameCol;

    @FXML
    private TableColumn<Task, String> descCol;

    @FXML
    private TableColumn<Task, Double> hoursCol;

    @FXML
    private TableColumn<Task, String> statusCol;

    @FXML
    private TableColumn<Task, Void> actionsCol;

    private final TaskRepository repository = new TaskRepository();

    @FXML
    public void initialize() {

        System.out.println("INIT OK");

        setupColumns();
        setupActionsColumn();
        loadTasks();
    }

    // =========================
    // CARGA DE DATOS
    // =========================
    private void loadTasks() {

        List<Task> taskList = repository.findAll();

        System.out.println("Tasks en BD: " + taskList.size());

        tableView.getItems().setAll(taskList);
    }

    // =========================
    // COLUMNAS
    // =========================
    private void setupColumns() {

        nameCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getName()
                )
        );

        descCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDescription()
                )
        );

        hoursCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getHours()
                )
        );

        statusCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getStatus()
                )
        );
    }

    // =========================
    // ACCIONES POR FILA
    // =========================
    private void setupActionsColumn() {

        actionsCol.setCellFactory(col -> new TableCell<>() {

            private final Button addHoursBtn = new Button("+");
            private final Button deleteBtn = new Button("X");

            private final HBox box = new HBox(5, addHoursBtn, deleteBtn);

            {

                addHoursBtn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    openAddHoursDialog(task);
                });

                deleteBtn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());

                    repository.deleteById(task.getId());

                    loadTasks();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(box);
                }
            }
        });
    }

    // =========================
    // DIALOG HORAS
    // =========================
    private void openAddHoursDialog(Task task) {

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Añadir horas");
        dialog.setHeaderText(task.getName());
        dialog.setContentText("Horas a añadir:");

        dialog.showAndWait().ifPresent(value -> {

            try {
                double hours = Double.parseDouble(value);

                task.setHours(task.getHours() + hours);

                repository.update(task);

                loadTasks();

            } catch (NumberFormatException e) {
                System.out.println("Valor inválido");
            }
        });
    }

    // =========================
    // BOTONES SUPERIORES
    // =========================
    @FXML
    private void onNewTask() {

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Nueva tarea");
        dialog.setHeaderText("Crear tarea");
        dialog.setContentText("Nombre:");


        dialog.showAndWait().ifPresent(name -> {

            Task task = new Task();
            task.setName(name);
            task.setDescription("");
            task.setHours(0);
            task.setStatus("ACTIVA");

            repository.save(task);

            loadTasks();
        });
    }

    @FXML
    private void onEditTask() {
        System.out.println("Editar tarea");
    }

    @FXML
    private void onDeleteTask() {
        System.out.println("Eliminar global");
    }
}