package Sistema.Ordenes.Pruebas;

import Sistema.Ordenes.Conn.Conn;
import Sistema.Ordenes.Dominio.Cliente;
import Sistema.Ordenes.Repositorio.ClienteDAO;
import Sistema.Ordenes.Repositorio.IClienteDAO;

public class TestClienteDAO {

    public static void main(String[] args) {
        Conn conn = new Conn();
        IClienteDAO daoCliente = new ClienteDAO(conn);

        Cliente c1 = new Cliente(4, "034234567273", "Pedro Sanchez");
        Cliente c2 = new Cliente(5, "475892784658", "Carmen Castillo");

        daoCliente.insertar(c1);
        daoCliente.insertar(c2);

        Cliente c3 = daoCliente.buscar(2);
        c3.setNombres("TRexAsitimbayBr");
        daoCliente.actualizar(c3);

        for (Cliente cliente : daoCliente.consultarTodos()) {
            System.out.println(cliente);
        }
    }
}
