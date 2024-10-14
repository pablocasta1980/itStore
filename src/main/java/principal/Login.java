package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import principal.ControladorVentanas.AdministradorController;
import principal.ControladorVentanas.AgenteVentasController;
import principal.ControladorVentanas.ClienteController;
import principal.conexion.Conexion;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends Application {
    public static String user = "";
    String pass = "";


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ventanaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("INICIO DE SESION... ");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DS.PNG")));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    // Método para validar las credenciales
    public boolean validarCredenciales(String user, String pass, Stage actualStage) throws IOException {



        if (user.equals("") || user.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes diligenciar los datos");
            return false;
        } else {
            this.user =user;

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(

                        "select tipo_nivel, estatus from usuarios where username = '" + user + "' and password = '" + pass + "'");

                    ResultSet rs = pst.executeQuery();

                    if(rs.next()){
                    String tipo_nivel = rs.getString("tipo_nivel");
                    String estatus = rs.getString("estatus");

                    if(tipo_nivel.equalsIgnoreCase("Administrador") && estatus.equalsIgnoreCase("Activo")){
                        cargarVentanaAdministrador();
                        actualStage.close();//para cerrar la ventana de login me traigo por parametro actualStage

                    }else if(tipo_nivel.equalsIgnoreCase("Cliente") && estatus.equalsIgnoreCase("Activo")){
                        cargarVentanaCliente();
                        actualStage.close();//para cerrar la ventana de login me traigo por parametro actualStag

                    }else if(tipo_nivel.equalsIgnoreCase("AgenteVentas") && estatus.equalsIgnoreCase("Activo")){
                        cargarVentanaAgenteVentas();
                        actualStage.close();//para cerrar la ventana de login me traigo por parametro actualStag
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "Datos de acceso incorrectos");
                }


                return true;

            } catch (SQLException e) {
                System.err.println("Error en el boton acceder");
                JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
            }

            return false;
        }
    }

    private void cargarVentanaAgenteVentas() throws IOException {

        // Cargar la nueva ventana solo si los datos son válidos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/ventanaAgenteVentas.fxml"));
        Parent root = loader.load();
        AgenteVentasController agenteVentasController = loader.getController();

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select nombre_usuario from usuarios where username = '" + user + "'");

            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                user = rs.getString("nombre_usuario");

            }
        } catch (Exception e) {
            System.err.println("Error en conexión desde la interfaz agente venta");
        }


        // Crear una nueva escena y mostrarla
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Agente Ventas - sesion de " + user);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DS.PNG")));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }

    private void cargarVentanaCliente() throws IOException {

        // Cargar la nueva ventana solo si los datos son válidos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/ventanaCliente.fxml"));
        Parent root = loader.load();
        ClienteController clienteController = loader.getController();



        // Crear una nueva escena y mostrarla
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Ventana Cliente");
        stage.show();
    }


    public void cargarVentanaAdministrador() throws IOException {


        // Cargar la nueva ventana solo si los datos son válidos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/ventanaAdministrador.fxml"));
        Parent root = loader.load();
        AdministradorController administradorController = loader.getController();

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select nombre_usuario from usuarios where username = '" + user + "'");

            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                user = rs.getString("nombre_usuario");

            }
        } catch (Exception e) {
            System.err.println("Error en conexión desde la interfaz Administrador");
        }

        // Crear una nueva escena y mostrarla
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Administrador sesion de " + user);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DS.PNG")));
        stage.setResizable(false);
        stage.show();


    }
}



