package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    public List<Usuario> consultarUsuariosTabla() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
        ResultSet rs =  sentencia.executeQuery();
        List<Usuario> usuarios = new ArrayList<>();
        while(rs.next()){   
                int id = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                String correo = rs.getString("Correo");
                String password = rs.getString("Contraseña");
                int saldo = rs.getInt("Saldo");
                boolean admin = rs.getBoolean("isAdmin");
                
                Usuario usuario = new Usuario(id, nombre, correo, password, saldo, admin);
                usuarios.add(usuario);
            }
            return usuarios;
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
    
    public List<Inventario> consultarInvntario(){
        String sql = "SELECT * FROM Inventario";
        try {
        PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
        ResultSet rs = sentencia.executeQuery();
            List<Inventario> invs = new ArrayList<>();
            while(rs.next()){   
                int id = rs.getInt("Id");
                String nombre = rs.getString("Nombre");
                String categoria = rs.getString("Categoria");
                int existencias = rs.getInt("Existencias");
                String imagen = rs.getString("Imagen");
                
                Inventario inv = new Inventario(id,nombre, categoria, existencias, imagen);
                invs.add(inv);
            }
            return invs;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    public Inventario obtenerInfoInventario(int id){
        String sql = "SELECT * FROM Inventario where Id = ?";
        PreparedStatement sentencia;
        try {
            sentencia = conexionSQLServer.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
               String nombre = rs.getString("Nombre"); 
               String categoria = rs.getString("Categoria"); 
               int existencias = rs.getInt("Existencias"); 
               String img = rs.getString("Imagen"); 
               Inventario inv = new Inventario(nombre, categoria, existencias, img);
               return inv;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String consultarImagenInventario(int id){
        String sql = "SELECT Imagen FROM Inventario where Id = ?";
        PreparedStatement sentencia;
        try {
            sentencia = conexionSQLServer.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                String img = rs.getString("Imagen"); 
               return img;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public int agregarInventario(String nombre, String categoria, int existencias, String imagen){
        String sql = "INSERT INTO Inventario ( Nombre ,Categoria ,Existencias ,Imagen) VALUES (? ,? ,? ,?)";
        try {
            PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, categoria);
            sentencia.setInt(3, existencias);
            sentencia.setString(4, imagen);
            return sentencia.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int eliminarInventario(int id){
        String sql = "DELETE FROM Inventario WHERE Id = ?";
        try {
            PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
            sentencia.setInt(1, id);
            return sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int agregarArticulo(String nombre, String categoria, int existencias, String imagen, int precio, boolean enOferta){
        String sql = "INSERT INTO Articulos (Nombre , Categoria, Existencias, Imagen, Precio, EnOferta) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, categoria);
            sentencia.setInt(3, existencias);
            sentencia.setString(4, imagen);
            sentencia.setInt(5, precio);
            sentencia.setBoolean(6, enOferta);
            return sentencia.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int eliminarArticulo(int id){
        String sql = "DELETE FROM Articulos WHERE Id = ?";
        try {
            PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
            sentencia.setInt(1, id);
            return sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<Articulo> consultarArticulo() {
        String sql = "SELECT * FROM Articulos";
        try {
        PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
        ResultSet rs = sentencia.executeQuery();
            List<Articulo> articulos = new ArrayList<>();
            while(rs.next()){   
                int id = rs.getInt("Id");
                String nombre = rs.getString("Nombre");
                String categoria = rs.getString("Categoria");
                int existencias = rs.getInt("Existencias");
                String imagen = rs.getString("Imagen");
                int precio = rs.getInt("Precio");
                boolean enOferta = rs.getBoolean("EnOferta");
                
                Articulo art = new Articulo(id, nombre, categoria, existencias, imagen, precio, enOferta);
                articulos.add(art);
            }
            return articulos;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    public List<Venta> consultarVentas(){
        String sql = "SELECT * FROM Ventas";
        try {
        PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
        ResultSet rs = sentencia.executeQuery();
            List<Venta> ventas = new ArrayList<>();
            while(rs.next()){   
                int id = rs.getInt("Id");
                String nombreComprador = rs.getString("NombreComprador");
                String nombreProducto = rs.getString("NombreProducto");
                int monto = rs.getInt("Monto");
                String imagen = rs.getString("ImagenProducto");
                Date fecha = rs.getDate("Fecha");
                                
                Venta venta = new Venta(id, nombreComprador, nombreProducto, imagen, monto, fecha);
                ventas.add(venta);
            }
            return ventas;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    public int agregarMonto(String nombreComprador, String nombreProducto, int monto, String imagen){
        String sql = "INSERT INTO Ventas (NombreComprador ,NombreProducto ,Monto ,ImagenProducto) VALUES (?,?,?,?)";
        try {
            PreparedStatement sentencia = conexionSQLServer.prepareStatement(sql);
            sentencia.setString(1, nombreComprador);
            sentencia.setString(2, nombreProducto);
            sentencia.setInt(3, monto);
            sentencia.setString(4, imagen);
            return sentencia.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    

}
