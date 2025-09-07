package Program;

import Library.Clases.Almacen;

public class Main {
    public static void main(String[] args) {
        Almacen supermercado = new Almacen();

        // Crear sectores
        supermercado.crearSector(1, "Electrodomésticos");
        supermercado.crearSector(2, "Alimentos");

        // Agregar productos a Electrodomésticos
        supermercado.listaSectores.get(0).nuevoProducto("Heladera", 1);
        supermercado.listaSectores.get(0).nuevoProducto("Microondas", 2);
        supermercado.listaSectores.get(0).agregarProducto(1, 5); // 5 heladeras
        supermercado.listaSectores.get(0).agregarProducto(2, 10); // 10 microondas

        // Agregar productos a Alimentos
        supermercado.listaSectores.get(1).nuevoProducto("Arroz", 5);
        supermercado.listaSectores.get(1).nuevoProducto("Fideos", 6);
        supermercado.listaSectores.get(1).agregarProducto(5, 20); // 20 arroz
        supermercado.listaSectores.get(1).agregarProducto(6, 30); // 30 fideos

        // Probar leerPedidos (va a leer del archivo pedidos.txt)
        supermercado.leerPedidos();
    }
}

