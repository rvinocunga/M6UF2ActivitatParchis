package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexio {

    protected Connection connexio;

    // Driver
    private final String JBDC_DRIVER = "com.mysql.jdbc.Driver";
    // URL BBDD
    private final String DB_URL = "jdbc:mysql://localhost:3306/parchisjiaqiangroger";

    // Credencials
    private final String USER = "root";
    private final String PASSWORD = "1234";

    // 
    public void conectar() throws Exception{
        try {
            connexio = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Class.forName(JBDC_DRIVER);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
    
    public void cerrar() throws SQLException {
        if (connexio != null) {
            if (connexio.isClosed()) {
                connexio.close();
            }
        }
    }
    
}
