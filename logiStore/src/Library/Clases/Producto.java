package Library.Clases;

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
}
