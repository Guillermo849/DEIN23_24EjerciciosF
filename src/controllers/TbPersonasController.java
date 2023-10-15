package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;

import javafx.beans.property.SimpleStringProperty;

import javafx.collections.ObservableList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import model.Persona;



import javafx.event.ActionEvent;

public class TbPersonasController implements Initializable{

    @FXML
    private Button btnAgregarPersona;
    
    @FXML
    private Button btnModificar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<Persona> tbViewPersonas;

    @FXML
    private TableColumn<Persona, String> tbColNombre;

    @FXML
    private TableColumn<Persona, String> tbColApellidos;

    @FXML
    private TableColumn<Persona, Integer> tbColEdad;
    
    @FXML
    private Button btnImportar;

    @FXML
    private Button btnExportar;

    @FXML
    private TextField tfFiltroNombre;
    
    private NuevaPersonaController newPersonaWindow;
    
    private static Image ICONO = new Image(Main.class.getResourceAsStream("/img/agenda.png"));
    
    private int personaIndex = -1;
    
    private ObservableList<Persona> originalLstPersona;
    
    @FXML
    void selectPersona(MouseEvent event) {
    	if (tbViewPersonas.getSelectionModel().getSelectedItem() != null) {
    		personaIndex = tbViewPersonas.getSelectionModel().getSelectedIndex();
    	}
    }
    
    @FXML
    void aniadirPersona(ActionEvent event) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DatosPersonasAgregar.fxml"));
			Parent root = loader.load();
			/* Le dice a la nueva ventana cual es su ventana padre */
			newPersonaWindow = loader.getController();
			newPersonaWindow.setParent(this, null);
			
			Stage agregarStage = new Stage();
			agregarStage.setScene(new Scene(root));
			agregarStage.setResizable(false);
			agregarStage.getIcons().add(ICONO);
			agregarStage.setTitle("Nueva Persona");
			agregarStage.showAndWait();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    
    @FXML
    void modificarPersona(ActionEvent event) {
    	if (personaIndex > -1) {
	    	try {
	    		
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DatosPersonasAgregar.fxml"));
				Parent root = loader.load();
				newPersonaWindow = loader.getController();
				newPersonaWindow.setParent(this, tbViewPersonas.getItems().get(personaIndex));
				
				Stage agregarStage = new Stage();
				agregarStage.setScene(new Scene(root));
				agregarStage.getIcons().add(ICONO);
				agregarStage.setTitle("Modificar Persona");
				agregarStage.showAndWait();
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
    	}
    }
    
    /* Elimina la persona seleccionada*/
    @FXML
    void eliminarPersona(ActionEvent event) {
    	if (personaIndex > -1) {
    		tbViewPersonas.getItems().remove(personaIndex);
    	}
    }

    /*
     * Añade la informacion de la ventana DatosPersonasAgregarController a la tabla
     * */
    public void devolverPersonaNueva(Persona person) {
    	originalLstPersona.add(person);
        tbViewPersonas.setItems(originalLstPersona);
    }
    
    /*
     * Añadirá la persona modificada a la tabla
     * */
    public void devolverPersonaMod(Persona person) {
    	tbViewPersonas.getItems().set(personaIndex, person);
    	originalLstPersona = tbViewPersonas.getItems();
    }
    
    
    @FXML
    void filtrarPorNombre(ActionEvent event) {
    	
    }

    @FXML
    void importarTabla(ActionEvent event) {

    }
    
    @FXML
    void exportarTabla(ActionEvent event) {

    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		originalLstPersona = tbViewPersonas.getItems();
		
		tbColNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        
		tbColApellidos.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido()));
        
		tbColEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
	}

}

