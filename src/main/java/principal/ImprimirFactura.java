package principal;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import principal.ControladorVentanas.ContratoController;
import principal.ControladorVentanas.ImprimirFacturaController;


import java.io.IOException;

public class ImprimirFactura extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ventanaImprimirFactura.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("Imprimir Factura");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DS.PNG")));
        stage.setResizable(false);
        stage.show();
    }

    public void imprimir() throws IOException {

        String user= Login.user;


        // Cargar la nueva ventana solo si los datos son válidos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/ventanaImprimirFactura.fxml"));
        Parent root = loader.load();
        ImprimirFacturaController imprimirFacturaController = loader.getController();
        // Crear una nueva escena y mostrarla
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Imprimir Factura - sesion de  " + user);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DS.PNG")));
        stage.setResizable(false);
        stage.show();

    }

}
