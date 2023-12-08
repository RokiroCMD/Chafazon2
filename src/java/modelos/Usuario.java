
package modelos;

public class Usuario {
    
    int id;
    String nombre;
    String correo;
    String password;
    float saldo;
    boolean isAdmin;

    public Usuario(String nombre, String correo, String password) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public Usuario(int id, String nombre, String correo, String password, float saldo, boolean isAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.saldo = saldo;
        this.isAdmin = isAdmin;
    }

    public Usuario(String correo, String password, float saldo, boolean isAdmin) {
        this.correo = correo;
        this.password = password;
        this.saldo = saldo;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public float getSaldo() {
        return saldo;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
}
