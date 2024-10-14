package principal;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import principal.ControladorVentanas.ContratoController;
import principal.conexion.Conexion;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static principal.Login.user;

public class Contrato extends Application {



    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ventanaContrato.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 680);
        stage.setScene(scene);
        stage.show();
    }


    public void nuevoContrato() throws IOException {

        String user= Login.user;


        // Cargar la nueva ventana solo si los datos son válidos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/ventanaContrato.fxml"));
        Parent root = loader.load();
        ContratoController contratoController = loader.getController();
        // Crear una nueva escena y mostrarla
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Registrar Contrato - sesion de  " + user);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DS.PNG")));
        stage.setResizable(false);
        stage.show();

    }

    public void cargarDatosCliente(int nit) {
        String query = "SELECT * FROM clientes WHERE nit = ?";
        try (
             Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nit);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre, direccion, email, telefono,fechaAfiliacion;

                nombre = rs.getString("nombre");
                direccion = rs.getString("direccion");
                email = rs.getString("email");
                telefono = rs.getString("telefono");
                fechaAfiliacion = rs.getString("fechaAfiliacion");


                ContratoController.llenarCampos(nombre,direccion,email,telefono,fechaAfiliacion);

            } else {
                System.out.println("No se encontró ningún cliente con el NIT: " + nit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDatosContrato(String nit, String bodega, String capacidad, String fechaInicio, String fechaFin, int valor) {

        int validacion = 0;

        if (nit.equals("")){
            validacion++;
        }

        if (bodega.equals("")) {
            validacion++;
        }
        if (capacidad.equals("")) {
            validacion++;
        }
        if (fechaInicio.equals("")) {
            validacion++;
        }
        if (fechaFin.equals("")) {
            validacion++;
        }
        if (valor == 0) {
            validacion++;
        }

        if (validacion != 0) {
            JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos");
        } else {
            System.out.println("ingresa a guardar datos contrato");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "SELECT idCliente FROM clientes WHERE nit = ?");
                pst.setString(1, nit);  // Usamos ? para evitar inyecciones SQL
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int idCliente = rs.getInt("idCliente"); // Capturamos el idCliente
                    System.out.println("ID del cliente: " + idCliente);
                    cn.close(); // Cerramos la conexión

                    // Continuamos con la inserción en la tabla contrato usando el idCliente
                    try {
                        Connection cn2 = Conexion.conectar();
                        PreparedStatement pst2 = cn2.prepareStatement(
                                "INSERT INTO contrato VALUES (?,?,?,?,?,?,?)");

                        pst2.setInt(1, 0); // ID autogenerado
                        pst2.setInt(2, idCliente); // Usamos el idCliente capturado
                        pst2.setString(3, bodega);
                        pst2.setString(4, capacidad);
                        pst2.setString(5, fechaInicio);
                        pst2.setString(6, fechaFin);
                        pst2.setInt(7, valor);

                        pst2.executeUpdate();
                        cn2.close();
                        JOptionPane.showMessageDialog(null, "Registro exitoso.");

                    } catch (SQLException e) {
                        System.err.println("Error en registrar contrato: " + e);
                        JOptionPane.showMessageDialog(null, "Error al registrar contrato, contacte al administrador.");
                    }

                } else {
                    System.out.println("No se encontró ningún cliente con el NIT proporcionado.");
                    cn.close(); // Cerramos la conexión
                }

            } catch (SQLException e) {
                System.err.println("Error en validar cliente por NIT: " + e);
                JOptionPane.showMessageDialog(null, "Error al buscar cliente por NIT, contacte al administrador.");
            }
        }
    }
}
