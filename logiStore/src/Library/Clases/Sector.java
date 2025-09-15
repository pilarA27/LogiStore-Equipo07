package Library.Clases;

import java.util.ArrayList;
import java.util.List;

/*
 1. Implementar un simulador de almacén logístico que permita:
a) Crear sectores en el almacén con un identificador único.
b) Agregar productos a un sector indicando nombre, código, cantidad, ubicación.
c) Buscar un producto en un sector.
d) Eliminar un producto en un sector.
e) Listar todos los productos de un sector.
f) Procesar pedidos agregando los productos solicitados a un flujo de pedidos.
g) Atender pedidos en orden, eliminándolos a medida que se completan.
h) Registrar pedidos procesados en un historial.
 */
public class Sector {
    public int id;
    public String nombre;


    public List<Producto> listaProductos = new ArrayList<>();


    public Sector(int id, String nombre){
        this.nombre = nombre;
        this.id = id;
    }

    //Se crea un nuevo producto
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

    //Se agrega el stock deseado al producto de elección
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

    //Se elima un producto del sector
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
