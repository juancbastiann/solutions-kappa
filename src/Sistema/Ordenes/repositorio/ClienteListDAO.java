package Sistema.Ordenes.Repositorio;

import Sistema.Ordenes.Dominio.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteListDAO implements IClienteDAO {

    List<Cliente> repositorioClientes;

    public ClienteListDAO() {
        repositorioClientes = new ArrayList<>();

        repositorioClientes.add(new Cliente(1, "0704070000", "Juan Altamirano"));
        repositorioClientes.add(new Cliente(2, "0000002334", "Bryan Chango"));
        repositorioClientes.add(new Cliente(3, "0234293242", "Fernando Alvarado"));
    }

    @Override
    public void insertar(Cliente c) {
        repositorioClientes.add(c);
    }

    @Override
    public void actualizar(Cliente c) {
        Cliente cliente = buscar(c.getCodigo());

        if (cliente != null) {
            int indice = repositorioClientes.indexOf(cliente);

            repositorioClientes.set(indice, c);
        }
    }

    public Cliente buscar(int codigo) {
        for (Cliente cliente : repositorioClientes) {
            if (cliente.getCodigo() == codigo)
                return cliente;
        }
        return null;
    }

    @Override
    public void eliminar(int codigo) {
        Cliente cliente = buscar(codigo);
        if (cliente != null) {
            repositorioClientes.remove(cliente);
        }
    }

    @Override
    public List<Cliente> consultarTodos() {
        return repositorioClientes;
    }

}
