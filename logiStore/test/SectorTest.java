package logiStore.test;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import Library.Clases.Sector;

public class SectorTest{

    @Test
    public void nuevoProducto(){
        //Se agrega un producto a la lista productos de un sector y se verifica que se haya creado
        Sector sectorTest = new Sector(1, "Electrónica");

        //No hay nada aún agregado, por lo que la lista está vacía
        assertNotEquals(1, sectorTest.listaProductos.size());
        
        //Se creó un producto, por lo que la lista debería tener un elemento.
        assertTrue(sectorTest.nuevoProducto("Laptop", 1));
        assertEquals(1, sectorTest.listaProductos.size());

        //Intento crear un producto con el mismo id
        assertFalse(sectorTest.nuevoProducto("Laptop", 1));
    }

    @Test
    public void agregarProducto(){
        //Se crea un producto en un sector
        Sector sectorTest = new Sector(1, "Electrónica");
        
        //Intento agregar stock a un producto inexistente
        assertFalse(sectorTest.agregarProducto(1, 20));

        //El stock del prodcto recién creado debería ser 0 ya que aún no se agregó stock
        assertTrue(sectorTest.nuevoProducto("Laptop", 1));
        assertEquals(0, sectorTest.listaProductos.get(0).stock);

        //Se agrega 20 de stock al producto
        sectorTest.agregarProducto(1, 20);
        assertEquals(20, sectorTest.listaProductos.get(0).stock);

    }

    @Test
    public void eliminarProducto(){
        Sector sectorTest = new Sector(1, "Electrónica");
        
        //Se intenta eliminar stock de un producto inexistente
        assertFalse(sectorTest.eliminarProducto(1, 20));

        //Se crea un producto y se le intenta eliminar stock antes de agregarselo    
        sectorTest.nuevoProducto("Laptop", 1);
        assertFalse(sectorTest.eliminarProducto(1, 20));

        //Se agrega 20 de stock al producto y luego se elimina
        sectorTest.agregarProducto(1, 20);
        assertTrue(sectorTest.eliminarProducto(1, 15));
        assertEquals(5, sectorTest.listaProductos.get(0).stock);
    }
}
