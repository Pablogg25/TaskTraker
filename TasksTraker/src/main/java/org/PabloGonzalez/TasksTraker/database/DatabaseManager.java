package org.PabloGonzalez.TasksTraker.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:C:/Users/Usuario/Desktop/TaskTraker/TasksTraker/tracker.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
