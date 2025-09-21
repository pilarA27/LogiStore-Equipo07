package Library.Clases;

public class Pedido {
    private int id;
    private Sector sector;
    private Producto producto;
    private int stock;
    private String estado;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Sector getSector() {
        return sector;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("EL stock no puede ser un número negativo");
        }
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public Pedido(int id, Sector sector, Producto producto, int stock, String estado) {
        this.id = id;
        this.sector = sector;
        this.producto = producto;
        this.stock = stock;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
            "id=" + id +
            ", sector=" + (sector != null ? sector.getNombre() : "null") +
            ", producto=" + (producto != null ? producto.getNombre() : "null") +
            ", stock=" + stock +
            ", estado='" + estado + '\'' +
            '}';
    }
}