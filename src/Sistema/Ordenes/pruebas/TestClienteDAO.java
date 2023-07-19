package Sistema.Ordenes.pruebas;

import Sistema.Ordenes.dominio.Cliente;
import Sistema.Ordenes.repositorio.ClienteListDAO;
import Sistema.Ordenes.repositorio.IClienteDAO;

public class TestClienteDAO {

    public static void main(String[] args) {

        IClienteDAO daoCliente = new ClienteListDAO();

        Cliente c1 = new Cliente(4, "034234567273", "Pedro Sanchez");
        Cliente c2 = new Cliente(5, "475892784658", "Carmen Castillo");

        daoCliente.insertar(c1);
        daoCliente.insertar(c2);

        for (Cliente cliente : daoCliente.consultarTodos()) {
            System.out.println(cliente);
        }

        Cliente clienteExistente = daoCliente.buscar(1);
        if (clienteExistente != null) {
            clienteExistente.setCedula("0706070000");
            clienteExistente.setNombres("Juan Altamirano");
            daoCliente.actualizar(clienteExistente);
        } else {
            System.out.println("Cliente no encontrado");
        }

        Cliente clienteEncontrado = daoCliente.buscar(1);
        if (clienteEncontrado != null) {
            System.out.println(clienteEncontrado);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }
}
