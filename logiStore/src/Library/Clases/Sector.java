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
    public boolean nuevoProducto(String nombreProd, int codigoProd){
        for(Producto producto : listaProductos){
            if (producto.codigo == codigoProd){
                System.out.println("El producto ya existe en este sector");
                return false;
            }
        }
        listaProductos.add(new Producto(nombreProd, codigoProd, 0, nombre));
        System.out.println("El producto " + nombreProd + " se agregó con exito.");
        return true;
    }

    //Se agrega el stock deseado al producto de elección
    public boolean agregarProducto(int codigoProd, int stock){
        for(Producto producto : listaProductos){
            if(producto.codigo == codigoProd){
                producto.stock += stock;
                System.out.println("Se agregó " + stock + " de " + producto.nombre);
                return true;
            }
        }
        System.out.println("No se encontró el producto solicitado.");
        return false;
    }

    //Se resta el stock deseado al producto de elección
    public boolean eliminarProducto(int codigoProd, int stock){
        for(Producto producto : listaProductos){
            if(producto.codigo == codigoProd){
                if(producto.stock < stock){
                    System.out.println("No hay stock suficiente.");
                    return false;
                }
                producto.stock -= stock;
                System.out.println("Se restó " + stock + " de " + producto.nombre);
                return true;
            }
        }
        System.out.println("No se encontró el producto solicitado.");
        return false;
    }
}
