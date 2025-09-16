package Library.Clases;

import Library.Clases.Sector;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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

    public Queue<Pedido> colaPedidos = new LinkedList<>();
    public List<Sector> listaSectores = new ArrayList<>();
    public Stack<Pedido> historialPedidosPrograma = new Stack<>();

    //Lista de pedidos pendientes

    //Lista de pedidos realizados

    
    static Path pedidosSolicitados = Path.of("logiStore\\src\\Library\\Files\\pedidos.txt");
    static Path historialPedidos = Path.of("logiStore\\src\\Library\\Files\\historialDePedidos.txt");


    //ADMINISTRACIÓN DE SECTORES
    
    public boolean crearSector(int idSector, String nombreSector){
        for(Sector sector : listaSectores){
            if(sector.getId() == idSector){
                System.out.println("El sector con el id "+ idSector + " ya existe.");
                return false;
            }
        }
        listaSectores.add(new Sector(idSector, nombreSector));
        System.out.println("El producto " + nombreSector + " se agregó con exito.");
        return true;
    }

    public boolean eliminarSector(int idSector){
        for(Sector sector : listaSectores) {
            if (sector.getId() == idSector) {
                listaSectores.remove(sector);
                System.out.println("El sector se elimino correctamente.");
                return true;
            }
        }
        System.out.println("No se encontró un sector con el id: " + idSector);
        return false;
    }

    public void verSectores(){
        if (listaSectores.size() <= 0 ) {
            System.out.println("La lista de sectores esta vacía");
            return;
        } 
        System.out.println("Lista de sectores: ");
        for (Sector sector : listaSectores) {
            System.out.println("Sector: " + sector.getNombre() + " ID: " + sector.getId());
        }    
    }

    public void verProductos(int idSector){
        for (Sector sector : listaSectores) {
            if (sector.getId() == idSector) {
                sector.listarProducto();
                return;
            }
        }
        System.out.println("No se encontró un sector con el id: " + idSector);
    }

    public boolean buscarProducto(int idSector, int idProducto){
        for (Sector sector : listaSectores) {
            if (sector.getId() == idSector) {
                for (Producto producto : sector.getListaProductos()) {
                    if (producto.getCodigo() == idProducto) {
                        System.out.println("Se encontro el producto buscado en el sector: " + sector.getNombre());
                        System.out.println("Nombre del producto: " + producto.getNombre() + " ID: " + producto.getCodigo() + " Stock: " + producto.getStock());
                        return true;
                    }
                }
                System.out.println("No se encontro un producto con el ID: " + idProducto);
                return false;
            }
        }
        System.out.println("No se encontro un sector con el ID: " + idSector);
        return false;
    }

    //ADMINISTRACIÓN DE PEDIDOS

    public void realizarPedidos(){
        while (!colaPedidos.isEmpty()) {
            Pedido pedido = colaPedidos.poll();
            Producto producto = pedido.getProducto();

            if (producto.getStock() >= pedido.getStock()) {
                producto.setStock(producto.getStock() - pedido.getStock());
                pedido.setEstado("Procesando");
                System.out.println("Pedido procesado: " + pedido);

                historialPedidosPrograma.push(pedido);
                String registro = producto.getCodigo() + " | " + pedido.getStock() + " | " + producto.getSector().getNombre();

                try {
                    Files.writeString(historialPedidos, registro + System.lineSeparator());
                } catch (IOException e) {
                    System.err.println("Error al intentar guarrlo en el hostiral: " + e.getMessage());
                }
            } else {
                pedido.setEstado("Rechazado - stock insuficiente");
                System.out.println("No hay stock suficiente para el pedido: " + pedido);
            }
        }
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
                    if(sector.getId() == idSector){
                        sectorEncontrado = sector;
                        for(Producto producto : sector.listaProductos){
                            if(producto.getCodigo() == idProducto){
                                productoEncontrado = producto;
                                System.out.println("Sector: " + sector.getNombre() + " | Producto: " + producto.getNombre() + " | STOCK REQUERIDO: " + stockRequerido);
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
