package principal.ControladorVentanas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.*;

import java.io.IOException;

import static principal.Login.user;

public class AgenteVentasController {

    @FXML
    private ImageView registrarCliente;

    @FXML
    private ImageView registrarUsuario1;

    @FXML
    void registrarCliente(MouseEvent event) throws IOException {
       // Cerrar la ventana actual
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        RegistrarCliente registrarCliente = new RegistrarCliente();
        registrarCliente.ingresarCliente();
    }


    @FXML
    void gestionarCliente(MouseEvent event) throws IOException {
        //Cerrar la ventana actual
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        GestionarClientes gestionarCliente = new GestionarClientes();
        gestionarCliente.actualizarCliente();

    }

    @FXML
    void gestionarContrato(MouseEvent event) throws IOException {
        System.out.println("contrato");

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        Contrato contrato = new Contrato();
        contrato.nuevoContrato();
    }

    @FXML
    void imprimirFactura(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        ImprimirFactura imprimirFactura = new ImprimirFactura();
        imprimirFactura.imprimir();


    }
}
