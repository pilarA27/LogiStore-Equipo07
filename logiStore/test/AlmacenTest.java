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
        //Lista los productos de un sector
    }

    @Test
    void realizarPedidos(){
        //Realiza en órden los pedidos de la cola de pédidos de un sector
    }

    @Test
    void cargarEnHistorial(){
        //Carga los pedidos realizados en "historialDePedidos.txt"
    }

    @Test
    void revertirUltimoPedido(){
        //Lee el historial de pedidos realizados, y revierte el último cambio
    }
}
