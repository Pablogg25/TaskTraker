package org.PabloGonzalez.TasksTraker.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        System.out.println("DB REAL PATH: " +
                new java.io.File("tracker.db").getAbsolutePath());

        System.out.println("WORKING DIR: " +
                System.getProperty("user.dir"));
        String sql = """
                CREATE TABLE IF NOT EXISTS tasks (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                name TEXT NOT NULL,
                                description TEXT,
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