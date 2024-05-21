package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.MesaDAO;
import sample.views.MesasTaqueria;
import sample.views.MesaForms;

import java.util.Optional;

public class CellCustomMesa extends TableCell<MesaDAO, String> {
    private Button btnCelda;
    private int opc;
    private MesaDAO objMDAO;

    public CellCustomMesa(int opc, MesasTaqueria mesasTaqueria) {
        this.opc = opc;
        if (opc == 1) {
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                objMDAO = CellCustomMesa.this.getTableView().getItems().get(CellCustomMesa.this.getIndex());
                mesasTaqueria.actualizarFormulario(objMDAO); // Llamar al método actualizarFormulario de MesasTaqueria
            });
        } else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar acción");
                alerta.setContentText("¿Realmente deseas borrar esta mesa?");
                Optional<ButtonType> result = alerta.showAndWait();

                if (result.get() == ButtonType.OK) {
                    objMDAO = CellCustomMesa.this.getTableView().getItems().get(CellCustomMesa.this.getIndex());
                    objMDAO.ELIMINAR();
                    CellCustomMesa.this.getTableView().setItems(objMDAO.SELECCIONAR());
                    CellCustomMesa.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setGraphic(btnCelda);
        }
    }
}