package javafx11;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AvlFx extends Pane{
	
	private Tree<Integer> tree = new Tree<>();
    private double radius = 20; 
    private double vGap = 55; 

    AvlFx(Tree<Integer> tree) {
        this.tree = tree;
        setStatus("Arbol vacio");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayAvlFx() {
        this.getChildren().clear(); 
        if (tree.getRoot() != null) {
            displayAvlFx(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }

 
    private void displayAvlFx(Avl.Node<Integer> root, double x, double y, double hGap) {
        if (root.left != null) {
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayAvlFx(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null) {
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayAvlFx(root.right, x + hGap, y + vGap, hGap / 2);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.ORANGE);
        circle.setStroke(Color.PINK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }

}
