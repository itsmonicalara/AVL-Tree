package application;

public class Avl<T extends Comparable<T>> extends Tree<T> {

	public Avl() {
	}
	
	
	protected AvlNode<T> createNewNode(T element){
		return new AvlNode<T>(element);
	}
	protected static class AvlNode<T extends Comparable<T>> extends Tree.Node<T>{
		int height = 0;
		
		public AvlNode(T element) {
			super(element);
		}
	}
	public boolean insert(T element) {
        boolean sePudo = super.insert(element);
        if (!sePudo) {
            return false; 
        } else {
            balanceo(element);  
        }
        return true; 
    }
	private void alturaVariable(AvlNode<T> node) {
        if (node.left == null && node.right == null) 
        {
            node.height = 0;
        } else if (node.left == null) 
        {
            node.height = 1 + ((AvlNode<T>) (node.right)).height;
        } else if (node.right == null) 
        {
            node.height = 1 + ((AvlNode<T>) (node.left)).height;
        } else {
            node.height = 1
                    + Math.max(((AvlNode<T>) (node.right)).height,
                    ((AvlNode<T>) (node.left)).height);
        }
    }
	private void balanceo(T element) {
		
        java.util.LinkedList<Node<T>> path = path(element);
        for (int i = path.size() - 1; i >= 0; i--) {
            AvlNode<T> A = (AvlNode<T>) (path.get(i));
            alturaVariable(A);
            AvlNode<T> padreDeA = (A == root) ? null
                    : (AvlNode<T>) (path.get(i - 1));

            switch (factorDeBalanceo(A)) {
                case -2:
                    if (factorDeBalanceo((AvlNode<T>) A.left) <= 0) {
                        balanceLL(A, padreDeA); // Rotación a la izquierda
                    } else {
                        balanceLR(A, padreDeA); // Rotación de izquierda a derecha
                    }
                    break;
                case +2:
                    if (factorDeBalanceo((AvlNode<T>) A.right) >= 0) {
                        balanceRR(A, padreDeA); // Rotación a la derecha
                    } else {
                        balanceRL(A, padreDeA); // Rotación de derecha a izquierda
                    }
            }
        }
    }
	private int factorDeBalanceo(AvlNode<T> node) {
        if (node.right == null)  
        {
            return -node.height;
        } else if (node.left == null)
        {
            return +node.height;
        } else {
            return ((AvlNode<T>) node.right).height
                    - ((AvlNode<T>) node.left).height;
        }
    }
			// Balanceo a la izquierda\\
	
	private void balanceLL(Node<T> A, Node<T> padreDeA) {
        Node<T> B = A.left; // A y B pesados a la izquierda 
        if (A == root) {
            root = B;
        } else {
            if (padreDeA.left == A) {
            	padreDeA.left = B;
            } else {
            	padreDeA.right = B;
            }
        }

        A.left = B.right; 						// Hace los sub arboles de A
        B.right = A; 							// Hace A el hijo izquierdo de B
        alturaVariable((AvlNode<T>) A);
        alturaVariable((AvlNode<T>) B);
    }

	
			// Balanceo left-right\\

    private void balanceLR(Node<T> A, Node<T> padreDeA) {
        Node<T> B = A.left; // A es pesado a la izquierda
        Node<T> C = B.right; // B es pesado a la derecha

        if (A == root) {
            root = C;
        } else {
            if (padreDeA.left == A) {
            	padreDeA.left = C;
            } else {
            	padreDeA.right = C;
            }
        }

        A.left = C.right; // Hace los sub arboles izquierdos de A 
        B.right = C.left; // Hace los sub arboles derechos de B
        C.left = B;
        C.right = A;

        // Ajuste de altura del tree
        alturaVariable((AvlNode<T>) A);
        alturaVariable((AvlNode<T>) B);
        alturaVariable((AvlNode<T>) C);
    }

			// Balanceo a la derecha\\

    private void balanceRR(Node<T> A, Node<T> padreDeA) {
    	Node<T> B = A.right; // A y B son pesados a la derecha

        if (A == root) {
            root = B;
        } else {
            if (padreDeA.left == A) {
            	padreDeA.left = B;
            } else {
            	padreDeA.right = B;
            }
        }

        A.right = B.left; // Hace el sub arbol derecho de A
        B.left = A;
        alturaVariable((AvlNode<T>) A);
        alturaVariable((AvlNode<T>) B);
    }

    		// Balanceo right-left\\
    private void balanceRL(Node<T> A, Node<T> padreDeA) {
    	Node<T> B = A.right; // A es pesado a la derecha
    	Node<T> C = B.left; // B ies pesado a la izquierda

        if (A == root) {
            root = C;
        } else {
            if (padreDeA.left == A) {
            	padreDeA.left = C;
            } else {
            	padreDeA.right = C;
            }
        }

        A.right = C.left; // Hace los sub arboles derechos de A
        B.left = C.right; // Hace los sub arboles izquierdos de B
        C.left = A;
        C.right = B;

        // Ajusta las alturas
        alturaVariable((AvlNode<T>) A);
        alturaVariable((AvlNode<T>) B);
        alturaVariable((AvlNode<T>) C);
    }

    		//Elimina elementos del avl
    @Override
    public boolean delete(T element) {
        if (root == null) {
            return false; // El elemento no está en el árbol
        }
        // Encuentra el nodo por borrar así como el parent
        Node<T> padre = null;
        Node<T> nodoActual = root;
        while (nodoActual != null) {
            if (element.compareTo(nodoActual.element) < 0) {
            	padre = nodoActual;
                nodoActual = nodoActual.left;
            } else if (element.compareTo(nodoActual.element) > 0) {
            	padre = nodoActual;
                nodoActual = nodoActual.right;
            } else {
                break; 
            }
        }

        if (nodoActual == null) {
            return false; 
        }
        
        //Caso en donde no hay hijos a la izquierda
        
        if (nodoActual.left == null) {
            if (padre == null) {
                root = nodoActual.right;
            } else {
                if (element.compareTo(padre.element) < 0) {
                	padre.left = nodoActual.right;
                } else {
                	padre.right = nodoActual.right;
                }
            }

            balanceo(padre.element);
        } else {
        	
            //Caso en donde hay un hijo a la izquierda 
        	
        	Node<T> padreDeDerecha = nodoActual;   //El derecho en el sub arbol izquierdo del nodo actual así como el de su parent
        	Node<T> derecha = nodoActual.left;

            while (derecha.right != null) {
            	padreDeDerecha = derecha;
            	derecha = derecha.right;
            }

            nodoActual.element = derecha.element;

            if (padreDeDerecha.right == derecha) {
            	padreDeDerecha.right = derecha.left;
            } else 
            {
            	padreDeDerecha.left = derecha.left;
            }

            balanceo(padreDeDerecha.element);
        }

        size--;
        return true; // Inserta el elemento
    }
	
	
	
	
}

