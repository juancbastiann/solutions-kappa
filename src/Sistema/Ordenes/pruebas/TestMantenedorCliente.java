package Sistema.Ordenes.Pruebas;

import java.util.List;
import java.util.Scanner;
import Sistema.Ordenes.Dominio.Cliente;
import Sistema.Ordenes.Negocio.IMantenedorClienteFacade;
import Sistema.Ordenes.Negocio.MantenedorCliente;
import Sistema.Ordenes.Repositorio.ClienteDAO;
import Sistema.Ordenes.Repositorio.IClienteDAO;
import Sistema.Ordenes.Conn.Conn;

public class TestMantenedorCliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conn conn = new Conn();
        IClienteDAO daoCliente = new ClienteDAO(conn);
        IMantenedorClienteFacade mantenedor = new MantenedorCliente(daoCliente);

        int opcion;
        do {
            System.out.println("-------- MENU --------");
            System.out.println("1. Ingresar cliente");
            System.out.println("2. Actualizar cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Mostrar todos los clientes");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Cliente nuevoCliente = ingresarCliente(scanner);
                    mantenedor.grabar(nuevoCliente);
                    System.out.println("Cliente ingresado con éxito.");
                    break;
                case 2:
                    int codigoActualizar = ingresarCodigoCliente(scanner);
                    Cliente clienteActualizar = mantenedor.obtener(codigoActualizar);
                    if (clienteActualizar != null) {
                        actualizarCliente(scanner, clienteActualizar);
                        mantenedor.grabar(clienteActualizar);
                        System.out.println("Cliente actualizado con éxito.");
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 3:
                    int codigoEliminar = ingresarCodigoCliente(scanner);
                    Cliente clienteEliminar = mantenedor.obtener(codigoEliminar);
                    if (clienteEliminar != null) {
                        mantenedor.eliminar(codigoEliminar);
                        System.out.println("Cliente eliminado con éxito.");
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 4:
                    List<Cliente> clientes = mantenedor.traerTodos();
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        System.out.println("Lista de clientes:");
                        for (Cliente cliente : clientes) {
                            System.out.println(cliente);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
            System.out.println("-----------------------");
        } while (opcion != 0);

        scanner.close();
    }

    private static Cliente ingresarCliente(Scanner scanner) {
        System.out.println("Ingrese los datos del cliente:");
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        System.out.print("Cédula: ");
        String cedula = scanner.next();
        scanner.nextLine();
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();

        return new Cliente(codigo, cedula, nombres);
    }

    private static int ingresarCodigoCliente(Scanner scanner) {
        System.out.print("Ingrese el código del cliente: ");
        return scanner.nextInt();
    }

    private static void actualizarCliente(Scanner scanner, Cliente cliente) {
        System.out.println("Actualice los datos del cliente (deje en blanco para mantener el valor actual):");
        System.out.print("Cédula [" + cliente.getCedula() + "]: ");
        String cedula = scanner.nextLine().trim();
        if (!cedula.isEmpty()) {
            cliente.setCedula(cedula);
        }
        System.out.print("Nombres [" + cliente.getNombres() + "]: ");
        String nombres = scanner.nextLine().trim();
        if (!nombres.isEmpty()) {
            cliente.setNombres(nombres);
        }
    }
}
