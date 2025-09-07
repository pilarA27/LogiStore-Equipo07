package Library.Clases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Almacen {
    //Lista de sectores
    public List<Sector> listaSectores = new ArrayList<>();

    //Lista de pedidos pendientes

    //Lista de pedidos realizados

    //Defino la ubicación de mis archivos .txt
    static Path pedidosSolicitados = Path.of("logiStore\\src\\Library\\Files\\pedidos.txt");
    static Path historialPedidos = Path.of("logiStore\\src\\Library\\Files\\pedidos.txt");


    //ADMINISTRACIÓN DE SECTORES

    //Se crea un sector con un identificador único
    public boolean crearSector(int idSector, String nombreSector){
        for(Sector sector : listaSectores){
            if(idSector == sector.id){
                System.out.println("El sector con el id "+ idSector + " ya existe.");
                return false;
            }
        }
        listaSectores.add(new Sector(idSector, nombreSector));
        System.out.println("El producto " + nombreSector + " se agregó con exito.");
        return true;
    }

    public void eliminarSector(){
        //Elimina un sector
    }

    public void verSectores(){
        //Lista todos los sectores
    }

    public void verProductos(){
        //Lista los productos de un sector
    }

    public void buscarProducto(){
        //Busca un producto solicitado en los sectores
    }

    //ADMINISTRACIÓN DE PEDIDOS

    public void realizarPedidos(){
        //Realiza en órden los pedidos de la cola de pédidos de un sector
    }

    //Lee el archivo "pedidos.txt" y lo imprime en consola de forma legible.
    public void  leerPedidos(){
        try{
            List<String> lineas = Files.readAllLines(pedidosSolicitados);

            for (String linea : lineas) {
                String[] datos = linea.split(",");

                if(datos.length != 3){
                    System.out.println("# Pedido con formato incorrecto. #");
                    continue;
                }

                int idSector = Integer.parseInt(datos[0].trim());
                int idProducto = Integer.parseInt(datos[1].trim());
                int stockRequerido = Integer.parseInt(datos[2].trim());

                Sector sectorEncontrado = null;
                Producto productoEncontrado = null;

                for(Sector sector : listaSectores){
                    if(idSector == sector.id){
                        sectorEncontrado = sector;
                        for(Producto producto : sector.listaProductos){
                            if(idProducto == producto.codigo){
                                productoEncontrado = producto;
                                System.out.println("SECTOR: " + sector.nombre + " | PRODUCTO: " + producto.nombre + " | STOCK REQUERIDO: " + stockRequerido);
                            }
                        }
                    }
                }

                if(sectorEncontrado == null){
                    System.out.println("# No se encontró el sector #");
                    continue;
                }

                if(productoEncontrado == null){
                    System.out.println("# No se encontró el producto #");
                }
            }
        }catch(IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
    }

    public void cargarEnHistorial(){
        //Carga los pedidos realizados en "historialDePedidos.txt"
    }

    public void leerHistorial(){
        //Lee los pedidos realizados de "historialDePedidos.txt"
    }

    public void revertirUltimoPedido(){
        //Lee el historial de pedidos realizados, y revierte el último cambio
    }

}
