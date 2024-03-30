/**
 * Sample Skeleton for 'lenguaje1.fxml' Controller Class
 */
package control;

/**
 * Sample Skeleton for 'lenguaje1.fxml' Controller Class
 */
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelo.Lenguaje;

public class Controller1 {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cdActualizar"
    private Button cdActualizar; // Value injected by FXMLLoader

    @FXML // fx:id="cdLenguaje"
    private Button cdLenguaje; // Value injected by FXMLLoader

    @FXML // fx:id="cmdGuardar"
    private Button cmdGuardar; // Value injected by FXMLLoader

    @FXML // fx:id="cmdGuardarRegla"
    private Button cmdGuardarRegla; // Value injected by FXMLLoader

    @FXML // fx:id="lblCiclos"
    private Label lblCiclos; // Value injected by FXMLLoader

    @FXML // fx:id="lblInicial"
    private Label lblInicial; // Value injected by FXMLLoader

    @FXML // fx:id="lblNoTerminal"
    private Label lblNoTerminal; // Value injected by FXMLLoader

    @FXML // fx:id="lblPalabras"
    private Label lblPalabras; // Value injected by FXMLLoader

    @FXML // fx:id="lblTerminal"
    private Label lblTerminal; // Value injected by FXMLLoader

    @FXML // fx:id="lblTitulo"
    private Label lblTitulo; // Value injected by FXMLLoader

    @FXML // fx:id="lstNoTerminales"
    private ListView lstNoTerminales; // Value injected by FXMLLoader

    @FXML // fx:id="palabras"
    private TextArea palabras; // Value injected by FXMLLoader

    @FXML // fx:id="txtCiclos"
    private TextField txtCiclos; // Value injected by FXMLLoader

    @FXML // fx:id="txtInicial"
    private TextField txtInicial; // Value injected by FXMLLoader

    @FXML // fx:id="txtNoTerminales"
    private TextField txtNoTerminales; // Value injected by FXMLLoader

    @FXML // fx:id="txtPalabras"
    private TextField txtPalabras; // Value injected by FXMLLoader

    @FXML // fx:id="txtReglas"
    private TextField txtReglas; // Value injected by FXMLLoader

    @FXML // fx:id="txtTerminales"
    private TextField txtTerminales; // Value injected by FXMLLoader

    @FXML // fx:id="txtinalcanzables"
    private TextField txtinalcanzables; // Value injected by FXMLLoader

    @FXML // fx:id="txtinutiles"
    private TextField txtinutiles; // Value injected by FXMLLoader

    private HashMap<String, ArrayList<String>> mapa = new HashMap<>();
    private Lenguaje lenguaje;

    //--------------------------------------------------------------------------
    @FXML
    void Actualizar(ActionEvent event) {
        txtCiclos.setText("");
        txtInicial.setText("");
        txtNoTerminales.setText("");
        txtPalabras.setText("");
        txtTerminales.setText("");
        palabras.setText("");
        txtReglas.setText("");
        txtinalcanzables.setText("");
        txtinutiles.setText("");

    }

    //--------------------------------------------------------------------------
    @FXML
    void Guardar(ActionEvent event) {
        try {
            String inputNT = txtNoTerminales.getText();
            List<String> noTerminales = Arrays.asList(inputNT.split(" "));

            String inputT = txtTerminales.getText();
            List<String> terminales = Arrays.asList(inputT.split(" "));

            String inicial = txtInicial.getText().trim();
            if (!validarTxtInicial(inicial)) {
                mostrarAlerta("Por favor ingrese solo un valor no terminal en el campo 'Inicial'");
                txtInicial.clear(); // Limpiar el campo de texto para que el usuario pueda ingresar nuevamente
                return; // Salir del método después de mostrar la alerta y limpiar el campo de texto
            }

            int cantidad = Integer.parseInt(txtPalabras.getText());
            int max = Integer.parseInt(txtCiclos.getText());

            if (noTerminales.isEmpty() || terminales.isEmpty() || inicial.isEmpty() || cantidad <= 0 || max <= 0) {
                throw new IllegalArgumentException("Todos los campos son obligatorios");
            }

            lenguaje.setNoTerminales(noTerminales);
            lenguaje.setTerminales(terminales);
            lenguaje.setInicial(inicial);
            lenguaje.setCantidad(cantidad);
            lenguaje.setMax(max);

            mostrarNoTerminales();
            actualizarGUI();
        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor ingrese valores numéricos válidos en los campos 'Palabras' y 'Ciclos'");
        } catch (IllegalArgumentException e) {
            mostrarAlerta(e.getMessage());
        }
    }

    //--------------------------------------------------------------------------
    public void mostrarNoTerminales() {
        ObservableList<String> items = FXCollections.observableArrayList(lenguaje.getNoTerminales());

        lstNoTerminales.getItems().clear();
        lstNoTerminales.setItems(items);
        //actualizarGUI();
    }

    //--------------------------------------------------------------------------
    @FXML
    void GuardarRegla(ActionEvent event) {
        try {
            String elementoSeleccionado = (String) lstNoTerminales.getSelectionModel().getSelectedItem();

            String inputNT = txtReglas.getText();
            ArrayList<String> reglas = new ArrayList<>(Arrays.asList(inputNT.split(" ")));

            if (lenguaje.reglasConTerminal(reglas)) {
                if (mapa.containsKey(elementoSeleccionado)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Actualizar regla");
                    alert.setHeaderText(null);
                    alert.setContentText("Ya se ha guardado una regla para '" + elementoSeleccionado + "'. ¿Desea actualizarla?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent()) {
                        if (result.get() == ButtonType.OK) {
                            mapa.put(elementoSeleccionado, reglas);
                            lenguaje.setTr(mapa);
                            mostrarAlerta("Regla actualizada con éxito para '" + elementoSeleccionado + "'");
                        }
                    } else {
                        return; // Si el usuario cierra la ventana de confirmación sin seleccionar OK ni Cancelar, salimos del método sin hacer nada
                    }
                } else {
                    mapa.put(elementoSeleccionado, reglas);
                    lenguaje.setTr(mapa);
                    mostrarAlerta("Regla guardada con éxito para '" + elementoSeleccionado + "'");
                }
            } else {
                throw new IllegalArgumentException("Las reglas deben incluir al menos un terminal");
            }

            txtReglas.clear();
        } catch (NullPointerException e) {
            mostrarAlerta("Por favor seleccione un elemento de la lista de no terminales");
        } catch (IllegalArgumentException e) {
            mostrarAlerta(e.getMessage());
        }
    }

    //--------------------------------------------------------------------------
    @FXML
    void abrirVentana2(ActionEvent event) {
        try {
            List<String> listaPalabras = lenguaje.generadorDePalabras(lenguaje.getMax(), lenguaje.getCantidad());
            StringBuilder sb = new StringBuilder();
            for (String palabra : listaPalabras) {
                sb.append(palabra).append("\n");
            }
            String textoPalabras = sb.toString().trim();
            palabras.setText(textoPalabras);

            enviarInalcazables();
            enviarInutiles();
        } catch (Exception e) {
            mostrarAlerta("Ha ocurrido un error al generar las palabras: " + e.getMessage());
        }
    }

    //--------------------------------------------------------------------------
    public void enviarInalcazables() {
        try {
            List<String> listaElementos = lenguaje.inalcanzable();
            StringBuilder in = new StringBuilder();
            for (String elemento : listaElementos) {
                in.append(elemento).append(" ");
            }
            String inal = in.toString().trim();
            txtinalcanzables.setText(inal);
        } catch (Exception e) {
            mostrarAlerta("Ha ocurrido un error al determinar los elementos inalcanzables: " + e.getMessage());
        }
    }

    //--------------------------------------------------------------------------
    public void enviarInutiles() {
        try {
            List<String> listaElementos = lenguaje.variableInutil();

            StringBuilder in = new StringBuilder();
            for (String elemento : listaElementos) {
                in.append(elemento).append(" ");
            }
            String inal = in.toString().trim();
            txtinutiles.setText(inal);
        } catch (Exception e) {
            mostrarAlerta("Ha ocurrido un error al determinar los elementos inútiles: " + e.getMessage());
        }
    }

    //--------------------------------------------------------------------------
    void actualizarGUI() {
        txtNoTerminales.setText(String.valueOf(this.lenguaje.getNoTerminales()));
        txtTerminales.setText(String.valueOf(this.lenguaje.getTerminales()));
        txtInicial.setText(String.valueOf(this.lenguaje.getInicial()));
        txtPalabras.setText(Integer.toString(this.lenguaje.getCantidad()));
        txtCiclos.setText(Integer.toString(this.lenguaje.getMax()));

    }

    //--------------------------------------------------------------------------
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //--------------------------------------------------------------------------
    private boolean validarTxtInicial(String inicial) {

        if (inicial.isEmpty()) {
            return true;
        }

        String[] valores = inicial.split("\\s+");
        if (valores.length > 1) {
            return false;
        }
        if (lenguaje.esNoTerminal(inicial)) {
            return true;
        }

        return true;
    }

    //--------------------------------------------------------------------------
    private void validarTxtNoTerminal(TextField textField) {
        String texto = textField.getText();

        if (texto.isEmpty()) {
            return;
        }
        boolean esMayuscula = texto.equals(texto.toUpperCase());
        boolean tieneSeparador = texto.contains(" ");

        if (!esMayuscula && !tieneSeparador) {
            // Datos no están en mayúscula y no están separados
            mostrarAlerta("Por favor ingrese los datos en mayúscula y separados por espacios");
        } else if (!esMayuscula) {
            // Datos no están en mayúscula
            mostrarAlerta("Por favor ingrese los datos en mayúscula");
        } else if (!tieneSeparador) {
            // Datos no están separados
            mostrarAlerta("Por favor ingrese los datos separados por espacios");
        }
    }

    //--------------------------------------------------------------------------
    private void validarTxtTerminal(TextField textField) {
        String texto = textField.getText();
        if (texto.isEmpty()) {

            return;
        }
        boolean esMinuscula = texto.equals(texto.toLowerCase());
        boolean tieneSeparador = texto.contains(" ");

        if (!esMinuscula && !tieneSeparador) {
            // Datos no están en minúsculas y no están separados
            mostrarAlerta("Por favor ingrese los datos en minúscula y separados por espacios");
        } else if (!esMinuscula) {
            // Datos no están en minúsculas
            mostrarAlerta("Por favor ingrese los datos en minúscula");
        } else if (!tieneSeparador) {
            // Datos no están separados
            mostrarAlerta("Por favor ingrese los datos separados por espacios");
        }
    }

    //--------------------------------------------------------------------------
    private void validarTxtInicial(TextField textField) {
        String texto = textField.getText().trim();

        if (texto.isEmpty()) {
            return;
        }
        String[] valores = texto.split("\\s+");
        if (valores.length > 1) {
            mostrarAlerta("Por favor ingrese solo un valor no terminal");
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmdGuardarRegla != null : "fx:id=\"cmdGuardarRegla\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert cdActualizar != null : "fx:id=\"cdActualizar\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert cdLenguaje != null : "fx:id=\"cdLenguaje\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert cmdGuardar != null : "fx:id=\"cmdGuardar\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert lblCiclos != null : "fx:id=\"lblCiclos\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert lblInicial != null : "fx:id=\"lblInicial\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert lblNoTerminal != null : "fx:id=\"lblNoTerminal\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert lblPalabras != null : "fx:id=\"lblPalabras\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert lblTerminal != null : "fx:id=\"lblTerminal\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert lblTitulo != null : "fx:id=\"lblTitulo\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert lstNoTerminales != null : "fx:id=\"lstNoTerminales\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert palabras != null : "fx:id=\"palabras\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert txtCiclos != null : "fx:id=\"txtCiclos\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert txtInicial != null : "fx:id=\"txtInicial\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert txtNoTerminales != null : "fx:id=\"txtNoTerminales\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert txtPalabras != null : "fx:id=\"txtPalabras\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert txtReglas != null : "fx:id=\"txtReglas\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert txtTerminales != null : "fx:id=\"txtTerminales\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert txtinalcanzables != null : "fx:id=\"txtinalcanzables\" was not injected: check your FXML file 'lenguaje1.fxml'.";
        assert txtinutiles != null : "fx:id=\"txtinutiles\" was not injected: check your FXML file 'lenguaje1.fxml'.";

        lenguaje = new Lenguaje();

        if (txtNoTerminales != null) {
            txtNoTerminales.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) {
                    validarTxtNoTerminal(txtNoTerminales);
                }
            });
        } else {
            System.err.println("Error: txtNoTerminal no se ha inicializado correctamente.");
        }

        if (txtTerminales != null) {
            txtTerminales.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) {
                    validarTxtTerminal(txtTerminales);
                }
            });
        } else {
            System.err.println("Error: txtTerminal no se ha inicializado correctamente.");
        }

        if (txtInicial != null) {
            txtInicial.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) {
                    validarTxtInicial(txtInicial);
                }
            });
        } else {
            System.err.println("Error: txtInicial no se ha inicializado correctamente.");
        }

    }

}
