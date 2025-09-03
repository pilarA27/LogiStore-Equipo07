package logiStore.test;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import Library.Clases.Sector;

public class SectorTest{

    @Test
    void nuevoProducto(){
        Sector sectorTest = new Sector(1, "Congelados");
        sectorTest.nuevoProducto("Helado", 1);
        assertEquals(1, sectorTest.listaProductos.size());
    }

    @Test
    void agregarProducto(){

    }

    @Test
    void eliminarProducto(){
    
    }
}
