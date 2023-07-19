package Sistema.Ordenes.repositorio;

import java.util.List;

import Sistema.Ordenes.dominio.Cliente;

public interface IClienteDAO {
    abstract void insertar(Cliente c);

    void actualizar(Cliente c);

    void eliminar(int codigo);

    Cliente buscar(int codigo);

    List<Cliente> consultarTodos();
}
