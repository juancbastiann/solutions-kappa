package Sistema.Ordenes.Negocio;

import java.util.List;
import Sistema.Ordenes.Dominio.Cliente;
import Sistema.Ordenes.Repositorio.IClienteDAO;

public class MantenedorCliente implements IMantenedorClienteFacade {

    IClienteDAO daoCliente;

    public MantenedorCliente(IClienteDAO daoCliente) {
        this.daoCliente = daoCliente;
    }

    @Override
    public void grabar(Cliente c) {
        Cliente cliente = daoCliente.buscar(c.getCodigo());

        if (cliente != null)
            daoCliente.actualizar(c);
        else
            daoCliente.insertar(c);
    }

    @Override
    public void eliminar(int codigo) {
        daoCliente.eliminar(codigo);
    }

    @Override
    public Cliente obtener(int codigo) {
        return daoCliente.buscar(codigo);
    }

    @Override
    public List<Cliente> traerTodos() {
        return daoCliente.consultarTodos();
    }
}
