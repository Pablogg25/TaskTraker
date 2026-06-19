package org.PabloGonzalez.TasksTraker.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // Esto detecta automáticamente la ruta del usuario actual (C:\Users\TuUsuario)
    private static final String USER_HOME = System.getProperty("user.home");
    // Guarda la base de datos de manera segura e independiente en la carpeta del usuario
    private static final String URL = "jdbc:sqlite:" + USER_HOME + File.separator + "tracker.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
