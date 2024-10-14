package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.*;
import principal.conexion.Conexion;
import java.io.IOException;

public class Administrador extends Application {

    public static int sesion_usuario;


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ventanaAdministrador.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 680);
        stage.setScene(scene);
        stage.show();
    }


    public void ingresarUsuario() {

      RegistrarUsuario  registrarUsuario = new RegistrarUsuario();
      String user,nombre_usuario;
      user= Login.user;
      sesion_usuario=1;
    }
}