
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.ConexionBD;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    }
}
