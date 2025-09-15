package Library.Clases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Escenario
En un mundo donde el comercio y la distribución de productos se realizan a gran
escala, la eficiencia en la gestión de almacenes se ha vuelto un factor crítico para el
éxito de cualquier empresa. La UCU ha decidido desarrollar LogiStore, un simulador
de almacén logístico pensado para entrenar a futuros profesionales en la administración
de inventarios, pedidos y sectores de almacenamiento. El sistema permitirá coordinar
productos, sectores, unidades y pedidos, asegurando que la operación del almacén sea ágil
y confiable.
En LogiStore, el almacén se organiza en sectores, que representan áreas físicas o lógicas
donde se guardan productos. Cada producto tiene atributos como nombre, código, cantidad disponible y ubicación en el sector correspondiente. Los productos deben modelarse
de tal manera que puedan realizarse operaciones de inserción, búsqueda, eliminación y
consultas rápidas, garantizando que no haya duplicados y que la información se mantenga
actualizada.
Cuando se procesa un pedido, los productos solicitados se agregan a un flujo de procesamiento, que sigue un orden secuencial basado en la llegada de los pedidos. Esta cola
asegura que los pedidos se atiendan de manera justa y organizada. Mientras tanto, se mantiene un historial de pedidos, que registra los pedidos ya procesados y permite consultar
o revertir operaciones recientes en caso de errores.
El correcto funcionamiento de LogiStore depende de la administración eficiente de los
sectores, las listas de productos, los pedidos por realizar y el historial, garantizando que
las operaciones de agregar, quitar, buscar y consultar se realicen de manera coherente y
sin pérdida de información.

Objetivo del proyecto
Desarrollar un simulador por consola del almacén LogiStore que permita gestionar sectores y productos, procesar pedidos y mantener un historial de operaciones, empleando
exclusivamente estructuras lineales (listas, pilas y colas) y las operaciones básicas asociadas vistas en clase. El sistema deberá permitir agregar, buscar, eliminar y listar productos
en cada sector, administrar los pedidos y registrar el historial de pedidos procesador, manteniendo siempre la integridad y coherencia de los datos.
 */
public class Almacen {
    //Lista de sectores
    public Queue<Pedido> colaPedidos = new LinkedList<>();

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

    public void eliminarSector(int id){
        //Elimina un sector
        for(Sector sector : listaSectores) {
            if (sector.)
        }
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
