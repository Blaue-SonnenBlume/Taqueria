package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.Main.BaseDeDatos;

public class OrdenesDAO {

    private int id_orden;
    private double montoOrden;
    private String fechaOrden;
    private int idEmpleado;
    private int idCliente;
    private Statement stmt;

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public double getMontoOrden() {
        return montoOrden;
    }

    public void setMontoOrden(double montoOrden) {
        this.montoOrden = montoOrden;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public void INSERTAROrdenes(){
        String query = "INSERT INTO ordenes (montoOrden, fechaOrden, idEmpleado, idCliente) " +
                "VALUES("+this.montoOrden+", '"+this.fechaOrden+"', "+this.idEmpleado+", "+this.idCliente+")";
        try {
            if (BaseDeDatos == 1){
                stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            } else if (BaseDeDatos == 2){
                stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAROrdenes(){
        String query = "UPDATE ordenes SET montoOrden="+this.montoOrden+", fechaOrden='"+this.fechaOrden+"'," +
                " idEmpleado="+this.idEmpleado+", idCliente="+this.idCliente+" WHERE id_orden = "+this.id_orden;
        try {
            if (BaseDeDatos == 1){
                stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            } else if (BaseDeDatos == 2){
                stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAROrdenes(){
        String query = "DELETE FROM ordenes WHERE id_orden = "+this.id_orden;
        try {
            if (BaseDeDatos == 1){
                stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            } else if (BaseDeDatos == 2){
                stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<OrdenesDAO> SELECCIONAROrdenes(){

        ObservableList<OrdenesDAO> listaO = FXCollections.observableArrayList();
        OrdenesDAO objO;
        String query = "SELECT * FROM ordenes ORDER BY id_orden";
        try {
            if (BaseDeDatos == 1){
                stmt = Conexion.conexion.createStatement();
            } else if (BaseDeDatos == 2){
                stmt = Conexion.conexion.createStatement();
            }
            ResultSet res = stmt.executeQuery(query);
            while( res.next() ){
                objO = new OrdenesDAO();
                objO.setId_orden(res.getInt("id_orden"));
                objO.setMontoOrden(res.getInt("montoOrden"));
                objO.setFechaOrden(res.getString("fechaOrden"));
                objO.setIdEmpleado(res.getInt("idEmpleado"));
                objO.setIdCliente(res.getInt("idCliente"));
                listaO.add(objO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaO;
    }
}
