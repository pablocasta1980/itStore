package principal;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import principal.ControladorVentanas.GestionarClientesController;

import java.sql.*;
import principal.conexion.Conexion;

import java.io.IOException;

public class GestionarClientes extends Application {

    public static String user_update = "";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ventanaGestionarClientes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setScene(scene);
        stage.show();
    }


    public void actualizarCliente() throws IOException {
        // Cargar la nueva ventana solo si los datos son válidos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/ventanaGestionarClientes.fxml"));
        Parent root = loader.load();
        GestionarClientesController gestionarclienteController = loader.getController();

        // Crear una nueva escena y mostrarla
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Gestionar cliente - sesion de " + Login.user);
        stage.show();
    }


    // Método para obtener los clientes desde la base de datos
    public static ObservableList<ListaClienteObservable> getClientes() {
        ObservableList<ListaClienteObservable> clientes = FXCollections.observableArrayList();

        // Conectar a la base de datos
        Connection conn = Conexion.conectar();

        if (conn != null) {
            try {
                // Consulta SQL para obtener todos los clientes
                String sql = "SELECT idCliente, nombre, direccion, email, estado, fechaAfiliacion, nit, registrado_por, telefono, username FROM clientes";

                // Preparar y ejecutar la consulta
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                // Procesar el resultado
                while (rs.next()) {
                    int idCliente = rs.getInt("idCliente");
                    String nombre = rs.getString("nombre");
                    String direccion = rs.getString("direccion");
                    String email = rs.getString("email");
                    String estado = rs.getString("estado");
                    String fechaAfiliacion = rs.getString("fechaAfiliacion");
                    int nit = rs.getInt("nit");
                    String registrado_por = rs.getString("registrado_por");
                    String telefono = rs.getString("telefono");
                    String username = rs.getString("username");

                    // Crear un objeto Cliente2 con los datos
                    ListaClienteObservable cliente = new ListaClienteObservable(idCliente, nombre, direccion, email, estado, fechaAfiliacion, nit, registrado_por, telefono, username);

                    // Agregar el cliente a la lista
                    clientes.add(cliente);
                }

                // Cerrar la conexión
                rs.close();
                pst.close();
                conn.close();

            } catch (SQLException e) {
                System.err.println("Error al obtener los datos de los clientes: " + e.getMessage());
            }
        }

        return clientes;
    }

}