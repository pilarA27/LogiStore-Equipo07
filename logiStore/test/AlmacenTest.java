package logiStore.test;

import Library.Clases.Almacen;
import Library.Clases.Pedido;
import Library.Clases.Producto;
import Library.Clases.Sector;
import static org.junit.Assert.*;

import org.junit.Test;

public class AlmacenTest {

    @Test
    void crearSector(){
        Almacen almacen = new Almacen();

        boolean creado = almacen.crearSector(1, "Deposito");
        assertTrue(creado);
        assertEquals(1, almacen.listaSectores.size());

        boolean creadoDuplicado = almacen.crearSector(1, "Deposito 2");
        assertFalse(creadoDuplicado);
        assertEquals(1, almacen.listaSectores.size());
    }

    @Test
    void eliminarSector(){
        Almacen almacen = new Almacen();
        almacen.crearSector(1, "Depósito)");

        boolean eliminado = almacen.eliminarSector(1);
        assertTrue(eliminado);
        assertEquals(0, almacen.listaSectores.size());

        assertFalse(almacen.eliminarSector(20));
    }

    @Test
    void verSectores(){
        Almacen almacen = new Almacen();

        almacen.verSectores();

        almacen.crearSector(1, "Depósito");
        almacen.crearSector(2, "Depósito 2");

        almacen.verSectores();
    }

    @Test
    void verProductos(){
        Almacen almacen = new Almacen();
        almacen.crearSector(1, "Depósito");
        Sector sector = almacen.listaSectores.get(0);
        sector.nuevoProducto("Martillo",20, 30 );
        
        almacen.verProductos(1);
        
        almacen.verProductos(30);
    }

    @Test
    void realizarPedidos(){
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
    void cargarEnHistorial(){
        
    }

    @Test
    void revertirUltimoPedido(){
        //Lee el historial de pedidos realizados, y revierte el último cambio
    }
}
