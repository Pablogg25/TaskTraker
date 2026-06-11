package org.PabloGonzalez.TasksTraker.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    @FXML
    private VBox newTaskPanel;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;
    private final TaskRepository repository = new TaskRepository();

    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        setupColumns();
        setupActionsColumn();

        tableView.setItems(taskList);

        loadTasks();
    }

    // =========================
    // CARGA DE DATOS
    // =========================
    private void loadTasks() {

        List<Task> tasksFromDb = repository.findAll();

        //System.out.println("Tasks en BD: " + tasksFromDb.size());

        taskList.clear();
        taskList.addAll(tasksFromDb);
    }

    // =========================
    // COLUMNAS
    // =========================
    private void setupColumns() {

        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        descCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));

        hoursCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getHours()));

        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
    }

    // =========================
    // ACCIONES POR FILA
    // =========================
    private void setupActionsColumn() {

        actionsCol.setCellFactory(col -> new TableCell<>() {

            private final Button addHoursBtn = new Button("Añadir Horas");
            private final Button deleteBtn = new Button("Eliminar");

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
    // FLUJO DE CREACIÓN DE TAREA
    // =========================
    @FXML
    private void onNewTask() {
        newTaskPanel.setVisible(true);
        newTaskPanel.setManaged(true);
    }

    @FXML
    private void onCancelTask() {
        newTaskPanel.setVisible(false);
        newTaskPanel.setManaged(false);
        clearForm();
    }

    @FXML
    private void onSaveTask() {

        // =========================
        // MODO EDICIÓN
        // =========================
        if (editingTask != null) {

            editingTask.setName(nameField.getText());
            editingTask.setDescription(descriptionField.getText());

            repository.update(editingTask);

            editingTask = null;

        } else {

            // =========================
            // MODO CREACIÓN
            // =========================
            Task task = new Task();

            task.setName(nameField.getText());
            task.setDescription(descriptionField.getText());
            task.setHours(0);
            task.setStatus("ACTIVA");

            repository.save(task);
        }

        // =========================
        // REFRESCAR UI
        // =========================
        loadTasks();
        clearForm();

        newTaskPanel.setVisible(false);
        newTaskPanel.setManaged(false);
    }

    private void clearForm() {
        nameField.clear();
        descriptionField.clear();
    }

    //=========================
    // FLUJO DE EDICIÓN DE TASK
    //==========================
    private Task editingTask = null;

    @FXML
    private void onEditTask() {

        Task selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Selecciona una tarea");
            alert.showAndWait();

            return;
        }

        editingTask = selected;

        nameField.setText(selected.getName());
        descriptionField.setText(selected.getDescription());

        newTaskPanel.setVisible(true);
        newTaskPanel.setManaged(true);
    }

    //=========================
    // FLUJO DE ELIMINACIÓN DE TASK
    //==========================
    @FXML
    private void onDeleteTask() {

        Task selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Selecciona una tarea");
            alert.showAndWait();

            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);

        confirm.setTitle("Eliminar tarea");
        confirm.setHeaderText("¿Eliminar tarea?");
        confirm.setContentText(selected.getName());

        confirm.showAndWait().ifPresent(result -> {

            if (result == ButtonType.OK) {

                repository.deleteById(selected.getId());

                loadTasks();
            }
        });
    }
}