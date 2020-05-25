package javafx11;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class TreeFx extends Pane{
	
	private Tree<Integer> tree = new Tree<>();
	private double radius = 20;
    private double vGap = 50;
    
    public TreeFx(Tree<Integer> tree) {
    	this.tree = tree;
    	setStatus("Árbol vacio");
    }
    public void setStatus(String msg) {
    	getChildren().add(new Text(20,20,msg));
    }
    public void displayTreeFx() {
        this.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTreeFx(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }
    private void displayTreeFx(Tree.Node<Integer> root, double x, double y, double hGap) {
    	if(root.left != null) {
    		getChildren().add(new Line(x -hGap, y + vGap, x, y));
    		displayTreeFx(root.left, x - hGap, y + vGap, hGap / 2);
    	}
    	if(root.right != null) {
    		getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTreeFx(root.right, x + hGap, y + vGap, hGap / 2);
    	}
    	Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.PURPLE);
        circle.setStroke(Color.PINK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }
    
    //MODIFICADO
    public void displayChangeColor() {
        this.getChildren().clear();
        if (tree.getCambio() != null) {
            displayTreeFx(tree.getCambio(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }
   
    
    public void displayChangeColor(Tree.Node<Integer> cambio, double x, double y, double hGap){
    	
    	
    	Circle circle = new Circle(x, y, radius);
    	circle.setFill(Color.GREEN);
        circle.setStroke(Color.GREEN);
        
        if (tree.getCambio() != null) {
            displayTreeFx(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }
    //MODIFICADO
    
 
    
	
	

}
