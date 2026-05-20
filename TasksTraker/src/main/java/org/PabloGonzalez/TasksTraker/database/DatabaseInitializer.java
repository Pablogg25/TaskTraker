package org.PabloGonzalez.TasksTraker.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {

        String sql = """
                CREATE TABLE IF NOT EXISTS tasks (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                code TEXT NOT NULL,
                                name TEXT NOT NULL,
                                description TEXT,
                                project_name TEXT,
                                hours REAL DEFAULT 0,
                                status TEXT
                            );""";

        try (
                Connection conn = DatabaseManager.connect();
                Statement stmt = conn.createStatement()
        ) {

            stmt.execute(sql);

            System.out.println("Tabla tasks creada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}