package logiStore.test;

import Library.Clases.Almacen;
import Library.Clases.Pedido;
import Library.Clases.Producto;
import Library.Clases.Sector;
import static org.junit.Assert.*;

import org.junit.Test;

public class AlmacenTest {

    @Test
    public void crearSector(){
        Almacen almacen = new Almacen();

        boolean creado = almacen.crearSector(1, "Depósito");
        assertTrue(creado);
        assertEquals(1, almacen.listaSectores.size());

        boolean creadoDuplicado = almacen.crearSector(1, "Depósito 2");
        assertFalse(creadoDuplicado);
        assertEquals(1, almacen.listaSectores.size());
    }

    @Test
    public void eliminarSector(){
        Almacen almacen = new Almacen();
        almacen.crearSector(1, "Depósito");

        boolean eliminado = almacen.eliminarSector(1);
        assertTrue(eliminado);
        assertEquals(0, almacen.listaSectores.size());

        assertFalse(almacen.eliminarSector(20));
    }

    @Test
    public void verSectores(){
        Almacen almacen = new Almacen();

        almacen.verSectores();

        almacen.crearSector(1, "Depósito");
        almacen.crearSector(2, "Depósito 2");

        almacen.verSectores();
    }

    @Test
    public void verProductos(){
        Almacen almacen = new Almacen();
        almacen.crearSector(1, "Depósito");
        Sector sector = almacen.listaSectores.get(0);
        sector.nuevoProducto("Martillo",20, 30 );
        
        almacen.verProductos(1);
        
        almacen.verProductos(30);
    }

    @Test
    public void realizarPedidos(){
        Almacen almacen = new Almacen();
        Sector sector = new Sector(1, "Depósito");
        Producto producto = new Producto("Clavo", 30, 100, sector); 
        sector.listaProductos.add(producto);
        almacen.listaSectores.add(sector);

        Pedido pedido = new Pedido(1, sector, producto, 30, "Nuevo");
        almacen.colaPedidos.add(pedido);

        Pedido pedido2 = new Pedido(2, sector, producto, 80, "Nuevo");
        almacen.colaPedidos.add(pedido2);

        almacen.realizarPedidos();

        assertTrue(almacen.colaPedidos.isEmpty());
        assertFalse(almacen.historialPedidosPrograma.isEmpty());
        assertEquals(70, producto.getStock());
    }

    @Test
    public void cargarEnHistorial(){
        Almacen almacen = new Almacen();
        Sector sector = new Sector(1, "Depósito");
        Producto producto = new Producto("Clavo",30,100,sector);
        
        Pedido pedido = new Pedido(1, sector, producto, 5, "Procesando");
        Pedido pedido2 = new Pedido(2, sector, producto, 7, "Procesando");
        almacen.historialPedidosPrograma.clear();
        almacen.historialPedidosPrograma.push(pedido);
        almacen.historialPedidosPrograma.push(pedido2);

        java.nio.file.Path file = java.nio.file.Path.of("logiStore\\src\\Library\\Files\\historialDePedidos.txt");
        try{
            java.nio.file.Files.createDirectories(file.getParent());
            java.nio.file.Files.deleteIfExists(file);
            java.nio.file.Files.createFile(file);
        }catch (java.io.IOException e){
            fail("No se pudo preparar el archivo de historial de Pedidos: " + e.getMessage());
        }
        almacen.cargarEnHistorial();

        try{
            java.util.List<String> lineas = java.nio.file.Files.readAllLines(file);
            assertEquals(2, lineas.size());
            assertTrue(lineas.contains("30 | 5 | 1"));
            assertTrue(lineas.contains("30 | 7 | 1"));
        }catch(java.io.IOException e){
            fail("No se pudo leer el archivo de historial de Pedidos: "+ e.getMessage());
        }
    }

    @Test
    public void revertirUltimoPedido(){
        Almacen almacen = new Almacen();
        Sector sector = new Sector(1,"Depósito");
        Producto producto = new Producto("Clavo", 30, 100, sector);
        
        Pedido pedido = new Pedido(1, sector, producto, 5, "Procesando");
        Pedido pedido2 = new Pedido(2, sector, producto, 7, "Procesando");
        almacen.historialPedidosPrograma.clear();
        almacen.historialPedidosPrograma.push(pedido);
        almacen.historialPedidosPrograma.push(pedido2);

        java.nio.file.Path file = java.nio.file.Path.of("logiStore\\src\\Library\\Files\\historialDePedidos.txt");
        try{
            java.nio.file.Files.createDirectories(file.getParent());
            java.util.List<String> preset = new java.util.ArrayList<>();
            preset.add("30 | 5 | 1");
            preset.add("30 | 7 | 1");
            java.nio.file.Files.write(file, preset);
        }catch (java.io.IOException e){
            fail("No se pudo preparar el archivo de historial de Pedidos para revertir: " + e.getMessage());
        }
        almacen.revertirUltimoPedido();

        assertEquals(107, producto.getStock());
        assertEquals(1, almacen.historialPedidosPrograma.size());
        assertEquals(pedido, almacen.historialPedidosPrograma.peek());

        try{
            java.util.List<String> lineas = java.nio.file.Files.readAllLines(file);
            assertEquals(1, lineas.size());
            assertEquals("30 | 5 | 1", lineas.get(0));
        }catch(java.io.IOException e){
            fail("No se pudo leer el archivo del historial de Pedidos tras revertir: " + e.getMessage());
        }
    }
}
