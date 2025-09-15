package Library.Clases;
import Library.Clases.Sector;
public class Producto {
    public String nombre;
    public int codigo;
    public int stock;
    public String sector;

    public Producto(String nombre, int codigo, int stock, String sector){
        this.nombre = nombre;
        this.codigo = codigo;
        this.stock = stock;
        this.sector = sector;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }
}
