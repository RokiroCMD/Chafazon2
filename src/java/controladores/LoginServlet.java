import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.ConexionBD;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                HttpSession session = request.getSession();
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
    }
}
