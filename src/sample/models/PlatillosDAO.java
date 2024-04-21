package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.Main.BaseDeDatos;

public class PlatillosDAO {

    private int idPlatillo;
    private String nombreplatillo;
    private double costo;
    private String tamanio;
    private double precio;
    private Statement stmt;

    public int getIdPlatillo() {return idPlatillo;}

    public void setIdPlatillo(int idPlatillo) {this.idPlatillo = idPlatillo;}

    public String getNombreplatillo() {return nombreplatillo;}

    public void setNombreplatillo(String nombreplatillo) {this.nombreplatillo = nombreplatillo;}

    public double getCosto() {return costo;}

    public void setCosto(double costo) {this.costo = costo;}

    public String getTamanio() {return tamanio;}

    public void setTamanio(String tamanio) {this.tamanio = tamanio;}

    public double getPrecio() {return precio;}

    public void setPrecio(double precio) {this.precio = precio;}

    public void INSERTARPlatilos(){
        String query = "INSERT INTO platillos (nombreplatillo, costo, tamanio, precio) " +
                "VALUES('"+this.nombreplatillo+"',"+this.costo+",'"+this.tamanio+"',"+this.precio+")";
        try {
            if (BaseDeDatos == 1 || BaseDeDatos == 2){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZARPlatillos(){
        String query = "UPDATE platillos SET nombreplatillo='"+this.nombreplatillo+"', costo="+this.costo+"," +
                " tamanio='"+this.tamanio+"', precio="+this.precio+"  WHERE idPlatillo = "+this.idPlatillo;
        try {
            if (BaseDeDatos == 1 || BaseDeDatos == 2){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINARPlatillos(){
        String query = "DELETE FROM platillos WHERE idPlatillo = "+this.idPlatillo;
        try {
            if (BaseDeDatos == 1 || BaseDeDatos == 2){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<PlatillosDAO> SELECCIONARPlatillos(){
        ObservableList<PlatillosDAO> listaPl = FXCollections.observableArrayList();
        PlatillosDAO objC;
        String query = "SELECT * FROM platillos ORDER BY nombreplatillo";
        try {
            if (BaseDeDatos == 1 || BaseDeDatos == 2){
                stmt = Conexion.conexion.createStatement();
                ResultSet res = stmt.executeQuery(query);
                while( res.next() ){
                    objC = new PlatillosDAO();
                    objC.setIdPlatillo(res.getInt("idPlatillo"));
                    objC.setNombreplatillo(res.getString("nombreplatillo"));
                    objC.setCosto(res.getDouble("costo"));
                    objC.setTamanio(res.getString("tamanio"));
                    objC.setPrecio(res.getDouble("precio"));
                    listaPl.add(objC);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPl;
    }

    // Método para seleccionar un platillo por su ID
    public PlatillosDAO SELECCIONARBYID(int id){
        PlatillosDAO platillo = null;
        String query = "SELECT * FROM platillos WHERE idPlatillo = " + id;
        try {
            if (BaseDeDatos == 1 || BaseDeDatos == 2){
                stmt = Conexion.conexion.createStatement();
                ResultSet res = stmt.executeQuery(query);
                if (res.next()) {
                    platillo = new PlatillosDAO();
                    platillo.setIdPlatillo(res.getInt("idPlatillo"));
                    platillo.setNombreplatillo(res.getString("nombreplatillo"));
                    platillo.setCosto(res.getDouble("costo"));
                    platillo.setTamanio(res.getString("tamanio"));
                    platillo.setPrecio(res.getDouble("precio"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platillo;
    }
}
