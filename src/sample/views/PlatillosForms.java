package sample.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;

public class PlatillosForms extends Stage {

    private Scene escena;
    private VBox vBox,vBox1, vBox2;
    private HBox hBox;
    private Label lblNombrePlatillo,lblCosto,lblTamanio,lblPrecio; // Cambio en el nombre de la etiqueta
    private TextField txtNombrePlatillo,txtCosto,txtTamanio,txtPrecio; // Cambio en el nombre del campo
    private Button btnGuardar;
    private PlatillosDAO objPltDAO;
    private TableView<PlatillosDAO> tbvPlatillos;

    public PlatillosForms(TableView<PlatillosDAO> tbvPlatillos, PlatillosDAO objPltDAO){
        this.tbvPlatillos = tbvPlatillos;
        if( objPltDAO != null )
            this.objPltDAO = objPltDAO;             // La acción es una actualización
        else
            this.objPltDAO = new PlatillosDAO();  // La acción es una inserción
        CrearUI();
        this.setTitle("Formulario De Platillos"); // Cambio en el título
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtNombrePlatillo = new TextField();
        txtNombrePlatillo.setPromptText("Platillo");
        txtNombrePlatillo.setText(objPltDAO.getNombreplatillo()); // Cambio en el método getter
        lblNombrePlatillo = new Label("Nombre Del Platillo");
        txtNombrePlatillo.setMinWidth(230);

        txtCosto = new TextField();
        txtCosto.setPromptText("Costo");
        txtCosto.setText(String.valueOf(objPltDAO.getCosto())); // Cambio en el método getter
        lblCosto = new Label("Costo Del Platillo");

        txtTamanio = new TextField();
        txtTamanio.setPromptText("C(Chico) - M(Mediano) - G(Grande)");
        txtTamanio.setText(objPltDAO.getTamanio()); // Cambio en el método getter
        lblTamanio = new Label("Tamaño Del Platillo"); // Cambio en el nombre de la etiqueta

        txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio");
        txtPrecio.setText(String.valueOf(objPltDAO.getPrecio())); // Cambio en el método getter
        lblPrecio = new Label("Precio Del Platillo");

        btnGuardar = new Button("Guardar");

        btnGuardar.setOnAction(event -> {
            objPltDAO.setNombreplatillo(txtNombrePlatillo.getText()); // Cambio en el método setter
            objPltDAO.setCosto(Double.parseDouble(txtCosto.getText())); // Cambio en el método setter
            objPltDAO.setTamanio(txtTamanio.getText()); // Cambio en el método setter
            objPltDAO.setPrecio(Double.parseDouble(txtPrecio.getText())); // Cambio en el método setter

            if( objPltDAO.getIdPlatillo() > 0 ) // Cambio en el método getter
                objPltDAO.ACTUALIZARPlatillos(); // Cambio en el método
            else
                objPltDAO.INSERTARPlatilos(); // Cambio en el método

            tbvPlatillos.setItems(objPltDAO.SELECCIONARPlatillos());
            tbvPlatillos.refresh();

            this.close();
        });

        vBox = new VBox();
        vBox1 = new VBox();
        vBox2 = new VBox();
        hBox = new HBox();
        vBox1.setSpacing(7);
        vBox1.setPadding(new Insets(10.0));
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10.0));
        vBox1.getChildren().addAll(lblNombrePlatillo,lblCosto,lblTamanio,lblPrecio); // Cambio en la lista de etiquetas
        vBox2.getChildren().addAll(txtNombrePlatillo,txtCosto,txtTamanio,txtPrecio); // Cambio en la lista de campos
        hBox.getChildren().addAll(vBox1,vBox2);
        vBox.getChildren().addAll(hBox,btnGuardar);
        escena = new Scene(vBox,400 ,250);
    }
}
