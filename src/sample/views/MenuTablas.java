package sample.views;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class MenuTablas extends Stage {

    private Scene escena;
    private VBox vBox;
    private GridPane gridPane;
    private Button btnClientes, btnEmpleados, btnOrdenes, btnPlatillos, btnDetPlatillo, btnDetOrden, btnInsumos;
    private Button[] btnMesas;
    private String usuarioAdmin = "adminTacos";
    private String contraseniaAdmin = "123";
    public MenuTablas() {
        CrearUI();
        this.setTitle("Menu De La Taqueria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        MenuBar menuBar = new MenuBar();

        // Crear menú de tablas
        Menu menuTablas = new Menu();
        menuTablas.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/1085/1085805.png", "Administración"));

        MenuItem itemMenuTablas = new MenuItem("Mostrar Tablas");
        itemMenuTablas.setOnAction(event -> {
            mostrarVentanaLogin();
            // MostrarBotonesTablas();
        });

        menuTablas.getItems().add(itemMenuTablas);

        // Crear menú para mesas
        Menu menuMesas = new Menu();
        menuMesas.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/4001/4001039.png", "Mesas"));

        MenuItem itemMenuMesas = new MenuItem("Mostrar Mesas");
        itemMenuMesas.setOnAction(event -> {
            MostrarBotonesMesas();
        });

        menuMesas.getItems().add(itemMenuMesas);

        // Crear menú para categorías
        Menu menuCategorias = new Menu();
        menuCategorias.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/1357/1357589.png", "Categorías"));

        MenuItem itemMenuCategorias = new MenuItem("Mostrar Categorias");
        itemMenuCategorias.setOnAction(event -> {
            MostrarBotonesCategorias();
        });

        menuCategorias.getItems().add(itemMenuCategorias);

        // Agregar menús a la barra de menú
        menuBar.getMenus().addAll(menuTablas, menuMesas, menuCategorias);

        vBox = new VBox();
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        // Agregar elementos al VBox
        vBox.getChildren().addAll(menuBar, gridPane);
        escena = new Scene(vBox, 420, 300);
    }
    private void mostrarVentanaLogin() {
        Stage ventanaLogin = new Stage();
        ventanaLogin.setTitle("Inicio de sesión");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        TextField usuarioField = new TextField();
        usuarioField.setPromptText("Usuario");
        PasswordField contraseniaField = new PasswordField();
        contraseniaField.setPromptText("Contraseña");

        Button btnIniciarSesion = new Button("Iniciar sesión");
        btnIniciarSesion.setOnAction(event -> {
            String usuario = usuarioField.getText();
            String contrasenia = contraseniaField.getText();
            if (usuario.equals(usuarioAdmin) && contrasenia.equals(contraseniaAdmin)) {
                ventanaLogin.close();
                MostrarBotonesTablas(); // Mostrar los botones de administración
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de inicio de sesión");
                alert.setHeaderText(null);
                alert.setContentText("Usuario o contraseña incorrectos.");
                alert.showAndWait();
            }
        });

        grid.add(new Label("Usuario:"), 0, 0);
        grid.add(usuarioField, 1, 0);
        grid.add(new Label("Contraseña:"), 0, 1);
        grid.add(contraseniaField, 1, 1);
        grid.add(btnIniciarSesion, 1, 2);

        Scene scene = new Scene(grid, 300, 150);
        ventanaLogin.setScene(scene);
        ventanaLogin.show();
    }
    private void MostrarBotonesTablas() {
        LimpiarGridPane();
        // Botones para las tablas
        btnClientes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/6009/6009864.png", "Tabla Clientes");
        btnEmpleados = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1830/1830878.png", "Tabla Empleados");
        btnOrdenes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/2082/2082194.png", "Tabla Ordenes");
        btnPlatillos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1046/1046849.png", "Tabla Platillos");
        btnDetOrden = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1980/1980997.png", "Tabla Detalle De Orden");
        btnDetPlatillo = createButtonWithIcon("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjChLaLhPfSopC_TH5lyN5YviE9BqPrmYLhTfyXIxIl5eRNAWU", "Tabla Detalle De Platillos");
        btnInsumos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/917/917940.png", "Tabla Bebidas");

        // Asignar acciones a los botones
        btnClientes.setOnAction(event -> new ClienteTaqueria());
        btnEmpleados.setOnAction(action -> new EmpleadoTaqueria());
        btnOrdenes.setOnAction(action -> new OrdenesTaqueria());
        btnPlatillos.setOnAction(action -> new PlatilloTaqueria());
        btnDetOrden.setOnAction(action -> new DetallesOrdenTaqueria());
        btnDetPlatillo.setOnAction(action -> new DetallesPlatilloTaqueria());
        btnInsumos.setOnAction(action -> new BebidasTaqueria());

        // Agregar botones al GridPane
        gridPane.addRow(0, btnClientes, btnEmpleados, btnOrdenes);
        gridPane.addRow(1, btnPlatillos, btnDetOrden, btnInsumos);
        gridPane.add(btnDetPlatillo, 0, 2, 3, 1);
    }
    private void MostrarBotonesCategorias() {
        LimpiarGridPane();
        // Botones para categorías
        Button btnBebidas = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1113/1113278.png", "Bebidas");
        btnBebidas.setOnAction(event -> {
            MostrarBebidas();
        });
        Button btnDesayunos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/887/887359.png", "Desayunos");
        Button btnBocadillos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/5508/5508486.png", "Bocadillos");
        Button btnGuarniciones = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/11790/11790114.png", "Guarniciones");
        Button btnCafes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/924/924514.png", "Cafes");
        Button btnPostres = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/5347/5347946.png", "Postres");
        Button btnComidas = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1886/1886807.png", "Comidas");
        Button btnSnacksTapas = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/10164/10164488.png", "Snacks/Tapas");

        // Agregar botones al GridPane
        gridPane.addRow(0, btnBebidas, btnDesayunos, btnBocadillos, btnGuarniciones);
        gridPane.addRow(1, btnCafes, btnPostres, btnComidas, btnSnacksTapas);
    }
    private void MostrarBebidas() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria", "adminTacos", "1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nombreBebida FROM bebidas");

            // Botones para categorías
            int row = 0;
            int col = 0;
            while (rs.next()) {
                String nombreBebida = rs.getString("nombreBebida");
                String iconoURL = IconManager.getIconURL(nombreBebida); // Obtener la URL del icono correspondiente
                Button btnBebida = createButtonWithIcon(iconoURL, nombreBebida);
                btnBebida.setOnAction(event -> {
                    // Acción al hacer clic en la bebida
                    // Por ejemplo, mostrar información detallada de la bebida
                    System.out.println(nombreBebida + " seleccionada");
                });

                // Agregar botón al GridPane
                gridPane.add(btnBebida, col, row);

                // Actualizar posición en el GridPane
                col++;
                if (col == 4) {
                    col = 0;
                    row++;
                }
            }

            // Cerrar la conexión con la base de datos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/*
    private void MostrarBebidas() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria", "adminTacos", "1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idBebida, nombreBebida FROM bebidas");

            // Botones para categorías
            int row = 0;
            int col = 0;
            while (rs.next()) {
                int idBebida = rs.getInt("idBebida");
                String nombreBebida = rs.getString("nombreBebida");
                Button btnBebida = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1113/1113278.png", nombreBebida);
                btnBebida.setOnAction(event -> {
                    // Acción al hacer clic en la bebida
                    // Por ejemplo, mostrar información detallada de la bebida
                    System.out.println(nombreBebida + " seleccionada");
                });

                // Agregar botón al GridPane
                gridPane.add(btnBebida, col, row);

                // Actualizar posición en el GridPane
                col++;
                if (col == 4) {
                    col = 0;
                    row++;
                }
            }

            // Cerrar la conexión con la base de datos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    private void MostrarBotonesMesas() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria", "adminTacos", "1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mesas");

            // Botones para mesas
            btnMesas = new Button[8];
            int count = 0;

            while (rs.next() && count < 8) {
                int idMesa = rs.getInt("idMesa");
                int numeroMesa = rs.getInt("numeroMesa");
                int capacidad = rs.getInt("capacidad");
                String estado = rs.getString("estado");

                // Crear imagen del icono de mesa
                ImageView imageView = new ImageView(new Image("https://cdn-icons-png.flaticon.com/128/1237/1237886.png"));
                imageView.setFitWidth(40);
                imageView.setPreserveRatio(true);

                // Crear botón de mesa
                Button btnMesa = new Button("Mesa " + numeroMesa, imageView);
                btnMesa.setOnAction(event -> {
                    // Acción al hacer clic en la mesa
                    // Por ejemplo, mostrar información detallada de la mesa
                    System.out.println("Mesa " + numeroMesa + " seleccionada");
                });

                // Establecer el color del botón según el estado de la mesa
                if (estado.equals("Ocupada")) {
                    btnMesa.setStyle("-fx-background-color: red");
                } else {
                    btnMesa.setStyle("-fx-background-color: green");
                }

                btnMesas[count] = btnMesa;
                count++;
            }

            // Agregar botones de mesas al GridPane
            gridPane.addRow(0, btnMesas[0], btnMesas[1], btnMesas[2], btnMesas[3]);
            gridPane.addRow(1, btnMesas[4], btnMesas[5], btnMesas[6], btnMesas[7]);

            // Cerrar la conexión con la base de datos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void LimpiarGridPane() {
        gridPane.getChildren().clear();
    }

    private Button createButtonWithIcon(String iconUrl, String buttonText) {
        Button button = new Button();
        ImageView imageView = new ImageView(new Image(iconUrl));
        imageView.setFitWidth(40); // Ajustar el ancho del icono
        imageView.setPreserveRatio(true); // Mantener la relación de aspecto del icono
        Label label = new Label(buttonText);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(imageView, label);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        button.setGraphic(vbox);
        return button;
    }

    private HBox createMenuIconWithText(String iconUrl, String text) {
        ImageView imageView = new ImageView(new Image(iconUrl));
        imageView.setFitWidth(40); // Ajustar el ancho del icono
        imageView.setPreserveRatio(true); // Mantener la relación de aspecto del icono
        Label label = new Label(text);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(imageView, label);
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        return hbox;
    }
}


/*
package sample.views;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class MenuTablas extends Stage {

    private Scene escena;
    private VBox vBox;
    private GridPane gridPane;
    private Button btnClientes, btnEmpleados, btnOrdenes, btnPlatillos, btnDetPlatillo, btnDetOrden, btnInsumos;
    private Button[] btnMesas;

    public MenuTablas() {
        CrearUI();
        this.setTitle("Menu De La Taqueria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        MenuBar menuBar = new MenuBar();

        // Crear menú de tablas
        Menu menuTablas = new Menu();
        menuTablas.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/1085/1085805.png", "Administración"));

        MenuItem itemMenuTablas = new MenuItem("Mostrar Tablas");
        itemMenuTablas.setOnAction(event -> {
            MostrarBotonesTablas();
        });

        menuTablas.getItems().add(itemMenuTablas);

        // Crear menú para mesas
        Menu menuMesas = new Menu();
        menuMesas.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/4001/4001039.png", "Mesas"));

        MenuItem itemMenuMesas = new MenuItem("Mostrar Mesas");
        itemMenuMesas.setOnAction(event -> {
            MostrarBotonesMesas();
        });

        menuMesas.getItems().add(itemMenuMesas);

        // Crear menú para categorías
        Menu menuCategorias = new Menu();
        menuCategorias.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/1357/1357589.png", "Categorías"));

        MenuItem itemMenuCategorias = new MenuItem("Mostrar Categorias");
        itemMenuCategorias.setOnAction(event -> {
            MostrarBotonesCategorias();
        });

        menuCategorias.getItems().add(itemMenuCategorias);

        // Agregar menús a la barra de menú
        menuBar.getMenus().addAll(menuTablas, menuMesas, menuCategorias);

        vBox = new VBox();
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        // Agregar elementos al VBox
        vBox.getChildren().addAll(menuBar, gridPane);
        escena = new Scene(vBox, 420, 300);
    }

    private void MostrarBotonesTablas() {
        // Botones para las tablas
        btnClientes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/6009/6009864.png", "Tabla Clientes");
        btnEmpleados = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1830/1830878.png", "Tabla Empleados");
        btnOrdenes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/2082/2082194.png", "Tabla Ordenes");
        btnPlatillos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1046/1046849.png", "Tabla Platillos");
        btnDetOrden = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1980/1980997.png", "Tabla Detalle De Orden");
        btnDetPlatillo = createButtonWithIcon("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjChLaLhPfSopC_TH5lyN5YviE9BqPrmYLhTfyXIxIl5eRNAWU", "Tabla Detalle De Platillos");
        btnInsumos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/917/917940.png", "Tabla Bebidas");

        // Asignar acciones a los botones
        btnClientes.setOnAction(event -> new ClienteTaqueria());
        btnEmpleados.setOnAction(action -> new EmpleadoTaqueria());
        btnOrdenes.setOnAction(action -> new OrdenesTaqueria());
        btnPlatillos.setOnAction(action -> new PlatilloTaqueria());
        btnDetOrden.setOnAction(action -> new DetallesOrdenTaqueria());
        btnDetPlatillo.setOnAction(action -> new DetallesPlatilloTaqueria());
        btnInsumos.setOnAction(action -> new BebidasTaqueria());

        // Agregar botones al GridPane
        gridPane.addRow(0, btnClientes, btnEmpleados, btnOrdenes);
        gridPane.addRow(1, btnPlatillos, btnDetOrden, btnInsumos);
        gridPane.add(btnDetPlatillo, 0, 2, 3, 1);
    }

    private void MostrarBotonesCategorias() {
        // Botones para categorías
        Button btnBebidas = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/1466/1466461.png", "Bebidas");
        Button btnDesayunos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/3490/3490427.png", "Desayunos");
        Button btnBocadillos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/2563/2563708.png", "Bocadillos");
        Button btnGuarniciones = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/504/504990.png", "Guarniciones");
        Button btnCafes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/1194/1194463.png", "Cafes");
        Button btnPostres = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/1072/1072077.png", "Postres");
        Button btnComidas = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/2625/2625261.png", "Comidas");
        Button btnSnacksTapas = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/2909/2909810.png", "Snacks/Tapas");

        // Agregar botones al GridPane
        gridPane.addRow(0, btnBebidas, btnDesayunos, btnBocadillos, btnGuarniciones);
        gridPane.addRow(1, btnCafes, btnPostres, btnComidas, btnSnacksTapas);
    }

    private void MostrarBotonesMesas() {
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_base_datos", "usuario", "contraseña");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mesas");

            // Botones para mesas
            btnMesas = new Button[8];
            int count = 0;

            while (rs.next() && count < 8) {
                int idMesa = rs.getInt("idMesa");
                int numeroMesa = rs.getInt("numeroMesa");
                int capacidad = rs.getInt("capacidad");
                String estado = rs.getString("estado");

                Button btnMesa = new Button("Mesa " + numeroMesa);
                btnMesa.setOnAction(event -> {
                    // Acción al hacer clic en la mesa
                    // Por ejemplo, mostrar información detallada de la mesa
                    System.out.println("Mesa " + numeroMesa + " seleccionada");
                });

                // Establecer el color del botón según el estado de la mesa
                if (estado.equals("Ocupada")) {
                    btnMesa.setStyle("-fx-background-color: red");
                } else {
                    btnMesa.setStyle("-fx-background-color: green");
                }

                btnMesas[count] = btnMesa;
                count++;
            }

            // Agregar botones de mesas al GridPane
            gridPane.addRow(0, btnMesas[0], btnMesas[1], btnMesas[2], btnMesas[3]);
            gridPane.addRow(1, btnMesas[4], btnMesas[5], btnMesas[6], btnMesas[7]);

            // Cerrar la conexión con la base de datos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Button createButtonWithIcon(String iconUrl, String buttonText) {
        Button button = new Button();
        ImageView imageView = new ImageView(new Image(iconUrl));
        imageView.setFitWidth(40); // Ajustar el ancho del icono
        imageView.setPreserveRatio(true); // Mantener la relación de aspecto del icono
        Label label = new Label(buttonText);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(imageView, label);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        button.setGraphic(vbox);
        return button;
    }

    private HBox createMenuIconWithText(String iconUrl, String text) {
        ImageView imageView = new ImageView(new Image(iconUrl));
        imageView.setFitWidth(40); // Ajustar el ancho del icono
        imageView.setPreserveRatio(true); // Mantener la relación de aspecto del icono
        Label label = new Label(text);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(imageView, label);
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        return hbox;
    }
}*/


/*SOLO LAS TABLAS DE ADMINISTRACION
package sample.views;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuTablas extends Stage {

    private Scene escena;
    private VBox vBox;
    private GridPane gridPane;
    private Button btnClientes, btnEmpleados, btnOrdenes, btnPlatillos, btnDetPlatillo, btnDetOrden, btnInsumos;

    public MenuTablas() {
        CrearUI();
        this.setTitle("Menu De La Taqueria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        MenuBar menuBar = new MenuBar();

        // Crear menú de tablas
        Menu menuTablas = new Menu();
        menuTablas.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/1085/1085805.png", "Administración"));

        MenuItem itemMenuTablas = new MenuItem("Mostrar Tablas");
        itemMenuTablas.setOnAction(event -> {
            MostrarBotonesTablas();
        });

        menuTablas.getItems().add(itemMenuTablas);

        // Crear menú para mesas
        Menu menuMesas = new Menu();
        menuMesas.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/4001/4001039.png", "Mesas"));

        MenuItem itemMenuMesas = new MenuItem("Mostrar Mesas");
        itemMenuMesas.setOnAction(event -> {
            // Agregar lógica para mostrar botones de mesas
        });

        menuMesas.getItems().add(itemMenuMesas);

        // Crear menú para categorías
        Menu menuCategorias = new Menu();
        menuCategorias.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/1357/1357589.png", "Categorías"));

        MenuItem itemMenuCategorias = new MenuItem("Mostrar Categorias");
        itemMenuCategorias.setOnAction(event -> {
            // Agregar lógica para mostrar botones de categorías
        });

        menuCategorias.getItems().add(itemMenuCategorias);

        // Agregar menús a la barra de menú
        menuBar.getMenus().addAll(menuTablas, menuMesas, menuCategorias);

        vBox = new VBox();
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        // Agregar elementos al VBox
        vBox.getChildren().addAll(menuBar, gridPane);
        escena = new Scene(vBox, 420, 300);
    }

    private void MostrarBotonesTablas() {
        // Botones para las tablas
        btnClientes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/6009/6009864.png", "Tabla Clientes");
        btnEmpleados = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1830/1830878.png", "Tabla Empleados");
        btnOrdenes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/2082/2082194.png", "Tabla Ordenes");
        btnPlatillos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1046/1046849.png", "Tabla Platillos");
        btnDetOrden = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1980/1980997.png", "Tabla Detalle De Orden");
        btnDetPlatillo = createButtonWithIcon("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjChLaLhPfSopC_TH5lyN5YviE9BqPrmYLhTfyXIxIl5eRNAWU", "Tabla Detalle De Platillos");
        btnInsumos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/917/917940.png", "Tabla Bebidas");

        // Asignar acciones a los botones
        btnClientes.setOnAction(event -> new ClienteTaqueria());
        btnEmpleados.setOnAction(action -> new EmpleadoTaqueria());
        btnOrdenes.setOnAction(action -> new OrdenesTaqueria());
        btnPlatillos.setOnAction(action -> new PlatilloTaqueria());
        btnDetOrden.setOnAction(action -> new DetallesOrdenTaqueria());
        btnDetPlatillo.setOnAction(action -> new DetallesPlatilloTaqueria());
        btnInsumos.setOnAction(action -> new BebidasTaqueria());

        // Agregar botones al GridPane
        gridPane.addRow(0, btnClientes, btnEmpleados, btnOrdenes);
        gridPane.addRow(1, btnPlatillos, btnDetOrden, btnInsumos);
        gridPane.add(btnDetPlatillo, 0, 2, 3, 1);
    }

    private Button createButtonWithIcon(String iconUrl, String buttonText) {
        Button button = new Button();
        ImageView imageView = new ImageView(new Image(iconUrl));
        imageView.setFitWidth(40); // Ajustar el ancho del icono
        imageView.setPreserveRatio(true); // Mantener la relación de aspecto del icono
        Label label = new Label(buttonText);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(imageView, label);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        button.setGraphic(vbox);
        return button;
    }

    private HBox createMenuIconWithText(String iconUrl, String text) {
        ImageView imageView = new ImageView(new Image(iconUrl));
        imageView.setFitWidth(40); // Ajustar el ancho del icono
        imageView.setPreserveRatio(true); // Mantener la relación de aspecto del icono
        Label label = new Label(text);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(imageView, label);
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        return hbox;
    }
}
*/

/* package sample.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuTablas extends Stage {

    private Scene escena;
    private VBox vBox;
    private HBox hBox1,hBox2,hBox3;
    private Button btnClientes,btnEmpleados,btnOrdenes,btnPlatillos,btnDetPlatillo,btnDetOrden,btnInsumos;

    public MenuTablas(){
        CrearUI();
        this.setTitle("Menu De La Taqueria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        btnClientes = new Button("Tabla Clientes");
        btnEmpleados = new Button("Tabla Empleados");
        btnOrdenes = new Button("Tabla Ordenes");
        btnPlatillos = new Button("Tabla Platillos");
        btnDetOrden = new Button("Tabla Detalle De Orden");
        btnDetPlatillo = new Button("Tabla Detalle De Platillos");
        btnInsumos = new Button("Tabla Bebidas");

        btnClientes.setOnAction(event -> new ClienteTaqueria());
        btnEmpleados.setOnAction(action -> new EmpleadoTaqueria());
        btnOrdenes.setOnAction(action -> new OrdenesTaqueria());
        btnPlatillos.setOnAction(action -> new PlatilloTaqueria());
        btnDetOrden.setOnAction(action -> new DetallesOrdenTaqueria() );
        btnDetPlatillo.setOnAction(action -> new DetallesPlatilloTaqueria() );
        btnInsumos.setOnAction(action -> new BebidasTaqueria() );

        vBox = new VBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(20);
        hBox1.setSpacing(20);
        hBox2.setSpacing(20);
        hBox3.setSpacing(20);
        hBox1.getChildren().addAll(btnClientes,btnEmpleados,btnOrdenes);
        hBox2.getChildren().addAll(btnPlatillos,btnDetOrden,btnInsumos);
        hBox3.getChildren().addAll(btnDetPlatillo);
        vBox.getChildren().addAll(hBox1,hBox2,hBox3);
        escena = new Scene(vBox,420,150);
    }
}
*/