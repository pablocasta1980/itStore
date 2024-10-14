package principal.ControladorVentanas;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import principal.Login;
import java.io.IOException;

public class LoginController {

    @FXML
    private Button JButton_Acceder;

    @FXML
    private ImageView fondo;

    @FXML
    private Label labelNombreUsuario;

    @FXML
    private Label labelcontrasena;

    @FXML
    private Label lbTitulo;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label txt_piePagina;

    @FXML
    private TextField txt_user;

    @FXML
    void clic_acceder(ActionEvent event) throws IOException {


        Login login = new Login(); // Instanciar la clase Login

        String usuario = txt_user.getText();
        String contrasena = txt_password.getText();
        Stage actualStage = (Stage) JButton_Acceder.getScene().getWindow();

        login.validarCredenciales(usuario, contrasena, actualStage);

        // Limpiar los TextField después de intentar el ingreso
        txt_user.setText("");
        txt_password.setText("");


    }

}

