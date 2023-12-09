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
import modelos.Venta;
import org.json.JSONObject;

@WebServlet(name = "ControladorVentas", urlPatterns = {"/ControladorVentas"})
public class ControladorVentas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String option = request.getParameter("option");
            ConexionBD conexion = null;
            try {
                conexion = new ConexionBD();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControladorInventario.class.getName()).log(Level.SEVERE, null, ex);
            }

            switch (option) {
                case "agregar":
                    String nombreComprador = request.getParameter("nombreComprador");
                    String nombreProducto = request.getParameter("nombreProducto");
                    int monto = 0;
                    try{
                        monto = Integer.parseInt(request.getParameter("monto"));
                    } catch(Exception e){
                        out.println(0);
                        return;
                    }
                    String imagen = request.getParameter("imagen");
                    out.println(conexion.agregarMonto(nombreComprador, nombreProducto, monto, imagen));
                                        
                    break;
                case "consultar":
                    List<Venta> ventas = conexion.consultarVentas();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    JSONObject json = new JSONObject();
                    for (Venta venta : ventas) {
                        Map<String, Object> mapa1 = new HashMap<>();
                        mapa1.put("nombreComprador", venta.getNombreComprador());
                        mapa1.put("nombreProducto", venta.getNombreProducto());
                        mapa1.put("monto", venta.getMonto());
                        mapa1.put("imagen", venta.getImagenPrducto());
                        mapa1.put("fecha", venta.getFecha());
                        json.put(String.valueOf(venta.getId()), mapa1);
                    }
                    out.println(json.toString());
                    
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
