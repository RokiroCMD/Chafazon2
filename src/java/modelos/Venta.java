
package modelos;

import java.util.Date;


public class Venta {
    int id;
    String nombreComprador;
    String nombreProducto;
    String imagenPrducto;
    int monto;
    Date fecha;

    public Venta(int id, String nombreComprador, String nombreProducto, String imagenPrducto, int monto, Date fecha) {
        this.id = id;
        this.nombreComprador = nombreComprador;
        this.nombreProducto = nombreProducto;
        this.imagenPrducto = imagenPrducto;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Venta(String nombreComprador, String nombreProducto, String imagenPrducto, int monto, Date fecha) {
        this.nombreComprador = nombreComprador;
        this.nombreProducto = nombreProducto;
        this.imagenPrducto = imagenPrducto;
        this.monto = monto;
        this.fecha = fecha;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagenPrducto() {
        return imagenPrducto;
    }

    public void setImagenPrducto(String imagenPrducto) {
        this.imagenPrducto = imagenPrducto;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
