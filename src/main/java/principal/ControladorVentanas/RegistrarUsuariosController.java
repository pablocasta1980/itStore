package principal.ControladorVentanas;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import principal.RegistrarUsuario;

public class RegistrarUsuariosController {

    @FXML

    private ComboBox<String> cmb_niveles;

    // Método para inicializar el ComboBox
    @FXML
    public void initialize() {
        // Añadir opciones al ComboBox
        cmb_niveles.getItems().addAll("Administrador", "Cliente", "AgenteVentas");
        cmb_niveles.setValue("Administrador");
    }

    @FXML
    private ImageView guardarUsuario;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_nombre;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_telefono;

    @FXML
    private TextField txt_username;



    @FXML
    void guardarUsuario(MouseEvent event) {
        String nombre,email,telefono,username,password,niveles;

        nombre = txt_nombre.getText().trim();
        email = txt_email.getText().trim();
        telefono = txt_telefono.getText().trim();
        username = txt_username.getText().trim();
        password = txt_password.getText().trim();
        niveles = cmb_niveles.getValue();

        Limpiar();

        //metodo para guardar los datos en la base de datos clase RegistrarUsuario
        RegistrarUsuario.guardarDatosBD(nombre,email,telefono,username,password,niveles);
    }

    private void Limpiar() {
        txt_nombre.setText("");
        txt_email.setText("");
        txt_telefono.setText("");
        txt_username.setText("");
        txt_password.setText("");
        cmb_niveles.setValue("Administrador");


    }

}

