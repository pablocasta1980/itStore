module principal.itstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires itext5.itextpdf;



    opens principal to javafx.fxml;
    exports principal;

    opens principal.ControladorVentanas to javafx.fxml;
    exports principal.ControladorVentanas;




}