package logiStore.test;

import Library.Clases.Producto;
import Library.Clases.Sector;
import org.junit.Test;

import static org.junit.Assert.*;

public class SectorTest {

    @Test
    public void testNuevoProducto() {
        Sector sectorTest = new Sector(1, "Electrónica");

        // Lista vacía al inicio
        assertEquals(0, sectorTest.listaProductos.size());

        // Se crea un producto, debería devolver true y lista con 1 elemento
        assertTrue(sectorTest.nuevoProducto("Laptop", 1, 0));
        assertEquals(1, sectorTest.listaProductos.size());

        // Intento crear otro con mismo código → debería devolver false
        assertFalse(sectorTest.nuevoProducto("Laptop 2", 1, 10));

        // Intento crear otro con código distinto → debería añadirse
        assertTrue(sectorTest.nuevoProducto("Mouse", 2, 5));
        assertEquals(2, sectorTest.listaProductos.size());
    }

    @Test
    public void testAgregarProducto() {
        Sector sectorTest = new Sector(1, "Electrónica");

        // Intento agregar stock a un producto inexistente
        assertFalse(sectorTest.agregarProducto(1, 20));

        // Se crea un producto con stock inicial 0
        assertTrue(sectorTest.nuevoProducto("Laptop", 1, 0));
        assertEquals(0, sectorTest.listaProductos.get(0).getStock());

        // Se agrega stock
        assertTrue(sectorTest.agregarProducto(1, 20));
        assertEquals(20, sectorTest.listaProductos.get(0).getStock());

        // Se agrega más stock
        assertTrue(sectorTest.agregarProducto(1, 5));
        assertEquals(25, sectorTest.listaProductos.get(0).getStock());
    }

    @Test
    public void testEliminarProducto() {
        Sector sectorTest = new Sector(1, "Electrónica");

        // Intentar eliminar producto inexistente
        assertFalse(sectorTest.eliminarProducto(1));

        // Se crea un producto
        assertTrue(sectorTest.nuevoProducto("Laptop", 1, 10));
        assertEquals(1, sectorTest.listaProductos.size());

        // Eliminar producto existente
        assertTrue(sectorTest.eliminarProducto(1));
        assertEquals(0, sectorTest.listaProductos.size());
    }

    @Test
    public void testGettersAndSetters() {
        Sector sectorTest = new Sector(1, "Electrónica");

        sectorTest.setId(5);
        sectorTest.setNombre("Ropa");

        assertEquals(5, sectorTest.getId());
        assertEquals("Ropa", sectorTest.getNombre());
    }

    @Test
    public void testListaProductosReference() {
        Sector sectorTest = new Sector(1, "Electrónica");

        Producto p = new Producto("Tablet", 99, 15, sectorTest);
        sectorTest.listaProductos.add(p);

        assertEquals(1, sectorTest.listaProductos.size());
        assertEquals("Tablet", sectorTest.listaProductos.get(0).getNombre());
        assertEquals(15, sectorTest.listaProductos.get(0).getStock());
    }
}
