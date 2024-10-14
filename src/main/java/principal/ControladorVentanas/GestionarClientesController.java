package principal.ControladorVentanas;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import principal.ListaClienteObservable;
import principal.GestionarClientes;

public class GestionarClientesController {

    @FXML
    private TableColumn<ListaClienteObservable, String> col_direccion;

    @FXML
    private TableColumn<ListaClienteObservable, String> col_email;

    @FXML
    private TableColumn<ListaClienteObservable, String> col_estado;

    @FXML
    private TableColumn<ListaClienteObservable, String> col_fechaAfil;

    @FXML
    private TableColumn<ListaClienteObservable, Integer> col_idCliente;

    @FXML
    private TableColumn<ListaClienteObservable, Integer> col_nit;

    @FXML
    private TableColumn<ListaClienteObservable, String> col_nombre;

    @FXML
    private TableColumn<ListaClienteObservable, String> col_registrado;

    @FXML
    private TableColumn<ListaClienteObservable, String> col_telefono;

    @FXML
    private TableColumn<ListaClienteObservable, String> col_username;

    @FXML
    private TableView<ListaClienteObservable> table_clientes;

    // Método para inicializar la tabla
    @FXML
    public void initialize() {
        col_idCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        col_fechaAfil.setCellValueFactory(new PropertyValueFactory<>("fechaAfil"));
        col_nit.setCellValueFactory(new PropertyValueFactory<>("nit"));
        col_registrado.setCellValueFactory(new PropertyValueFactory<>("registrado"));
        col_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));

        // Cargar los datos desde la base de datos
        table_clientes.setItems(GestionarClientes.getClientes());
    }


}
