/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Articulo;
import modelos.ConexionBD;
import modelos.Inventario;
import org.json.JSONObject;

/**
 *
 * @author alexc
 */
@WebServlet(name = "ControladorArticulos", urlPatterns = {"/ControladorArticulos"})
public class ControladorArticulos extends HttpServlet {

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
            ConexionBD conexion = null;
            try {
                conexion = new ConexionBD();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControladorInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            switch (option) {
                case "agregar":
                    String nombre = request.getParameter("nombre");
                    String categoria = request.getParameter("categoria");
                    int existencias = 0;
                    try{
                        existencias = Integer.parseInt(request.getParameter("existencias"));
                    } catch(Exception e){
                        out.println(0);
                        return;
                    }
                    String imagen = request.getParameter("imagen");
                    int precio = 0;
                    try{
                        precio = Integer.parseInt(request.getParameter("precio"));
                    } catch(Exception e){
                        out.println(0);
                        return;
                    }
                    boolean enOferta = false;
                    if ("true".equals(request.getParameter("enOferta"))) {
                      enOferta = true;  
                    } 
                    out.println(conexion.agregarArticulo(nombre, categoria, existencias, imagen, precio, enOferta));
                    break;
                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("idEliminar"));
                    out.println(conexion.eliminarArticulo(idEliminar));
                    break;
                case "consultar":
                    List<Articulo> articulos = conexion.consultarArticulo();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    JSONObject json = new JSONObject();
                    for (Articulo art : articulos) {
                        Map<String, Object> mapa1 = new HashMap<>();
                        mapa1.put("nombre", art.getNombre());
                        mapa1.put("categoria", art.getCategoria());
                        mapa1.put("existencias", art.getExistencias());
                        mapa1.put("imagen", art.getImagen());
                        mapa1.put("precio", art.getPrecio());
                        mapa1.put("oferta", art.isEnOferta());
                        json.put(String.valueOf(art.getId()), mapa1);
                    }
                    out.println(json.toString());
                    break;
                case "modificar":
                    
                    break;
            }
            
        }
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
