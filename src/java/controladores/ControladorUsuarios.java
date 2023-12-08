/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.ConexionBD;

/**
 *
 * @author alexc
 */
@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/ControladorUsuarios"})
public class ControladorUsuarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String option = request.getParameter("option");

            switch (option) {
                case "agregarUsuario":
                    String newName = request.getParameter("newName");
                    String newUsername = request.getParameter("newUsername");
                    String newPassword = request.getParameter("newPassword");

                    try {
                        ConexionBD conexionBD = new ConexionBD();
                        int result = conexionBD.insertarUsuario(newName, newUsername, newPassword);

                        if (result > 0) {

                            response.getWriter().write("Registro exitoso");
                        } else {

                            response.getWriter().write("Error en el registro. Inténtalo de nuevo.");
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                        response.getWriter().write("Error en el servidor. Inténtalo de nuevo.");

                    }
                    break;
                case "consultarDinero":
                    HttpSession session = request.getSession();
                    int i = (int) session.getAttribute("id");

                    try {
                        ConexionBD conexion = new ConexionBD();
                        double result = conexion.consultarSaldo(i);
                        System.out.println("Consultar " + result);
                        out.println(result);
                    } catch (ClassNotFoundException | SQLException ex) {
                        out.println("0.00");
                    }

                    break;
                case "agregarDinero":

                    session = request.getSession();
                    i = (int) session.getAttribute("id");

                    try {
                        ConexionBD conexion = new ConexionBD();
                        out.println(conexion.agregarFondos(i));
                    } catch (ClassNotFoundException | SQLException ex) {
                        out.println("0.00");
                    }

                    break;
                case "cerrarSesionUsuario":
                    session = request.getSession();
                    session.removeAttribute("correoUsuario");
                    session.removeAttribute("ID");
                    break;
                case "iniciarSesionUsuario":
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    try {
                        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
                            throw new IllegalArgumentException("Username y password son requeridos.");
                        }

                        ConexionBD conexionBD = new ConexionBD();
                        ResultSet resultSet = conexionBD.consultarUsuario(username, password);

                        // ...
                        if (resultSet.next()) {
                            // Almacena el correo del usuario en la sesión
                            session = request.getSession();
                            session.setAttribute("correoUsuario", resultSet.getString("Correo"));
                            session.setAttribute("id", resultSet.getInt("ID"));

                            // Obtener el valor de isAdmin
                            boolean isAdmin = resultSet.getBoolean("isAdmin");

                            System.out.println("Valor de isAdmin (boolean): " + isAdmin);

                            // Convertir a cadena para la comparación en JavaScript
                            String isAdminString = isAdmin ? "admin" : "user";

                            System.out.println("Valor de isAdmin (cadena): " + isAdminString);

                            if (isAdmin) {
                                System.out.println("Es un administrador, mostrando alerta");
                                response.getWriter().write(isAdminString);
                            } else {
                                System.out.println("Redirigiendo a UsuarioInfo.html");
                                response.getWriter().write(isAdminString);
                            }
                        } else {
                            System.out.println("Credenciales inválidas, redirigiendo a index.html");
                            response.getWriter().write("false");
                        }

                    } catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e.getMessage());
                        response.getWriter().write("false");
                    }
                    break;
                case "consultarInfoUsuario":
                    String correoUsuario = obtenerCorreoUsuarioDesdeSesion(request);

                    if (correoUsuario != null && !correoUsuario.isEmpty()) {
                        ResultSet resultSet = null;
                        try {
                            ConexionBD conexionBD = new ConexionBD();
                            resultSet = conexionBD.obtenerInfoUsuario(correoUsuario);

                            if (resultSet.next()) {
                                JsonObjectBuilder userInfoBuilder = Json.createObjectBuilder();
                                userInfoBuilder.add("Nombre", resultSet.getString("Nombre"));
                                userInfoBuilder.add("Correo", resultSet.getString("Correo"));
                                userInfoBuilder.add("Saldo", resultSet.getDouble("Saldo"));

                                JsonObject userInfo = userInfoBuilder.build();

                                response.setContentType("application/json");
                                response.getWriter().write(userInfo.toString());
                            } else {
                                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                                response.getWriter().write("Usuario no encontrado");
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            response.getWriter().write("Error al obtener la información del usuario");
                        }
                    } else {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Acceso no autorizado");
                    }
                    break;
            }

        }
    }
    
    private String obtenerCorreoUsuarioDesdeSesion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("correoUsuario");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
