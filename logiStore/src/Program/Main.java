package Program;
import Library.Clases.Almacen;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Almacen supermercado = new Almacen();
        Scanner in = new Scanner(System.in);
        // Crear sectores
        supermercado.crearSector(1, "Electrodomésticos");
        supermercado.crearSector(2, "Alimentos");

        // Agregar productos a Electrodomésticos
        supermercado.listaSectores.get(0).nuevoProducto("Heladera", 1, 0);
        supermercado.listaSectores.get(0).nuevoProducto("Microondas", 2, 0);
        supermercado.listaSectores.get(0).agregarProducto(1, 5); // 5 heladeras
        supermercado.listaSectores.get(0).agregarProducto(2, 10); // 10 microondas

        // Agregar productos a Alimentos
        supermercado.listaSectores.get(1).nuevoProducto("Arroz", 5, 10);
        supermercado.listaSectores.get(1).nuevoProducto("Fideos", 6, 4);
        supermercado.listaSectores.get(1).agregarProducto(5, 20); // 20 arroz
        supermercado.listaSectores.get(1).agregarProducto(6, 30); // 30 fideos

        int opcion;
        do{
            System.out.println("Menu de opciones LogiStore");
            System.out.println("1. Crear Sectores");
            System.out.println("2. Listar Sectores");
            System.out.println("3. Crear Productos en un sector");
            System.out.println("4. Listar Productos de un Sector");
            System.out.println("5. Procesar los pedidos del archivo");
            System.out.println("6. Ver Historial de Pedidos");
            System.out.println("7. Salir");


            while(!in.hasNextInt()){
                System.out.println("Por favor ingrese un número válido.");
                in.nextLine();
            }
            opcion = in.nextInt();
            in.nextLine();
            try{
                switch (opcion) {
                    case 1:
                        // Crear Sectores
                        System.out.println("Ingrese el ID del nuevo sector:");
                        int idSector = in.nextInt();
                        in.nextLine();  // Limpiar el buffer
                        System.out.println("Ingrese el nombre del nuevo sector:");
                        String nombreSector = in.nextLine();
                        supermercado.crearSector(idSector, nombreSector);
                        System.out.println("\n");
                        break;
                    case 2:
                        // Listar Sectores
                        supermercado.verSectores();
                        System.out.println("\n");
                        break;
                    case 3:
                        // Crear nuevo producto en un sector
                        System.out.println("Ingrese el ID del sector donde desea crear el producto:");
                        int idSectorNuevoProducto = in.nextInt();
                        in.nextLine();
                        System.out.println("Ingrese el nombre del nuevo producto:");
                        String nombreProducto = in.nextLine();
                        System.out.println("Ingrese el stock inicial del producto:");
                        int stockInicial = in.nextInt();
                        in.nextLine();
                        
                        
                        boolean creado = supermercado.nuevoProducto(idSectorNuevoProducto, nombreProducto, stockInicial);
                        if (!creado) {
                            System.out.println("No se pudo crear el producto. Verifique que el sector exista.");
                        }
                        System.out.println("\n");
                        break;

                    case 4:
                        //Listar Productos de un Sector
                        System.out.println("Ingrese el ID del sector donde desea listar los productos:");
                        int idSectorListar = in.nextInt();
                        in.nextLine();
                        supermercado.verProductos(idSectorListar);
                        System.out.println("\n");
                        break;
                        
                    case 5:
                        // Procesar los pedidos del archivo pedidos.txt
                    
                        supermercado.leerPedidos();
                        System.out.println("\n");
                        break;
                    case 6:
                        // Ver Historial de Pedidos
                        supermercado.leerHistorial();
                        System.out.println("\n");
                        break;
                    
                    default:
                        System.out.println("Opción no válida. Por favor seleccione una opción del menú.");
                        break;
                    case 7:
                        System.out.println("Gracias por utilizar LogiStore.");
                }
            }catch (Exception e){
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
          } while (opcion != 7);

        in.close();
    }
}