package Sistema.Ordenes.Vista;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import Sistema.Ordenes.Dominio.Cliente;
import Sistema.Ordenes.Negocio.IMantenedorClienteFacade;
import Sistema.Ordenes.Negocio.MantenedorCliente;
import Sistema.Ordenes.Repositorio.ClienteListDAO;
import Sistema.Ordenes.Repositorio.IClienteDAO;

public class MntClienteControlador extends Application implements Initializable {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage screen) throws Exception {
        Parent raiz = FXMLLoader.load(getClass().getResource("frmMntcliente.fxml"));

        Scene window = new Scene(raiz);
        screen.setScene(window);
        screen.setTitle("Mantenedor");
        screen.show();
    }

    @FXML
    private Button btncancelar;

    @FXML
    private Button btnconsultar;

    @FXML
    private Button btneliminar;

    @FXML
    private Button btngrabar;

    @FXML
    private TextField txtcedula;

    @FXML
    private TextField txtcodigo;

    @FXML
    private TextField txtnombres;

    @FXML
    private ListView<Cliente> listClientes;
    private ObservableList<Cliente> observableClientes = FXCollections.observableArrayList();

    private IClienteDAO daoCliente = new ClienteListDAO();
    private IMantenedorClienteFacade mantenedor = new MantenedorCliente(daoCliente);

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        observableClientes.addAll(mantenedor.traerTodos());
        listClientes.setItems(observableClientes);
    }

    @FXML
    public void doGrabarCliente() {
        Cliente c = new Cliente();
        c.setCodigo(Integer.parseInt(txtcodigo.getText()));
        c.setCedula(txtcedula.getText());
        c.setNombres(txtnombres.getText());

        mantenedor.grabar(c);
        observableClientes.add(c);
    }

    @FXML
    public void doEliminarCliente() {
        int codigo = Integer.parseInt(txtcodigo.getText());

        mantenedor.eliminar(codigo);
        observableClientes.clear();
        observableClientes.addAll(mantenedor.traerTodos());
    }

    @FXML
    public void doCancelar() {
        txtcodigo.clear();
        txtcedula.clear();
        txtnombres.clear();
        listClientes.getSelectionModel().clearSelection();
    }

    @FXML
    public void doConsultarCliente() {
        Cliente clienteSeleccionado = listClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            txtcodigo.setText(String.valueOf(clienteSeleccionado.getCodigo()));
            txtcedula.setText(clienteSeleccionado.getCedula());
            txtnombres.setText(clienteSeleccionado.getNombres());
        }
    }

}
