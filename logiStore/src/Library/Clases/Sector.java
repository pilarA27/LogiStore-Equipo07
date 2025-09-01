package Library.Clases;

import java.util.ArrayList;
import java.util.List;

public class Sector {
    //Atributos de la clase Sector
    public int id;
    public String nombre;

    //Atributos para manejar productos
    public String nombreProd;
    public int codigoProd;
    public int stock;
    public String sector;

    //Lista de productos
    public List<Producto> listaProductos = new ArrayList<>();

    //Constructor de Sector
    public Sector(int id, String nombre){
        this.nombre = nombre;
        this.id = id;
    }

    //Se crea un nuevo producto
    public void nuevoProducto(String nombreProd, int codigoProd){
        for(Producto producto : listaProductos){
            if (producto.codigo == codigoProd){
                System.out.println("El producto ya existe en este sector");
                return;
            }
        }
        listaProductos.add(new Producto(nombreProd, codigoProd, 0, nombre));
        System.out.println("El producto " + nombreProd + " se agregó con exito.");
    }

    //Se agrega el stock deseado al producto de elección
    public void agregarProducto(int codigoProd, int stock){
        for(Producto producto : listaProductos){
            if(producto.codigo == codigoProd){
                producto.stock += stock;
                System.out.println("Se agregó " + stock + " de " + producto.nombre);
                return;
            }
        }
        System.out.println("No se encontró el producto solicitado.");
    }

    //Se resta el stock deseado al producto de elección
    public void eliminarProducto(int codigoProd, int stock){
        for(Producto producto : listaProductos){
            if(producto.codigo == codigoProd){
                if(producto.stock < stock){
                    System.out.println("No hay stock suficiente.");
                    return;
                }
                producto.stock -= stock;
                System.out.println("Se restó " + stock + " de " + producto.nombre);
                return;
            }
        }
        System.out.println("No se encontró el producto solicitado.");
    }
}
