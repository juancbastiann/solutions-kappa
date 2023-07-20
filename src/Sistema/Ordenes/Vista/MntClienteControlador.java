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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
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
    private TableColumn<Cliente, Integer> tcCodigo;

    @FXML
    private TableColumn<Cliente, String> tcCedula;

    @FXML
    private TableColumn<Cliente, String> tcNombres;

    @FXML
    private TableView<Cliente> tcList;

    private ObservableList<Cliente> observableClientes;

    private IClienteDAO daoCliente = new ClienteListDAO();
    private IMantenedorClienteFacade mantenedor = new MantenedorCliente(daoCliente);

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        List<Cliente> listaClientes = mantenedor.traerTodos();
        observableClientes = FXCollections.observableArrayList(listaClientes);
        tcList.setItems(observableClientes);

        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        tcNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
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
        tcList.getSelectionModel().clearSelection();
    }

    @FXML
    public void doConsultarCliente() {
        int codigo = Integer.parseInt(txtcodigo.getText());
        Cliente clienteEncontrado = null;

        for (Cliente cliente : observableClientes) {
            if (cliente.getCodigo() == codigo) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            tcList.getSelectionModel().select(clienteEncontrado);
        } else {
            txtcedula.clear();
            txtnombres.clear();
        }
    }

}
