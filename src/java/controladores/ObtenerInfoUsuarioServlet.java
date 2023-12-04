import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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

@WebServlet("/ObtenerInfoUsuarioServlet")
public class ObtenerInfoUsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
    }


    private String obtenerCorreoUsuarioDesdeSesion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("correoUsuario");
    }
}

