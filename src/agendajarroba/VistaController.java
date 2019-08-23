package agendajarroba;

import Persona.Persona;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Richard
 */
public class VistaController implements Initializable {

    // Declaramos los botones
    @FXML private Button aniadirBT;
    @FXML private Button modificarBT;
    @FXML private Button eliminarBT;
    @FXML private Button nuevoBT;
    //dfhjgj
    
    // Declaramos los textfileds
    @FXML private TextField nombreTF;
    @FXML private TextField apellidoTF;
    @FXML private TextField edadTF;
    @FXML private TextField telefonoTF;
    
    // Declaramos la tabla y las columnas
    @FXML private TableView<Persona> tablaPersonas;
    @FXML private TableColumn nombreCL;
    @FXML private TableColumn apellidoCL;
    @FXML private TableColumn edadCL;
    @FXML private TableColumn telefonoCL;
    
    ObservableList<Persona> personas;
    
    private int posicionPersonaEnTabla;

    /**
     * Método que realiza las acciones tras pulsar el boton "Nuevo"
     *
     * @param event
     */
    @FXML private void nuevo(ActionEvent event) {
        nombreTF.setText("");
        apellidoTF.setText("");
        edadTF.setText("");
        telefonoTF.setText("");
        modificarBT.setDisable(true);
        eliminarBT.setDisable(true);
        aniadirBT.setDisable(false);
    }

    /**
     * Método que realiza las acciones tras pulsar el boton "Añadir"
     *
     * @param event
     */
    @FXML private void aniadir(ActionEvent event) {
        Persona persona = new Persona();
        persona.setNombre(nombreTF.getText());
        persona.setApellido(apellidoTF.getText());
        persona.setEdad(Integer.parseInt(edadTF.getText()));
        persona.setTelefono(telefonoTF.getText());
        personas.add(persona);
    }

    /**
     * Método que realiza las acciones tras pulsar el boton "Modificar"
     *
     * @param event
     */
    @FXML private void modificar(ActionEvent event) {
        Persona persona = new Persona();
        persona.setNombre(nombreTF.getText());
        persona.setApellido(apellidoTF.getText());
        persona.setEdad(Integer.parseInt(edadTF.getText()));
        persona.setTelefono(telefonoTF.getText());
        personas.set(posicionPersonaEnTabla, persona);
    }

    /**
     * Método que realiza las acciones tras pulsar el boton "Eliminar"
     *
     * @param event
     */
    @FXML private void eliminar(ActionEvent event) {
        personas.remove(posicionPersonaEnTabla);
    }
    /**
     * Listener de la tabla personas
     */
    private final ListChangeListener<Persona> selectorTablaPersonas =
            new ListChangeListener<Persona>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Persona> c) {
                    ponerPersonaSeleccionada();
                }
            };

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public Persona getTablaPersonasSeleccionada() {
        if (tablaPersonas != null) {
            List<Persona> tabla = tablaPersonas.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Persona competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void ponerPersonaSeleccionada() {
//        final Persona persona = getTablaPersonasSeleccionada();
//        posicionPersonaEnTabla = personas.indexOf(persona);
//
//        if (persona != null) {
//
//            // Pongo los textFields con los datos correspondientes
//            nombreTF.setText(persona.getNombre());
//            apellidoTF.setText(persona.getApellido());
//            edadTF.setText(persona.getEdad().toString());
//            telefonoTF.setText(persona.getTelefono());
//
//            // Pongo los botones en su estado correspondiente
//            modificarBT.setDisable(false);
//            eliminarBT.setDisable(false);
//            aniadirBT.setDisable(true);
//
//        }
    }

    /**
     * Método para inicializar la tabla
     */
    private void inicializarTablaPersonas() {
        nombreCL.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        apellidoCL.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));
        edadCL.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
        telefonoCL.setCellValueFactory(new PropertyValueFactory<Persona, String>("telefono"));

        personas = FXCollections.observableArrayList();
        tablaPersonas.setItems(personas);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Inicializamos la tabla
        this.inicializarTablaPersonas();

        // Ponemos estos dos botones para que no se puedan seleccionar
        modificarBT.setDisable(true);
        eliminarBT.setDisable(true);

        // Seleccionar las tuplas de la tabla de las personas
        final ObservableList<Persona> tablaPersonaSel = tablaPersonas.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaPersonas);

        // Inicializamos la tabla con algunos datos aleatorios
        for (int i = 0; i < 20; i++) {
            Persona p1 = new Persona();
            p1.setNombre("Nombre " + i);
            p1.setApellido("Apellido " + i);
            p1.setEdad(20 + i);
            p1.setTelefono("67589458" + i);
            personas.add(p1);
        }
    }
}
