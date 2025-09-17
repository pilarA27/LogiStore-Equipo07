package Library.Clases;

import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class Almacen {

    public Queue<Pedido> colaPedidos = new LinkedList<>();
    public List<Sector> listaSectores = new ArrayList<>();
    public Stack<Pedido> historialPedidosPrograma = new Stack<>();

    
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
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(historialPedidos.toFile(), true))) {
            for (Pedido pedido : historialPedidosPrograma) {
                Producto producto = pedido.getProducto();
                String registro = producto.getCodigo() + " | " + pedido.getStock() + " | " + producto.getSector().getNombre();
                writer.write(registro);
                writer.newLine();
            }
            System.out.println("Historial guardado correctamente.");
        } catch (java.io.IOException e) {
            System.err.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    public void leerHistorial(){
        try {
            List<String> lineas = Files.readAllLines(historialPedidos);
            if (lineas.isEmpty()) {
                System.out.println("El historial esta vacío");
                return;
            }
            System.out.println("Historial de pedidos: ");
            for (String linea : lineas) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el historial");
        }
    }

    public void revertirUltimoPedido(){
        try {
            List<String> lineas = Files.readAllLines(historialPedidos);
            if (lineas.isEmpty()) {
                System.out.println("El historial esta vacío");
                return;
            }
            Pedido ultimoPedido = historialPedidosPrograma.peek();
            Producto producto = ultimoPedido.getProducto();
            producto.setStock(producto.getStock() + ultimoPedido.getStock());
            historialPedidosPrograma.pop();
            lineas.remove(lineas.size() - 1);
            Files.write(historialPedidos, lineas);
            System.out.println("Se revirtio el último pedido con exito!");
        } catch (Exception e) {
            System.err.println("Error al leer el historial");
        }
    }

}
