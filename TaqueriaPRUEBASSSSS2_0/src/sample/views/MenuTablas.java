package sample.views;

/*
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.OrdenDAO;
import sample.models.PedidoDAO;
import sample.models.PlatosDAO;


import java.sql.*;


public class MenuTablas extends Stage {

    private Scene escena;
    private VBox vBox;
    private GridPane gridPane;
    private Button btnusuarios, btnEmpleados, btnOrdenes, btnPlatillos, btnInsumos, btnMesass;
    private Button[] btnMesas;
    private Label lblTotal;
    private Button btnPedido;
    private TableView<OrdenDAO> tbvOrdenes;
    private TableView<OrdenDAO> tblOrdenes;
    private int mesaSeleccionada;
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
            LimpiarGridPane();
            LimpiarVBox();
            MostrarBotonesTablas();
        });

        menuTablas.getItems().add(itemMenuTablas);

        // Crear menú para mesas
        Menu menuMesas = new Menu();
        menuMesas.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/4001/4001039.png", "Mesas"));


        MenuItem itemMenuMesas = new MenuItem("Mostrar Mesas");
        itemMenuMesas.setOnAction(event -> {
            LimpiarGridPane();
            LimpiarVBox();
            MostrarBotonesMesas();
        });

        menuMesas.getItems().add(itemMenuMesas);

        // Crear menú para categorías
        Menu menuCategorias = new Menu();
        menuCategorias.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/1357/1357589.png", "Categorías"));

        MenuItem itemMenuCategorias = new MenuItem("Mostrar Categorias");
        itemMenuCategorias.setOnAction(event -> {
            LimpiarGridPane();
            LimpiarVBox();
            MostrarBotonesCategorias();
        });

        menuCategorias.getItems().add(itemMenuCategorias);

        Menu menuTelefonos = new Menu();
        menuTelefonos.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/5828/5828548.png", "Teléfonos"));

        MenuItem itemMenuTelefonos = new MenuItem("Mostrar Teléfonos");
        itemMenuTelefonos.setOnAction(event -> {
        });

        menuTelefonos.getItems().add(itemMenuTelefonos);

        // Crear menú para caja
        Menu menuCaja = new Menu();
        menuCaja.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/4689/4689889.png", "Caja"));

        MenuItem itemMenuCaja = new MenuItem("Mostrar Caja");
        itemMenuCaja.setOnAction(event -> {
            LimpiarGridPane();
            LimpiarVBox();
            MostrarBotonesCaja();
        });

        menuCaja.getItems().add(itemMenuCaja);


        Menu menuPedido = new Menu();
        menuPedido.setGraphic(createMenuIconWithText("https://cdn-icons-png.flaticon.com/128/6384/6384868.png", "Pedido"));

        MenuItem itemMenuPedido = new MenuItem("Mostrar Pedido");
        itemMenuPedido.setOnAction(event -> {
            LimpiarGridPane();
            LimpiarVBox();
           MostrarBotonesPedido();
        });

        menuPedido.getItems().add(itemMenuPedido);

        menuBar.getMenus().addAll(menuTablas, menuMesas, menuCategorias, menuTelefonos, menuCaja, menuPedido);

        vBox = new VBox();
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        // Agregar elementos al VBox
        vBox.getChildren().addAll(menuBar, gridPane);
        escena = new Scene(vBox, 750, 380);
    }
    private void MostrarBotonesTablas() {
        LimpiarGridPane();

        btnusuarios = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/6009/6009864.png", "Tabla Usuarios");
        btnEmpleados = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1830/1830878.png", "Tabla Empleados");
        btnOrdenes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/2082/2082194.png", "Tabla Ordenes");
        btnPlatillos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1046/1046849.png", "Tabla MENU");
        btnInsumos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/512/917/917940.png", "Tabla Pedidos");
        btnMesass = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/673/673281.png", "Tabla Mesas");


        btnusuarios.setOnAction(event -> new UsuarioTaqueria());
        btnEmpleados.setOnAction(action -> new EmpleadoTaqueria());
        btnOrdenes.setOnAction(action -> new OrdenTaqueria());
        btnPlatillos.setOnAction(action -> new PlatosTaqueria());
         btnInsumos.setOnAction(action -> new PedidoTaqueria());
        btnMesass.setOnAction(action -> new MesasTaqueria());


        gridPane.addRow(0, btnusuarios, btnEmpleados, btnPlatillos);
        gridPane.addRow(1,  btnMesass, btnOrdenes, btnInsumos);


        for (Node child : gridPane.getChildren()) {
            if (child instanceof Button) {
                GridPane.setHalignment(child, HPos.RIGHT);
            }
        }
    }

    private void MostrarBotonesCategorias() {
        LimpiarGridPane();
        // Botones para categorías
        Button btnBebidas = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1113/1113278.png", "Bebidas");
        btnBebidas.setOnAction(event -> {
            MostrarBebidas();
        });
        Button btnDesayunos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/887/887359.png", "Desayunos");
        btnDesayunos.setOnAction(event -> {
            MostrarDesayunos();
        });
        Button btnBocadillos = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/5508/5508486.png", "Bocadillos");
        btnBocadillos.setOnAction(event -> {
            MostrarBocadillos();
        });
        Button btnGuarniciones = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/985/985478.png", "Guarniciones");
        btnGuarniciones.setOnAction(event -> {
            MostrarGuarniciones();
        });
        Button btnCafes = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/7438/7438571.png", "Cafes");
        btnCafes.setOnAction(event -> {
            MostrarCafes();
        });
        Button btnPostres = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/5347/5347946.png", "Postres");
        btnPostres.setOnAction(event -> {
            MostrarPostres();
        });
        Button btnSnacksTapas = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/10164/10164488.png", "Snacks/Tapas");
        btnSnacksTapas.setOnAction(event -> {
            MostrarSnacksTapas();
        });
        Button btnPlatillo = createButtonWithIcon("https://cdn-icons-png.flaticon.com/128/1886/1886807.png", "Platillos");
        btnPlatillo.setOnAction(event -> {
            MostrarPlatillos();
        });


        // Agregar botones al GridPane
        gridPane.addRow(0, btnBebidas, btnDesayunos, btnBocadillos, btnGuarniciones);
        gridPane.addRow(1, btnCafes, btnPostres, btnSnacksTapas, btnPlatillo);
    }

    private void MostrarBotonesMesas() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mesas");

            // Botones para mesas
            btnMesas = new Button[8];
            int count = 0;

            while (rs.next() && count < 8) {
                int id = rs.getInt("id");
                int numeroMesa = rs.getInt("numeroMesa");
                String estado = rs.getString("estado");


                Button btnMesa = new Button("Mesa " + numeroMesa);
                btnMesa.setOnAction(event -> {

                    if (btnMesa.getStyle().equals("-fx-background-color: green")) {
                        btnMesa.setStyle("-fx-background-color: red");
                        ActualizarEstadoMesa(id, "Ocupada");
                        mesaSeleccionada = numeroMesa; // Almacenar el número de la mesa
                        MostrarBotonesCaja();
                    } else {
                        btnMesa.setStyle("-fx-background-color: green");
                        ActualizarEstadoMesa(id, "Libre");
                    }
                });


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

    private void ActualizarEstadoMesa(int id, String estado) {
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE mesas SET estado = ? WHERE id = ?");

            pstmt.setString(1, estado);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();


            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void LimpiarVBox() {
        if (vBox.getChildren().contains(lblTotal)) {
            vBox.getChildren().remove(lblTotal);
        }
        if (vBox.getChildren().contains(btnPedido)) {
            vBox.getChildren().remove(btnPedido);
        }
    }
    private void MostrarBotonesCaja() {
        LimpiarGridPane();


        TableView<OrdenDAO> tblOrdenes = crearTablaOrdenes();


        TableView<PlatosDAO> tblPlatos = crearTablaPlatos(tblOrdenes);

        gridPane.add(tblPlatos, 0, 0, 1, 2);
        gridPane.add(tblOrdenes, 1, 0, 1, 2);

        // Inicializar el Label del total
        lblTotal = new Label();
        vBox.getChildren().add(lblTotal);

        // Inicializar el botón de pedido
        btnPedido = new Button("Realizar pedido");
        btnPedido.setStyle("-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #ffffff; " +
                "-fx-background-color: #4CAF50; " +
                "-fx-padding: 10px; " +
                "-fx-border-radius: 5px;");
        vBox.getChildren().add(btnPedido);

        btnPedido.setOnAction(event -> {

            ObservableList<OrdenDAO> listaOrdenes = tblOrdenes.getItems();


            for (OrdenDAO orden : listaOrdenes) {

                PedidoDAO pedido = new PedidoDAO();
                pedido.setNombre(orden.getNombre());
                pedido.setPrecio(orden.getPrecio());
                pedido.setCantidad(orden.getCantidad());
                pedido.setComentario(orden.getComentario());
                pedido.setId_plato(orden.getId_plato());


                pedido.INSERTAR();
            }

            OrdenDAO ordenDAO = new OrdenDAO();


            ObservableList<OrdenDAO> nuevaListaOrdenes = FXCollections.observableArrayList();


            tblOrdenes.setItems(nuevaListaOrdenes);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pedido");
            alert.setHeaderText(null);
            alert.setContentText("Pedido realizado y almacenado en la tabla de pedidos");
            alert.showAndWait();


            actualizarTablaOrdenes(tblOrdenes);
        });


        actualizarTabla();
    }


    public void actualizarTabla() {
        OrdenDAO ordenDAO = new OrdenDAO();
        float total = ordenDAO.calcularTotal();


        if (lblTotal != null && vBox.getChildren().contains(lblTotal)) {
            // Si lblTotal ya existe, eliminarlo del layout
            vBox.getChildren().remove(lblTotal);
        }


        lblTotal = new Label("Total a pagar: " + total);


        lblTotal.setStyle("-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #ff0000; " +
                "-fx-background-color: #ffff00; " +
                "-fx-padding: 10px; " +
                "-fx-border-color: #0000ff; " +
                "-fx-border-width: 5px; " +
                "-fx-border-radius: 10px;");


        vBox.getChildren().add(lblTotal);
    }
   private TableView<PlatosDAO> crearTablaPlatos(TableView<OrdenDAO> tblOrdenes) {
        TableView<PlatosDAO> tblPlatos = new TableView<>();
        TableColumn<PlatosDAO, Integer> tbcId = new TableColumn<>("ID");
        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<PlatosDAO, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<PlatosDAO, Double> tbcPrecio = new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        PlatosDAO platosDAO = new PlatosDAO();
        ObservableList<PlatosDAO> listaPlatos = platosDAO.SELECCIONAR();


        tblPlatos.setItems(listaPlatos);
        tblPlatos.getColumns().addAll(tbcId, tbcNombre, tbcPrecio);


        tblPlatos.setRowFactory(tv -> {
            TableRow<PlatosDAO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()==MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    PlatosDAO clickedRow = row.getItem();

                    agregarPlatoAOrden(clickedRow, tblOrdenes);
                }
            });
            return row ;
        });
        return tblPlatos;
    }

    private TableView<OrdenDAO> crearTablaOrdenes() {
        TableColumn<OrdenDAO, Integer> tbcId = new TableColumn<>("ID");
        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<OrdenDAO, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<OrdenDAO, Double> tbcPrecio = new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<OrdenDAO, Integer> tbcCantidad = new TableColumn<>("Cantidad");
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<OrdenDAO, Double> tbcSubtotal = new TableColumn<>("Subtotal");
        tbcSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        TableColumn<OrdenDAO, String> tbcComentario = new TableColumn<>("Comentario");
        tbcComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));

        TableColumn<OrdenDAO, Integer> tbcIdPlato = new TableColumn<>("ID Plato");
        tbcIdPlato.setCellValueFactory(new PropertyValueFactory<>("id_plato"));

        TableView<OrdenDAO> tablaOrdenes = new TableView<>();
        tablaOrdenes.getColumns().add(tbcId);
        tablaOrdenes.getColumns().add(tbcNombre);
        tablaOrdenes.getColumns().add(tbcPrecio);
        tablaOrdenes.getColumns().add(tbcCantidad);
        tablaOrdenes.getColumns().add(tbcSubtotal);
        tablaOrdenes.getColumns().add(tbcComentario);
        tablaOrdenes.getColumns().add(tbcIdPlato);

        return tablaOrdenes;
    }
    private void agregarPlatoAOrden(PlatosDAO plato, TableView<OrdenDAO> tblOrdenes) {
        OrdenDAO orden = new OrdenDAO();
        orden.setNombre(plato.getNombre());
        orden.setPrecio((float) plato.getPrecio());
        orden.setCantidad(1);
        orden.setId_plato(plato.getId());
        orden.INSERTAR();
        actualizarTablaOrdenes(tblOrdenes);
        actualizarTabla();
    }
    private void actualizarTablaOrdenes(TableView<OrdenDAO> tblOrdenes) {

        OrdenDAO ordenDAO = new OrdenDAO();
        ObservableList<OrdenDAO> listaOrdenes = ordenDAO.SELECCIONAR();


        tblOrdenes.setItems(listaOrdenes);
    }
    private void MostrarBotonesPedido() {
        LimpiarGridPane();


        TableView<PedidoDAO> tblPedidos = crearTablaPedidos();


        gridPane.add(tblPedidos, 0, 0);

    }

        private TableView<PedidoDAO> crearTablaPedidos() {

           // private TableView<PedidoDAO> crearTablaPedidos (TableView < OrdenDAO > tblOrdenes) {
                TableView<PedidoDAO> tblPedidos = new TableView<>();
                TableColumn<PedidoDAO, Integer> tbcIdPedido = new TableColumn<>("Pedido (ID)");
                tbcIdPedido.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<PedidoDAO, String> tbcNombre = new TableColumn<>("Nombre");
                tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

                TableColumn<PedidoDAO, Double> tbcPrecio = new TableColumn<>("Precio");
                tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

                TableColumn<PedidoDAO, Integer> tbcCantidad = new TableColumn<>("Cantidad");
                tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

                TableColumn<PedidoDAO, String> tbcComentario = new TableColumn<>("Comentario");
                tbcComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));

                TableColumn<PedidoDAO, Integer> tbcIdPlato = new TableColumn<>("ID Plato");
                tbcIdPlato.setCellValueFactory(new PropertyValueFactory<>("id_plato"));

                // Crear una instancia de PedidoDAO y obtener la lista de pedidos
                PedidoDAO pedidoDAO = new PedidoDAO();
                ObservableList<PedidoDAO> listaPedidos = pedidoDAO.SELECCIONAR();

                // Añadir los pedidos a la tabla
                tblPedidos.setItems(listaPedidos);
                tblPedidos.getColumns().addAll(tbcIdPedido, tbcNombre, tbcPrecio, tbcCantidad, tbcComentario, tbcIdPlato);
                return tblPedidos;
            }

    private double calcularTotal() {

        return 0.0;
    }


    private void LimpiarGridPane() {
        gridPane.getChildren().clear();
    }

    private Button createButtonWithIcon(String iconUrl, String buttonText) {
        Button button = new Button();
        ImageView imageView = new ImageView(new Image(iconUrl));
        imageView.setFitWidth(55); // Ajustar el ancho del icono
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
        imageView.setFitWidth(55); // Ajustar el ancho del icono
        imageView.setPreserveRatio(true); // Mantener la relación de aspecto del icono
        Label label = new Label(text);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(imageView, label);
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        return hbox;
    }


    private void MostrarDesayunos() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, categoria FROM platos WHERE categoria = 'desayuno'");

            // Botones para los desayunos
            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String iconoURL = IconManager.getIconURL(nombre, categoria); // Obtener la URL del icono correspondiente

                Button btnBebida = createButtonWithIcon(iconoURL, nombre);
                btnBebida.setOnAction(event -> {

                });


                gridPane.add(btnBebida, col, row);


                col++;
                if (col == 4) {
                    col = 0;
                    row++;
                }
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void MostrarBocadillos() {
        LimpiarGridPane();
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, categoria FROM platos WHERE categoria = 'bocadillo'");


            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String iconoURL = IconManager.getIconURL(nombre, categoria); // Obtener la URL del icono correspondiente

                Button btnBebida = createButtonWithIcon(iconoURL, nombre);
                btnBebida.setOnAction(event -> {

                });


                gridPane.add(btnBebida, col, row);


                col++;
                if (col == 4) {
                    col = 0;
                    row++;
                }
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void MostrarGuarniciones() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, categoria FROM platos WHERE categoria = 'guarnicion'");


            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String iconoURL = IconManager.getIconURL(nombre, categoria); // Obtener la URL del icono correspondiente

                Button btnBebida = createButtonWithIcon(iconoURL, nombre);
                btnBebida.setOnAction(event -> {

                });


                gridPane.add(btnBebida, col, row);


                col++;
                if (col == 4) {
                    col = 0;
                    row++;
                }
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void MostrarCafes() {
        LimpiarGridPane();
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, categoria FROM platos WHERE categoria = 'cafe'");


            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String iconoURL = IconManager.getIconURL(nombre, categoria); // Obtener la URL del icono correspondiente

                Button btnBebida = createButtonWithIcon(iconoURL, nombre);
                btnBebida.setOnAction(event -> {

                });


                gridPane.add(btnBebida, col, row);

                // Actualizar posición en el GridPane
                col++;
                if (col == 4) {
                    col = 0;
                    row++;
                }
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void MostrarPostres() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, categoria FROM platos WHERE categoria = 'postre'");

            // Botones para los postres
            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String iconoURL = IconManager.getIconURL(nombre, categoria); // Obtener la URL del icono correspondiente

                Button btnBebida = createButtonWithIcon(iconoURL, nombre);
                btnBebida.setOnAction(event -> {

                });

                // Agregar botón de bebida al GridPane
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
    private void MostrarSnacksTapas() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, categoria FROM platos WHERE categoria = 'snackTapas'");

            // Botones para los snacks o tapas
            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String iconoURL = IconManager.getIconURL(nombre, categoria); // Obtener la URL del icono correspondiente

                Button btnBebida = createButtonWithIcon(iconoURL, nombre);
                btnBebida.setOnAction(event -> {

                });

                // Agregar botón de bebida al GridPane
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

    private void MostrarBebidas() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, categoria FROM platos WHERE categoria = 'bebida'");

            // Botones para las bebidas
            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String iconoURL = IconManager.getIconURL(nombre, categoria); // Obtener la URL del icono correspondiente

                Button btnBebida = createButtonWithIcon(iconoURL, nombre);
                btnBebida.setOnAction(event -> {

                });

                // Agregar botón de bebida al GridPane
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

    private void MostrarPlatillos() {
        LimpiarGridPane();
        try {
            // Conexión con la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, categoria FROM platos WHERE categoria = 'platillos'");

            // Botones para los platillos
            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String iconoURL = IconManager.getIconURL(nombre, categoria); // Obtener la URL del icono correspondiente

                Button btnBebida = createButtonWithIcon(iconoURL, nombre);
                btnBebida.setOnAction(event -> {

                });

                // Agregar botón de bebida al GridPane
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

}
*/


import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import java.sql.*;

public class MenuTablas {
    private GridPane gridPane;

    private void LimpiarGridPane() {
        gridPane.getChildren().clear();
    }

    private Button createButtonWithIcon(String iconUrl, String buttonText) {
        Button button = new Button();
        ImageView imageView = new ImageView(new Image(iconUrl));
        imageView.setFitWidth(55); // Ajustar el ancho del icono
        imageView.setPreserveRatio(true); // Mantener la relación de aspecto del icono
        Label label = new Label(buttonText);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(imageView, label);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        button.setGraphic(vbox);
        return button;
    }

    private void MostrarDesayunos() {
        LimpiarGridPane();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, categoria FROM platos WHERE categoria = 'desayuno'");

            int row = 0;
            int col = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                String iconoURL = IconManager.getIconURL(nombre, categoria);

                insertarCategoriaEnOrden(categoria);

                Button btnBebida = createButtonWithIcon(iconoURL, nombre);
                btnBebida.setOnAction(event -> {
                    // Aquí puedes agregar lógica adicional al hacer clic en el botón
                });

                gridPane.add(btnBebida, col, row);

                col++;
                if (col == 4) {
                    col = 0;
                    row++;
                }
            }

            rs.close();
            stmt.close();
            conn.close();

            mostrarBotonesCaja();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos similares para otras categorías...

    private void insertarCategoriaEnOrden(String categoria) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taqueria2", "adminTacos2", "123");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO orden (categoria) VALUES (?)");
            pstmt.setString(1, categoria);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrarBotonesCaja() {
        // Aquí implementa la lógica para mostrar los botones en la caja
    }
}





