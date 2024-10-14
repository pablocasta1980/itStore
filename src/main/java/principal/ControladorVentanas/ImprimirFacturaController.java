package principal.ControladorVentanas;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import principal.Cliente2;
import principal.conexion.Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class ImprimirFacturaController {

    @FXML
    private Button btn_imprimir;

    @FXML
    private TextField txt_nit;

    @FXML
    private TableColumn<Cliente2, String> col_bodega;

    @FXML
    private TableColumn<Cliente2, String> col_capacidad;

    @FXML
    private TableColumn<Cliente2, String> col_direccion;

    @FXML
    private TableColumn<Cliente2, String> col_email;

    @FXML
    private TableColumn<Cliente2, Integer> col_id;

    @FXML
    private TableColumn<Cliente2, String> col_nombre;

    @FXML
    private TableColumn<Cliente2, String> col_telefono;

    @FXML
    private TableColumn<Cliente2, Integer> col_valor;

    @FXML
    private TableView<Cliente2> tablaDatosImprimir;



    @FXML
    void imprimirFact(MouseEvent event) {
        // Configurar columnas
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        col_bodega.setCellValueFactory(new PropertyValueFactory<>("bodega"));
        col_capacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        col_valor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        // Cargar los datos
        cargarDatosClientes();
        int id_cliente = Integer.parseInt(txt_nit.getText().trim());
        generarPDF(Integer.parseInt(String.valueOf(id_cliente)));

    }

    private void generarPDF(int id_cliente) {
        Document documento = new Document();

        try {

            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Factura_" + txt_nit.getText().trim() + ".pdf"));

            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/main/resources/images/Banner4.PNG");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Factura del cliente. \n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();
            documento.add(header);
            documento.add(parrafo);


            // Tabla para la información del cliente
            PdfPTable tablaCliente = new PdfPTable(5);
            tablaCliente.addCell("ID");
            tablaCliente.addCell("Nombre");
            tablaCliente.addCell("Email");
            tablaCliente.addCell("Teléfono");
            tablaCliente.addCell("Dirección");

            try {

                // Obtener datos del cliente con el NIT proporcionado
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select * from clientes where nit = ?");
                pst.setString(1, txt_nit.getText().trim());

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {
                        tablaCliente.addCell(rs.getString("idCliente"));
                        tablaCliente.addCell(rs.getString("nombre"));
                        tablaCliente.addCell(rs.getString("email"));
                        tablaCliente.addCell(rs.getString("telefono"));
                        tablaCliente.addCell(rs.getString("direccion"));
                    } while (rs.next());

                    documento.add(tablaCliente);
                }

                // Añadir un párrafo de separación
                Paragraph parrafo2 = new Paragraph();
                parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo2.add("\n \n Detalles del contrato. \n \n");
                parrafo2.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));
                documento.add(parrafo2);

                // Tabla para los detalles del contrato
                PdfPTable tablaContrato = new PdfPTable(4);
                tablaContrato.addCell("ID Contrato");
                tablaContrato.addCell("Bodega");
                tablaContrato.addCell("Capacidad");
                tablaContrato.addCell("Valor");

                try {

                    System.out.println("vamos aqui");
                    // Obtener detalles del contrato basado en el NIT del cliente
                    Connection cn2 = Conexion.conectar();
                    PreparedStatement pst2 = cn2.prepareStatement(
                            "select id_contrato, bodega, capacidad, valor from contrato where id_cliente = ?");
                    pst2.setString(1, txt_nit.getText().trim());

                    ResultSet rs2 = pst2.executeQuery();

                    if (rs2.next()) {
                        do {
                            tablaContrato.addCell(rs2.getString("id_contrato"));
                            tablaContrato.addCell(rs2.getString("bodega"));
                            tablaContrato.addCell(rs2.getString("capacidad"));
                            tablaContrato.addCell(rs2.getString("valor"));
                        } while (rs2.next());

                        documento.add(tablaContrato);
                    }

                } catch (SQLException e) {
                    System.err.println("Error al cargar contratos " + e);
                }

            } catch (SQLException e) {
                System.err.print("Error al obtener datos del cliente. " + e);
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "Factura generada correctamente.");

        } catch (DocumentException | IOException e) {
            System.err.println("Error en PDF o ruta de imagen: " + e);
            JOptionPane.showMessageDialog(null, "Error al generar PDF, contacte al administrador");
        }
    }

    private void cargarDatosClientes() {
        ObservableList<Cliente2> listaClientes = FXCollections.observableArrayList();

        String query = "SELECT idCliente, nombre, direccion, email, telefono,nit,fechaAfiliacion,idCliente FROM clientes";

        //SE DEBE SACAR LOS VALORES NIT, FECHAAFILIACION ESTADO PARA OBTENERLOS DE OTRA TABAL

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String bodega = rs.getString("nit"); // SE DEBE PASAR DE LA TABLA CONTRATO BODEGA
                String capacidad = rs.getString("fechaAfiliacion");// SE DEBE PASAR DE LA TABLA CONTRATO BODEGA
                int valor = rs.getInt("idCliente");// SE DEBE PASAR DE LA TABLA CONTRATO BODEGA

                listaClientes.add(new Cliente2(id, nombre, direccion, email, telefono, bodega, capacidad, valor));
            }

            tablaDatosImprimir.setItems(listaClientes);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de clientes");
        }
    }
}

