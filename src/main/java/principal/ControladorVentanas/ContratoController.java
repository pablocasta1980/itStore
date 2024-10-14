package principal.ControladorVentanas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import principal.Contrato;

public class ContratoController {

    public static String nom,dir,mail,tel,fec = "";

    @FXML
    private ImageView firmarContrato;

    @FXML
    private ImageView bucar_nit;

    @FXML
    private Label lbl_direccion;

    @FXML
    private Label lbl_email;

    @FXML
    private Label lbl_fechaAfiliacion;

    @FXML
    private Label lbl_nombre;

    @FXML
    private Label lbl_telefono;

    @FXML
    private TextField txt_nit;

    @FXML
    private TextField txt_valor;

    @FXML
    private TextField txt_bodega;

    @FXML
    private TextField txt_capacidad;

    @FXML
    private TextField txt_fechaFin;

    @FXML
    private TextField txt_fechaInicio;

    @FXML
    void firmarContrato(MouseEvent event) {

        String nit;
        nit = txt_nit.getText();



        String bodega,capacidad,fechaInicio,fechaFin,valorStr;
        int valor=0;

        bodega = txt_bodega.getText().trim();
        capacidad = txt_bodega.getText().trim();
        fechaInicio = txt_fechaInicio.getText().trim();
        fechaFin = txt_fechaFin.getText().trim();

        valorStr = txt_nit.getText().trim();

        try {
            // Convertir el NIT de String a entero
            valor = Integer.parseInt(valorStr);
            System.out.println("Conversión valor a entero: " + valor);


        } catch (NumberFormatException e) {
            // Manejo de error: NIT no válido
            System.out.println("Por favor, ingresa un VALOR válido.");
            // Puedes agregar una etiqueta o un mensaje de error visual para el usuario


        }


        Contrato contrato = new Contrato();
       contrato.guardarDatosContrato(nit,bodega,capacidad,fechaInicio,fechaFin,valor);

    }



    @FXML
    void bucarPorNit(MouseEvent event) {
        String nitStr = txt_nit.getText().trim();

        try {
            // Convertir el NIT de String a entero
            int nit = Integer.parseInt(nitStr);
            System.out.println("Conversión nit a entero: " + nit);

            Contrato contrato = new Contrato();
            contrato.cargarDatosCliente(nit);

            lbl_nombre.setText(nom);
            lbl_direccion.setText(dir);
            lbl_email.setText(mail);
            lbl_telefono.setText(tel);
            lbl_fechaAfiliacion.setText(fec);


        } catch (NumberFormatException e) {
            // Manejo de error: NIT no válido
            System.out.println("Por favor, ingresa un NIT válido.");
            // Puedes agregar una etiqueta o un mensaje de error visual para el usuario


        }

    }

    public static void llenarCampos(String nombre,String direccion,String email,String telefono,String fechaAfiliacion) {

        ContratoController.nom =nombre;
        ContratoController.dir=direccion;
        ContratoController.mail=email;
        ContratoController.tel =telefono;
        ContratoController.fec =fechaAfiliacion;
    }


}
