package org.PabloGonzalez.TasksTraker.repository;

import org.PabloGonzalez.TasksTraker.database.DatabaseManager;
import org.PabloGonzalez.TasksTraker.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public void save(Task task) {

        String sql = """
                INSERT INTO tasks (
                    name,
                    description,
                    hours,
                    status
                )
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = DatabaseManager.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setDouble(3, task.getHours());
            stmt.setString(4, task.getStatus());

            stmt.executeUpdate();

            System.out.println("Tarea guardada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (
                Connection conn = DatabaseManager.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {
                Task task = new Task();

                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setHours(rs.getDouble("hours"));
                task.setStatus(rs.getString("status"));

                tasks.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public Task findById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";

        try (
                Connection conn = DatabaseManager.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Task task = new Task();

                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setHours(rs.getDouble("hours"));
                task.setStatus(rs.getString("status"));
                return task;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void update(Task task) {

        String sql = """
                UPDATE tasks
                SET
                    name = ?,
                    description = ?,
                    hours = ?,
                    status = ?
                WHERE id = ?
                """;

        try (
                Connection conn = DatabaseManager.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(2, task.getName());
            stmt.setString(3, task.getDescription());
            stmt.setDouble(5, task.getHours());
            stmt.setString(6, task.getStatus());
            stmt.setInt(7, task.getId());

            stmt.executeUpdate();

            System.out.println("Tarea actualizada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        try (
                Connection conn = DatabaseManager.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Tarea eliminada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}