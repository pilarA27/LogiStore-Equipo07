package Library.Clases;


public class Producto {
    public String nombre;
    public int codigo;
    public int stock;
    public Sector sector;

    public Producto(String nombre, int codigo, int stock, Sector sector){
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

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Sector getSector() {
        return sector;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Producto producto = (Producto) obj;
        return codigo == producto.codigo;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(codigo);
    }
}
