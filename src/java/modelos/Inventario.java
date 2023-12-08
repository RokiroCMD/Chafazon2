
package modelos;

public class Inventario {
    int id;
    String nombre;
    String categoria;
    int existencias;
    String imagen;

    public Inventario(int id, String nombre, String categoria, int existencias, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.existencias = existencias;
        this.imagen = imagen;
    }

    public Inventario(String nombre, String categoria, int existencias, String imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.existencias = existencias;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
}
