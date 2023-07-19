package Sistema.Ordenes.Repositorio;

import java.util.ArrayList;
import java.util.List;

import Sistema.Ordenes.Dominio.Cliente;

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
        int index = -1;
        for (int i = 0; i < repositorioClientes.size(); i++) {
            Cliente cliente = repositorioClientes.get(i);
            if (cliente.getCodigo() == c.getCodigo()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            repositorioClientes.set(index, c);
        } else {
            throw new IllegalArgumentException("Cliente no encontrado en la base de datos");
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
        return repositorioClientes;
    }

}
