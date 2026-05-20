package org.PabloGonzalez.TasksTraker.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.PabloGonzalez.TasksTraker.model.Task;
import org.PabloGonzalez.TasksTraker.repository.TaskRepository;

import java.util.List;

public class MainView {

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
        loadTable();
        setupColumns();
    }

    private void loadTable() {
        List<Task> tasks = repository.findAll();
        tableView.getItems().setAll(tasks);
    }

    private void setupColumns() {

        nameCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getName())
        );

        descCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription())
        );

        hoursCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getHours())
        );

        statusCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus())
        );

        setupActionsColumn();
    }

    private void setupActionsColumn() {

        actionsCol.setCellFactory(col -> new TableCell<>() {

            private final Button addHoursBtn = new Button("+");
            private final Button deleteBtn = new Button("X");

            {
                addHoursBtn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    System.out.println("Añadir horas a: " + task.getName());
                    // aquí llamas tu dialog
                });

                deleteBtn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    repository.deleteById(task.getId());
                    loadTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new javafx.scene.layout.HBox(5, addHoursBtn, deleteBtn));
                }
            }
        });
    }

    // BOTONES SUPERIORES
    @FXML
    private void onNewTask() {
        System.out.println("Nueva tarea");
        // aquí abrirás diálogo
    }

    @FXML
    private void onEditTask() {
        System.out.println("Editar");
    }

    @FXML
    private void onDeleteTask() {
        System.out.println("Eliminar");
    }
}