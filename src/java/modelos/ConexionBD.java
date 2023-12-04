package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    public Connection conexionSQLServer = null;

    public ConexionBD() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost\\Chafazon:1433;databaseName=chafazon";
        String user = "sa";
        String password = "123";

        conexionSQLServer = DriverManager.getConnection(connectionUrl, user, password);
    }

    public ResultSet consultarUsuario(String correo, String contraseña) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE Correo = ? AND Contraseña = ?";
        PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
        sentencia.setString(1, correo);
        sentencia.setString(2, contraseña);
        return sentencia.executeQuery();
    }

    public double consultarSaldo(int i) throws SQLException {
        String sql = "SELECT Saldo FROM Usuarios WHERE ID = ?";
        PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
        sentencia.setInt(1, i);
        double result = 0;
        ResultSet rs = sentencia.executeQuery();
        while (rs.next()) {
            result = rs.getDouble("Saldo");
        }

        return result;
    }

    public double agregarFondos(int id) throws SQLException, SQLException {
        String sql = "UPDATE Usuarios SET Saldo = ? WHERE id = ?";
        PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
        double result = (int) consultarSaldo(id) + 100;
        sentencia.setInt(1, (int) result);
        sentencia.setInt(2, id);
        sentencia.execute();
        return result;

    }

    public ResultSet obtenerInfoUsuario(String correo) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE Correo = ?";
        PreparedStatement sentencia = null;
        ResultSet resultSet = null;

        try {
            sentencia = conexionSQLServer.prepareStatement(sql);
            sentencia.setString(1, correo);
            resultSet = sentencia.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al ejecutar la consulta SQL: " + e.getMessage());
            throw e;  // Re-lanzar la excepción para que sea manejada en el servlet
        }
    }

    public int insertarUsuario(String Nombre, String correo, String contrasena) throws SQLException {
        String sql = "INSERT INTO Usuarios (Nombre,Correo,Contraseña,Saldo,isAdmin) VALUES (?, ?, ?, 0, 0)";
        try ( PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql)) {
            sentencia.setString(1, Nombre);
            sentencia.setString(2, correo);
            sentencia.setString(3, contrasena);
            return sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void cerrarConexion() throws SQLException {
        if (conexionSQLServer != null && !conexionSQLServer.isClosed()) {
            conexionSQLServer.close();
        }
    }

}
