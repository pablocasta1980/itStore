package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import principal.ControladorVentanas.RegistrarUsuariosController;
import principal.conexion.Conexion;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static principal.Login.user;


public class RegistrarUsuario extends Application {


    public static void guardarDatosBD(String nombre,String email,String telefono,String username,String password, String niveles) {

        int validacion = 0;

        if (nombre.equals("")) {
            validacion++;
        }
        if (email.equals("")) {
            validacion++;
        }
        if (telefono.equals("")) {
            validacion++;
        }
        if (username.equals("")) {
            validacion++;
        }
        if (password.equals("")) {
            validacion++;
        }
        if (niveles.equals("")) {
            validacion++;
        }
        if (validacion != 0) {
            JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos");
        } else {
            System.out.println("ingresa a guardar");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select username from usuarios where username = '" + username + "'");
                ResultSet rs = pst.executeQuery();


                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible.");
                    cn.close();

                } else {

                    cn.close();

                    try {

                        Connection cn2 = Conexion.conectar();
                        PreparedStatement pst2 = cn2.prepareStatement(
                                "insert into usuarios values (?,?,?,?,?,?,?,?,?)");

                        pst2.setInt(1, 0);
                        pst2.setString(2, nombre);
                        pst2.setString(3, email);
                        pst2.setString(4, telefono);
                        pst2.setString(5, username);
                        pst2.setString(6, password);
                        pst2.setString(7, niveles);
                        pst2.setString(8, "Activo");
                        pst2.setString(9, user);

                        pst2.executeUpdate();
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


            System.out.println(validacion + "  " + nombre + email + telefono + username + password + " " + niveles);
        }
    }
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ventanaRegistrarUsuarios.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 680);
        stage.setScene(scene);
        stage.show();
    }


    public void ingresarUsuario() throws IOException {

        String user= Login.user;


        // Cargar la nueva ventana solo si los datos son válidos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/ventanaRegistrarUsuarios.fxml"));
        Parent root = loader.load();
        RegistrarUsuariosController registrarUsuariosController = loader.getController();
        // Crear una nueva escena y mostrarla
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Registrar Nuevo Usuario - sesion de  " + user);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DS.PNG")));
        stage.setResizable(false);
        stage.show();


    }
}
