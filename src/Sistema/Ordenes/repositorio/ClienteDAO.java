package Sistema.Ordenes.Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Sistema.Ordenes.Conn.Conn;
import Sistema.Ordenes.Dominio.Cliente;

public class ClienteDAO implements IClienteDAO {

    private Connection conn;

    public ClienteDAO(Conn conn) {
        this.conn = conn.getConnection();
    }

    @Override
    public void insertar(Cliente c) {
        String sql = "INSERT INTO clientes (codigo, cedula, nombres) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, c.getCodigo());
            statement.setString(2, c.getCedula());
            statement.setString(3, c.getNombres());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Cliente c) {
        String sql = "UPDATE clientes SET cedula = ?, nombres = ? WHERE codigo = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, c.getCedula());
            statement.setString(2, c.getNombres());
            statement.setInt(3, c.getCodigo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int codigo) {
        String sql = "DELETE FROM clientes WHERE codigo = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscar(int codigo) {
        String sql = "SELECT * FROM clientes WHERE codigo = ?";
        Cliente cliente = null;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String cedula = resultSet.getString("cedula");
                String nombres = resultSet.getString("nombres");
                cliente = new Cliente(codigo, cedula, nombres);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public List<Cliente> consultarTodos() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String cedula = resultSet.getString("cedula");
                String nombres = resultSet.getString("nombres");
                Cliente cliente = new Cliente(codigo, cedula, nombres);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}
