package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class Cliente extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ventanaCliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("USUARIO_cliente");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DS.PNG")));
        stage.setResizable(false);
        stage.show();
    }
}