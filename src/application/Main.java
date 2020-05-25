package application;
	
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;

import application.Tree.Node;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Main extends Application {
	
	Circle circle = new Circle();
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("AVL Tree - Final Project");
			primaryStage.getIcons().add(new Image("/Images/arbol.png"));
			
			//View 
			Tree<Integer> tree = new Tree<>();
			TreeFx treefx = new TreeFx(tree);
			treefx.setStyle("-fx-font: 14 roboto;");
			
			Avl<Integer> avl = new Avl<>();
			AvlFx avlfx = new AvlFx(avl);
			avlfx.setStyle("-fx-font: 14 roboto;");

			BorderPane pane = new BorderPane();
			pane.setCenter(treefx);
			treefx.setPrefWidth(300);
			pane.setRight(avlfx);
			avlfx.setPrefWidth(250);
			
			pane.setPrefWidth(250);
			BorderPane.setMargin(treefx, new Insets(10, 20, 10, 20));
			BorderPane.setMargin(avlfx, new Insets(10, 20, 10, 20));

			TextField text = new TextField();
	        text.setPrefColumnCount(2);
	        text.setPromptText("Ingresa un numero");
	        text.setStyle("-fx-font: 14 roboto;");
	        text.setAlignment(Pos.BASELINE_RIGHT);
	        


	        Image imageDecline = new Image(getClass().getResourceAsStream("/Images/add1.png"));
	        Button insertButton = new Button();
	        insertButton.setGraphic(new ImageView(imageDecline));
	        
	        Image imageDecline1 = new Image(getClass().getResourceAsStream("/Images/delete1.png"));
	        Button deleteButton = new Button();
	        deleteButton.setGraphic(new ImageView(imageDecline1));
	        
	    
	        
	        Button balanceButton = new Button("Balancear");
	        balanceButton.setStyle("-fx-font: 14 roboto;");
	        Button clearButton = new Button("Borrar Arbol");
	        clearButton.setStyle("-fx-font: 14 roboto;");
	        Button exitButton = new Button("Salir");
	        exitButton.setStyle("-fx-font: 14 roboto;");
			Button searchButton = new Button("Buscar");
			searchButton.setStyle("-fx-font: 14 roboto;");
	        
	        
	        
	        Label label = new Label("Ingresa un numero: ");
	        label.setStyle("-fx-font: 14 roboto;");
	        
	        VBox vBox = new VBox(20);
	        vBox.getChildren().addAll(label ,text, insertButton, deleteButton, searchButton, balanceButton, clearButton, exitButton);
	        vBox.setAlignment(Pos.CENTER);
	        pane.setLeft(vBox);
	        vBox.setAlignment(Pos.BASELINE_CENTER);
	        BorderPane.setMargin(vBox, new Insets(40, 10, 10, 10));
	               
	        Validation v = new Validation();
	        LinkedList<Integer> treeVal = new LinkedList<>();

	        insertButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	if (v.emptyTextField(text)) {
		                invalidKey(text, "�No se inserto ningun numero!");
		            } else {
		                try {
		                    int element = Integer.parseInt(text.getText());
		                    if (tree.search(element)) {
		                        treefx.displayTreeFx();
		                        treefx.setStatus(element + " Ya se encuentra en el arbol");
		                    } else {
		                        tree.insert(element); 
		                        treefx.displayTreeFx();
		                        treefx.setStatus(element + " Se inserto en el arbol");
		                        treeVal.add(element);
		                    }
		                    text.setText("");
		                    text.requestFocus();
		                } catch (NumberFormatException ex) {
		                    invalidKey(text, "�El numero debe ser un entero!");
		                }
		            }
	            }
	        });
	        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	if (v.emptyTextField(text)) {
		                invalidKey(text, "�No se inserto ningun numero!");
		            } else {
		                try {
		                    int element = Integer.parseInt(text.getText());
		                    if (!tree.search(element)) { 
		                        treefx.displayTreeFx();
		                        treefx.setStatus(element + " No se encuentra en el arbol");
		                    } else {
		                        tree.delete(element); 
		                        treefx.displayTreeFx();
		                        treefx.setStatus(element + " Se elimino del arbol");
		                        treeVal.remove(element);
		                        if (!avl.isEmpty()) { 
		                            if (avl.getSize() == 1) { 
		                                avl.clear();
		                            } else {
		                                avl.delete(element);
		                            }
		                            avlfx.displayAvlFx();
		                        }
		                        avl.delete(element); 
		                    }
		                    text.setText("");
		                } catch (NumberFormatException ex) {
		                    invalidKey(text, "El numero debe ser un entero!");
		                }
		            }
	            }
	        });
	        //MODIFICADO
	        searchButton.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        		public void handle(ActionEvent e) {	 
	        		if (v.emptyTextField(text)) {
		                invalidKey(text, "�No se inserto ningun numero!");
		            }  {
	        		try {
	                    int element = Integer.parseInt(text.getText());
	                    if (!tree.search(element)) {
	                    	treefx.displayTreeFx();
	                        treefx.setStatus(element + " no se encuentra en el arbol");
	                        
	                    } else {
	                    	tree.searchNode(element);
	                    	treefx.displayChangeColor();
	                        
	                        
	                    }
	                } catch (NumberFormatException ex) {
	                    invalidKey(text, "El numero debe ser un entero!");
	                }	
	        	}
	        }
	        }
	        	
	        		);
	        //MODIFICADO
	
	        
	        
	        
	        balanceButton.setOnAction(new EventHandler<ActionEvent>() {
	        	public void handle(ActionEvent e) {
	        		for (Integer i : treeVal) {
	                    avl.insert(i); 
	                }
	                avlfx.displayAvlFx();
	                if (treeVal.isEmpty()) {
	                    avlfx.setStatus("El arbol esta vacio"); 
	                } else {
	                    avlfx.setStatus("Arbol balanceado");
	                }
	        	}
	        });
	        
	        
	        clearButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				    public void handle(ActionEvent e) {
				        text.clear();
				        tree.clear();
				        avl.clear();
				        treeVal.clear();
				        treefx.displayTreeFx();
				        treefx.setStatus("Se elimino el Arbol binario");
				        avlfx.displayAvlFx();
				        avlfx.setStatus("Se elimino el Arbol avl");
				    }
				});

	        	        
	        
	        
	

	        exitButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
	                exit.setTitle("�Adios!");
	                exit.setContentText("�Estas seguro de que quieres salir?");
	                Optional<ButtonType> result = exit.showAndWait();
	                if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
	                    System.exit(0);
	                }
	            }
	        });   
			//End view
	        Scene scene = new Scene(pane,900,450);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void invalidKey(TextField element, String alertHeader) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(alertHeader);
        alert.setContentText("�Intentalo de nuevo!");
        element.requestFocus();
        alert.showAndWait();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
