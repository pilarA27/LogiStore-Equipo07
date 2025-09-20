package logiStore.test;

import Library.Clases.Pedido;
import Library.Clases.Sector;
import Library.Clases.Producto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PedidoTest {

    private Pedido pedido;
    private Sector sector;
    private Producto producto;

    @Before
    public void setUp() {
        sector = new Sector(1, "Electrónica");
        producto = new Producto("Televisor", 10, 50, sector);
        pedido = new Pedido(100, sector, producto, 5, "Pendiente");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(100, pedido.getId());
        assertEquals(sector, pedido.getSector());
        assertEquals(producto, pedido.getProducto());
        assertEquals(5, pedido.getStock());
        assertEquals("Pendiente", pedido.getEstado());
    }

    @Test
    public void testSettersAndGetters() {
        Sector nuevoSector = new Sector(2, "Ropa");
        Producto nuevoProducto = new Producto("Camisa", 20, 30, nuevoSector);

        pedido.setId(200);
        pedido.setSector(nuevoSector);
        pedido.setProducto(nuevoProducto);
        pedido.setStock(15);
        pedido.setEstado("Completado");

        assertEquals(200, pedido.getId());
        assertEquals(nuevoSector, pedido.getSector());
        assertEquals(nuevoProducto, pedido.getProducto());
        assertEquals(15, pedido.getStock());
        assertEquals("Completado", pedido.getEstado());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetStockNegativeThrowsException() {
        pedido.setStock(-5);
    }

    @Test
    public void testToStringWithValues() {
        String result = pedido.toString();
        assertTrue(result.contains("id=100"));
        assertTrue(result.contains("sector=Electrónica"));
        assertTrue(result.contains("producto=Televisor"));
        assertTrue(result.contains("stock=5"));
        assertTrue(result.contains("estado='Pendiente'"));
    }

    @Test
    public void testToStringWithNulls() {
        Pedido pedidoNulo = new Pedido(300, null, null, 0, "Cancelado");
        String result = pedidoNulo.toString();
        assertTrue(result.contains("sector=null"));
        assertTrue(result.contains("producto=null"));
        assertTrue(result.contains("estado='Cancelado'"));
    }
}
