package Library.Clases;

import java.util.ArrayList;
import java.util.List;

public class Sector {
    private int id;
    private String nombre;

    public List<Producto> listaProductos = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Sector(int id, String nombre){
        this.nombre = nombre;
        this.id = id;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
    
    public boolean nuevoProducto(String nombreProd, int codigoProd, int stockInicial){
        for(Producto producto : listaProductos){
            if (producto.getCodigo() == codigoProd){
                System.out.println("El producto ya existe en este sector");
                return false;
            }
        }
        listaProductos.add(new Producto(nombreProd, codigoProd, stockInicial, this));
        System.out.println("El producto " + nombreProd + " se agregó con exito.");
        return true;
    }

    public boolean agregarProducto(int codigoProd, int stock){
        for(Producto producto : listaProductos){
            if(producto.getCodigo() == codigoProd){
                producto.setStock(producto.getStock() + stock);
                System.out.println("Se agregó " + stock + " de " + producto.getNombre());
                return true;
            }
        }
        System.out.println("No se encontró el producto solicitado.");
        return false;
    }

    public boolean eliminarProducto(int codigoProd){
        for(Producto producto : listaProductos){
            if(producto.getCodigo() == codigoProd){
                listaProductos.remove(producto);
                System.out.println("Se elimino el producto: " + producto.getNombre());
                return true;
            }
        }
        System.out.println("No se encontró el producto solicitado.");
        return false;
    }

    public void listarProducto() {
        System.out.println("Lista de productos:");
        for (Producto producto : listaProductos) {
            System.out.println("Producto: " + producto.getNombre() + " Stock: " + producto.getStock() + " Sector: " + producto.getSector());
        }
    }
}
