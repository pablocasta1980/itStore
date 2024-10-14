package principal.ControladorVentanas;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import principal.RegistrarCliente;

public class RegistrarClienteController {

    @FXML
    private ComboBox<String> cmb_estado;

    // Método para inicializar el ComboBox
    @FXML
    public void initialize() {
        // Añadir opciones al ComboBox
        cmb_estado.getItems().addAll("Activo", "Inactivo");
        cmb_estado.setValue("Activo");
    }


    @FXML
    private ImageView registrarCliente;

    @FXML
    private TextField txt_direccion;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_fechaAfil;

    @FXML
    private TextField txt_nit;

    @FXML
    private TextField txt_nombre;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_registadorPor;

    @FXML
    private TextField txt_telefono;

    @FXML
    private TextField txt_usuario;

    @FXML
    void registrarCliente(MouseEvent event) {
        String direccion,email,fechaAfil,nombre,registradoPor,password,estado,telefono,usuario;
        int nit = 0;

        direccion = txt_direccion.getText().trim();
        email = txt_email.getText().trim();
        fechaAfil = txt_fechaAfil.getText();
        nombre = txt_nombre.getText().trim();
        registradoPor = txt_registadorPor.getText().trim();
        password = txt_password.getText().trim();
        estado = cmb_estado.getValue();
        telefono = txt_telefono.getText().trim();
        String nitText = txt_nit.getText();  // Obtener el texto del TextField
        usuario = txt_usuario.getText().trim();

        try {
            nit = Integer.parseInt(nitText);  // Convertir el texto a entero
            // Aquí puedes usar el valor de nit
        } catch (NumberFormatException e) {
            // Manejar el caso en que el valor no es un número entero válido
            System.out.println("El valor ingresado no es un número entero válido.");
        }

        limpiarCampos();

        RegistrarCliente registrarCliente= new RegistrarCliente();
        registrarCliente.guardarClienteBD(direccion,email,fechaAfil,nombre,registradoPor,password,estado,telefono,nit,usuario);
    }

    private void limpiarCampos() {
        txt_direccion.setText("");
        txt_email.setText("");
        txt_fechaAfil.setText("");
        txt_nombre.setText("");
        txt_registadorPor.setText("");
        txt_password.setText("");
        cmb_estado.setValue("Activo");
        txt_telefono.setText("");
        txt_nit.setText("");
        txt_usuario.setText("");


    }


}