package Sistema.Ordenes.Repositorio;

import Sistema.Ordenes.Dominio.Cliente;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteHashSetDAO implements IClienteDAO {

    Set<Cliente> repositorioClientes;

    public ClienteHashSetDAO() {
        repositorioClientes = new HashSet<>();

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
            repositorioClientes.remove(cliente);
            repositorioClientes.add(c);
        }
    }

    @Override
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
        return new ArrayList<>(repositorioClientes);
    }
}
