package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import principal.ControladorVentanas.RegistrarClienteController;
import principal.conexion.Conexion;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static principal.Login.user;

public class RegistrarCliente extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ventanaRegistrarCliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("Registrar Cliente");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DS.PNG")));
        stage.setResizable(false);
        stage.show();
    }

    public void ingresarCliente() throws IOException {

        // Cargar la nueva ventana solo si los datos son válidos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/ventanaRegistrarCliente.fxml"));
        Parent root = loader.load();
        RegistrarClienteController registrarclienteController = loader.getController();

        // Crear una nueva escena y mostrarla
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Registrar cliente - sesion de " + user);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            try {
                // Cargar nuevamente la ventana de AgenteVentas cuando se cierra la ventana actual
                FXMLLoader agentLoader = new FXMLLoader(getClass().getResource("/principal/ventanaAgenteVentas.fxml"));
                Parent agentRoot = agentLoader.load();

                // Mostrar la ventana de AgenteVentas
                Stage agentStage = new Stage();
                agentStage.setScene(new Scene(agentRoot));
                agentStage.setTitle("\"Agente Ventas - sesion de " + user);
                agentStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // Mostrar la ventana nueva
        stage.show();
    }

    public void guardarClienteBD(String direccion, String email, String fechaAfil, String nombre, String registradoPor,
                                 String password, String estado, String telefono, int nit, String usuario) {


        int validacion = 0;

        if (direccion.equals("")) {
            validacion++;
        }
        if (email.equals("")) {
            validacion++;
        }
        if (fechaAfil.equals("")) {
            validacion++;
        }
        if (nombre.equals("")) {
            validacion++;
        }
        if (registradoPor.equals("")) {
            validacion++;
        }
        if (password.equals("")) {
            validacion++;
        }
        if (estado.equals("")) {
            validacion++;
        }
        if (telefono.equals("")) {
            validacion++;
        }
        if (nit == 0) {
            validacion++;
        }
        if (usuario.equals("") ) {
            validacion++;
        }


        if (validacion != 0) {
            System.out.println(validacion);

            JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos");
        } else {
            System.out.println("ingresa a guardar");

//variable user traida desde login
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select username from clientes where username = '" + usuario + "'");
                ResultSet rs = pst.executeQuery();


                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible.");
                    cn.close();

                } else {

                    cn.close();

                    try {

                        Connection cn2 = Conexion.conectar();
                        PreparedStatement pst2 = cn2.prepareStatement(
                                "insert into clientes values (?,?,?,?,?,?,?,?,?,?)");


                        pst2.setInt(1, 0);
                        pst2.setString(2, nombre);
                        pst2.setInt(3,nit);
                        pst2.setString(4, direccion);
                        pst2.setString(5, email);
                        pst2.setString(6, fechaAfil);
                        pst2.setString(7, telefono);
                        pst2.setString(8, estado);
                        pst2.setString(9, registradoPor);
                        pst2.setString(10, usuario);

                        pst2.executeUpdate();
                        PreparedStatement pst3 = cn2.prepareStatement(
                                "insert into usuarios values (?,?,?,?,?,?,?,?,?)");

                        pst3.setInt(1, 0);
                        pst3.setString(2, nombre);
                        pst3.setString(3, email);
                        pst3.setString(4, telefono);
                        pst3.setString(5, usuario);
                        pst3.setString(6, password);
                        pst3.setString(7, "Cliente");
                        pst3.setString(8, estado);
                        pst3.setString(9, user);

                        pst3.executeUpdate();
                        cn2.close();
                        JOptionPane.showMessageDialog(null, "Registro exitoso.");


                    } catch (SQLException e) {
                        System.err.println("Error en Registrar usuario." + e);
                        JOptionPane.showMessageDialog(null, "¡¡ERROR al registrar!!, contacte al administrador.");
                    }

                }

            } catch (SQLException e) {
                System.err.println("Error en validar nombre de usario." + e);
                JOptionPane.showMessageDialog(null, "¡¡ERROR al comparar usuario!!, contacte al administrador.");

            }


        }


    }
}
