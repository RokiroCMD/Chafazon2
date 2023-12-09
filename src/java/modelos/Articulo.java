
package modelos;


public class Articulo {
    int id;
    String nombre;
    String categoria;
    int existencias;
    String imagen;
    int precio;
    boolean enOferta;

    public Articulo(int id, String nombre, String categoria, int existencias, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.existencias = existencias;
        this.imagen = imagen;
    }

    public Articulo(String nombre, String categoria, int existencias, String imagen, int precio, boolean enOferta) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.existencias = existencias;
        this.imagen = imagen;
        this.precio = precio;
        this.enOferta = enOferta;
    }

    
    public Articulo(int id, String nombre, String categoria, int existencias, String imagen, int precio, boolean enOferta) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.existencias = existencias;
        this.imagen = imagen;
        this.precio = precio;
        this.enOferta = enOferta;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isEnOferta() {
        return enOferta;
    }

    public void setEnOferta(boolean enOferta) {
        this.enOferta = enOferta;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    
     
}
