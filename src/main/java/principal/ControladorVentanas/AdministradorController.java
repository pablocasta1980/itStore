package principal.ControladorVentanas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.Administrador;
import principal.Login;
import principal.RegistrarUsuario;

import java.io.IOException;

public class AdministradorController {


    @FXML
    private ImageView registrarUsuario;

    @FXML
    void registrarUsuario_P_A(MouseEvent event) throws IOException {

        RegistrarUsuario registrarUsuario= new RegistrarUsuario();
        registrarUsuario.ingresarUsuario();



    }

}