package ControladorVistas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ControladorEntidades.TareaJpaController;
import ControladorEntidades.exceptions.NonexistentEntityException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.Tarea;

/**
 *
 * @author Oscar Rodenas
 */
public class ControladorPrincipal implements Initializable {
   
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_borrar;
    @FXML
    private Button btn_actualizar;
    @FXML
    private TableView<Tarea> tabla;
    @FXML
    private TableColumn Col_contador;
    @FXML
    private TableColumn Col_nombre;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_horas;
 
    TareaJpaController ControladorTarea = new TareaJpaController();
    private ObservableList<Tarea> tareas;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.Col_nombre.setCellValueFactory(new PropertyValueFactory<Tarea, String>("nombre"));
        this.Col_contador.setCellValueFactory(new PropertyValueFactory<Tarea, Integer>("contador"));
        
        tareas = FXCollections.observableArrayList();
        tareas.addAll(ControladorTarea.findTareaEntities());
        tabla.setItems(tareas);
    }   
    
        @FXML
    private void seleccionar(MouseEvent event) {
       
        Tarea t = tabla.getSelectionModel().getSelectedItem();
        if( t != null ){
            this.txt_nombre.setText(t.getNombre());
            this.txt_horas.setText(Integer.toString(t.getContador()));

        }

    }
    
    @FXML
    private void crear(ActionEvent event) {
        
        String  nom = txt_nombre.getText();
        Integer hor = Integer.parseInt(txt_horas.getText());
        Tarea t = new Tarea(nom, hor);
        ControladorTarea.create(t);
        
        tareas = FXCollections.observableArrayList();
        tareas.addAll(ControladorTarea.findTareaEntities());
        tabla.setItems(tareas);
    }
    
    @FXML
    private void buscar(ActionEvent event) {
         
        String  nom = txt_nombre.getText();
        Tarea t;
        t = ControladorTarea.buscarPorNombre(nom);
        this.txt_horas.setText(Integer.toString(t.getContador()));

        
      
    }

   

    @FXML
    private void actualizar(ActionEvent event) {
 
        Tarea t = tabla.getSelectionModel().getSelectedItem();
        if( t != null ){
                    t.setNombre(txt_nombre.getText());
                    t.setContador(Integer.parseInt(txt_horas.getText()));
                  
            try {
                ControladorTarea.edit(t);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txt_nombre.clear();
        txt_horas.clear();
               
        tareas = FXCollections.observableArrayList();
        tareas.addAll(ControladorTarea.findTareaEntities());
        tabla.refresh();
                

    }
    
     @FXML
    private void borrar(ActionEvent event) {
     
        Tarea t = tabla.getSelectionModel().getSelectedItem();
        if( t != null ){
            try {
                ControladorTarea.destroy(t.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txt_nombre.clear();
        tareas = FXCollections.observableArrayList();
        tareas.addAll(ControladorTarea.findTareaEntities());
        tabla.setItems(tareas);
       
    }

}
