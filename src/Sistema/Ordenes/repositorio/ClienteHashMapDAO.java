package Sistema.Ordenes.Repositorio;

import Sistema.Ordenes.Dominio.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteHashMapDAO implements IClienteDAO {

    Map<Integer, Cliente> repositorioClientes;

    public ClienteHashMapDAO() {
        repositorioClientes = new HashMap<>();

        repositorioClientes.put(1, new Cliente(1, "0704070000", "Juan Altamirano"));
        repositorioClientes.put(2, new Cliente(2, "0000002334", "Bryan Chango"));
        repositorioClientes.put(3, new Cliente(3, "0234293242", "Fernando Alvarado"));
    }

    @Override
    public void insertar(Cliente c) {
        repositorioClientes.put(c.getCodigo(), c);
    }

    @Override
    public void actualizar(Cliente c) {
        Cliente cliente = buscar(c.getCodigo());

        if (cliente != null) {
            repositorioClientes.put(c.getCodigo(), c);
        }
    }

    @Override
    public Cliente buscar(int codigo) {
        return repositorioClientes.get(codigo);
    }

    @Override
    public void eliminar(int codigo) {
        repositorioClientes.remove(codigo);
    }

    @Override
    public List<Cliente> consultarTodos() {
        return new ArrayList<>(repositorioClientes.values());
    }
}