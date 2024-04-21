package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.Main.*;

public class ClientesDAO {

    private int idCliente;
    private String nombreCliente;
    private String telefonoCliente;
    private String direccionCliente;
    private Statement stmt;

    public int getIdCliente() { return idCliente; }

    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombreCliente() { return nombreCliente; }

    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getTelefonoCliente() { return telefonoCliente; }

    public void setTelefonoCliente(String telefonoCliente) { this.telefonoCliente = telefonoCliente; }

    public String getDireccionCliente() { return direccionCliente; }

    public void setDireccionCliente(String direccionCliente) { this.direccionCliente = direccionCliente; }

    public void INSERTAR(){
        String query = "INSERT INTO cliente (nombreCliente, telefonoCliente, direccionCliente) " +
                "VALUES('"+this.nombreCliente+"','"+this.telefonoCliente+"','"+this.direccionCliente+"')";
        try {
            if (BaseDeDatos == 1 || BaseDeDatos == 2){
                Statement stmt = Conexion.conexion.createStatement();
                // Ejecutar la consulta de inserción
                stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                // Obtener el ID generado para el nuevo cliente
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    this.idCliente = rs.getInt(1); // Obtener el ID generado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void ACTUALIZAR(){
        String query = "UPDATE cliente SET nombreCliente='"+this.nombreCliente+"', telefonoCliente='"+this.telefonoCliente+"'," +
                "direccionCliente='"+this.direccionCliente+"' WHERE idCliente = "+this.idCliente;
        try {
            if (BaseDeDatos == 1){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            } else if (BaseDeDatos == 2){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR(){
        String query = "DELETE FROM cliente WHERE idCliente = "+this.idCliente;
        try {
            if (BaseDeDatos == 1){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            } else if (BaseDeDatos == 2){
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ClientesDAO> SELECCIONAR(){

        ObservableList<ClientesDAO> listaClientes = FXCollections.observableArrayList();
        ClientesDAO objCliente;
        String query = "SELECT * FROM cliente ORDER BY nombreCliente";
        try {
            if (BaseDeDatos == 1){
                stmt = Conexion.conexion.createStatement();
            } else if (BaseDeDatos == 2){
                stmt = Conexion.conexion.createStatement();
            }
            ResultSet res = stmt.executeQuery(query);
            while( res.next() ){
                objCliente = new ClientesDAO();
                objCliente.setIdCliente(res.getInt("idCliente"));
                objCliente.setNombreCliente(res.getString("nombreCliente"));
                objCliente.setTelefonoCliente(res.getString("telefonoCliente"));
                objCliente.setDireccionCliente(res.getString("direccionCliente"));
                listaClientes.add(objCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

    public void seleccionarPorId() {
        // Aquí iría el código para seleccionar un cliente por su id
    }
}