import java.util.List;

/**
 * This class implements a Binary tree whose nodes hold objects that implement
 * the Comparable interface.
 * 
 * used code from previous 146 class as reference
 * 
 * @param <T>
 * 
 */
public class KDTree<T extends Comparable<T>>
{
	private Node<T> root;

	/**
	 * Constructs an empty tree.
	 */
	public KDTree()
	{
	}

	public Node<T> getRoot()
	{
		return root;
	}

	/**
	 *  inserts the element and calls on add to creat the node
	 */
	public void insert(T x, T y)
	{
		add(x, y);
	}
    
	/**
	 *  inorder traversal
	 * @param aRoot
	 * @param order
	 */
	public void makeInOrder(Node<T> aRoot, List<T> order)
	{
		//returns X coordinates of the nodes.
		if (aRoot != null)
		{
			makeInOrder(aRoot.left, order);
			order.add(aRoot.X);
			makeInOrder(aRoot.right, order);
		}
	}
	
	/**
	 * gets height of tree
	 * @param n
	 * @return
	 */
	public int height(Node<T> n)  
	    { 
	        if (n == null) 
	            return 0; 
	        else 
	        {  
	            int lheight = height(n.left); 
	            int rheight = height(n.right);  
	            if (lheight > rheight) 
	                return (lheight + 1); 
	             else 
	                return (rheight + 1); 
	        } 
	    } 
	
	/**
	 * gets total amount of nodes, calls on height
	 * @param n
	 * @return
	 */
	public int totalNodes(Node<T> n)
	{
		if (n == null) 
            return 0; 
        else 
        {  
        	
            int lTotal = totalNodes(n.left); 
            int rTotal = totalNodes(n.right);  
             
                return (lTotal + rTotal + 1); 
            
               
                
        } 
	}
/**
 *  do post order traversal
 * @param aRoot
 * @param postOrder
 */
	public void postOrderTraversal(Node<T> aRoot, List<T> postOrder)
	{
		if (aRoot != null)
		{
			postOrderTraversal(aRoot.left, postOrder);
			postOrderTraversal(aRoot.right, postOrder);
			postOrder.add(aRoot.X);
		}
	}

	/**
	 * main printer for the traversals
	 * 
	 * @param order
	 */
	public void print(List<T> order)
	{
		for (T item : order)
		{
			System.out.println(item);
		}
	}

	/**
	 * main adding mechanism
	 * @param element
	 */
	public void add(T x, T y)
	{
		Node<T> newNode = new Node<T>(x, y);
		if (root == null)
		{
			root = newNode;
		}
		else
		{
			Node<T> tempNode = root;
			Node<T> prev = null;
			int level = 0;
			while (tempNode != null)
			{
				prev = tempNode;
				// this set of if's are testing whether the level is even or odd and then checks for the X or Y coordinate respectively
				if (level % 2 == 0)
				{
					if (x.compareTo(tempNode.X) > 0)
					{
						tempNode = tempNode.right;
					}
					else
					{
						tempNode = tempNode.left;
					}
				}
				else
				{
					if (y.compareTo(tempNode.Y) > 0)
					{
						tempNode = tempNode.right;
					}
					else
					{
						tempNode = tempNode.left;
					}
				}
				level++;
			}
			// these if's 
			if (x.compareTo(prev.X) < 0)
			{
				prev.left = newNode;
			}
			else
			{
				prev.right = newNode;
			}
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * A node; stores element item and references to the left and right
	 * child nodes.
	 */
	class Node<T extends Comparable<T>> // extends Comparable<T>
	{
		public Node<T> left;
		public Node<T> right;
		public T X;
		public T Y;

		public Node(T X, T Y)
		{
			this.X = X;
			this.Y = Y;
		}
		public T getX()
		{
			return X;
		}
		public T getY()
		{
			return Y;
		}
		public String toString()
		{
			String x;
			String y;
			x = String.valueOf(this.getX());
			y = String.valueOf(this.getY());
			return "(" + x + "," + " "+ y+ ")";
		}
	}
}
