package Sistema.Ordenes.Negocio;

import java.util.List;
import Sistema.Ordenes.Dominio.Cliente;

public interface IMantenedorClienteFacade {
    void grabar(Cliente c);

    void eliminar(int codigo);

    Cliente obtener(int codigo);

    List<Cliente> traerTodos();
}
