package javafx11;
import java.util.LinkedList;


public class Tree<T extends Comparable <T>> {
	
	protected Node<T> root;
	protected int size = 0;
	
	//MODIFICADO//
	protected Node<T> cambio;
	//MODIFICADO
	
	public Tree() {
	}
	//Clase Nodo
	public static class Node<T extends Comparable<T>>{
		public T element;
		public Node<T> right;
		public Node<T> left;
		public int height;
		
		public Node(T element) {
			this.element = element;
		}
		public T getElement() {
			return element;
		}
		public void setElement(T element) {
			this.element = element;
		}
		public Node<T> getRight() {
			return right;
		}
		public void setRight(Node<T> right) {
			this.right = right;
		}
		public Node<T> getLeft() {
			return left;
		}
		public void setLeft(Node<T> left) {
			this.left = left;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
	}
	protected Node<T> createNewNode(T element){
		return new Node<>(element);
	}
	public Node<T> getRoot(){
		return root;
	}
	
	
	
	//MODIFICADO
	public Node<T> getCambio(){
		return cambio;
	}
	//MODIFICADO
	
	
	public int getSize() {
		return size;
	}
	public boolean isEmpty() {
		return getSize() == 0;
	}
	public void clear() {
		root = null;
		size = 0;
	}
	public boolean insert(T element) {
        if (root == null) {
            root = createNewNode(element); // Create a new root
        } else {
            // Locate the parent node
            Node<T> parent = null;
            Node<T> actual = root;
            while (actual != null) {
                if (element.compareTo(actual.element) < 0) {
                    parent = actual;
                    actual = actual.left;
                } else if (element.compareTo(actual.element) > 0) {
                    parent = actual;
                    actual = actual.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (element.compareTo(parent.element) < 0) {
                parent.left = createNewNode(element);
            } else {
                parent.right = createNewNode(element);
            }
        }
        size++;
        return true; // Element inserted
    }
	
	//MODIFICADO
	public void searchNode(T element) {
		cambio = createNewNode(element);
		
	}
	//MODIFICADO
	
	
	public boolean search(T element) {
       Node<T> actual = root;
        while (actual != null) {
            if (element.compareTo(actual.element) < 0) {
            	actual = actual.left;
            } else if (element.compareTo(actual.element) > 0) {
            	actual = actual.right;
            } else {
                return true;
            }
        }
        return false;
    }
	
	
	
	//Insertar elementos en arbol binario
	public void insertElement(T element) {
		Node<T> node= new Node<>(element);
		if(root==null) {
			root=node;
		}else {
			insertElementRec(root,node);
		}
	}
	public Node<T> insertElementRec(Node<T> root, Node<T> newNode){
		if(root==null) {
			return newNode;
		}else {
			if(newNode.getElement().compareTo(root.getElement())>0) {
				root.setRight(insertElementRec(root.getRight(),newNode));
			}else {
				root.setLeft(insertElementRec(root.getLeft(),newNode));
			}
		}
		size++;
		return root;
	}
	//Ordenamientos
	public void preOrder() {
		preOrderRec(root);		
	}
	public void preOrderRec(Node<T> node) {
		if(node!=null) {
			System.out.print(node.getElement().toString()+", ");
			preOrderRec(node.getLeft());
			preOrderRec(node.getRight());
		}
	}
	public void inOrder() {
		inOrderRec(root);
	}
	private void inOrderRec(Node<T> node) {
		if(node!=null){
			inOrderRec(node.getLeft());
			System.out.print(node.getElement().toString()+", ");
			inOrderRec(node.getRight());
		}
	}	
	public void posOrder() {
		posOrderRec(root);
	}
	private void posOrderRec(Node<T> node) {
		if(node!=null){
			posOrderRec(node.getLeft());
			posOrderRec(node.getRight());
			System.out.print(node.getElement().toString()+", ");
		}	
	}
	
	
	
	
	
	
	//Eliminar un elemento del arbol binario
	public boolean delete(T element) {
        Node<T> node = null;
        Node<T> temp = root;
        while (temp != null) {
            if (element.compareTo(temp.element)< 0) {
                node = temp;
                temp = temp.left;
            }else if (element.compareTo(temp.element)> 0) {
                node = temp;
                temp = temp.right;
            }else {
                break;
            }
        }if(temp == null) {
            return false;
        }if (temp.left == null) {
            if (node == null) {
                root = temp.right;
            }else {
                if(element.compareTo(node.element) < 0) {
                    node.left = temp.right;
                }else {
                    node.right = temp.right;
                }
            }
        } else {
            Node<T> nodeDerechaMaxima = temp;
            Node<T> derechaMaxima = temp.left;

            while (derechaMaxima.right != null) {
            	nodeDerechaMaxima = derechaMaxima;
                derechaMaxima = derechaMaxima.right; 
            }
            temp.element = derechaMaxima.element;
            if (nodeDerechaMaxima.right == derechaMaxima) {
            	nodeDerechaMaxima.right = derechaMaxima.left;
            } else {
            	nodeDerechaMaxima.left = derechaMaxima.left;
            }
        }
        size--;
        return true;
	}	
	
	public LinkedList<Node<T>> path(T e) {

        LinkedList<Node<T>> list = new LinkedList<>();
        Node<T> actual = root; 

        while (actual != null) {
            list.add(actual); 					// Agrega el nodo a la lista
            if (e.compareTo(actual.element) < 0) {
            	actual = actual.left;
            } else if (e.compareTo(actual.element) > 0) {
            	actual = actual.right;
            } else {
                break;
            }
        }
        return list; 							// Regresa arreglo de nodos
    }
	
	
	

	
	
	

}

