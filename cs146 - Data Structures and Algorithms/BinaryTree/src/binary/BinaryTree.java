package binary;

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
public class BinaryTree<T extends Comparable<T>>
{
	private Node<T> root;

	/**
	 * Constructs an empty tree.
	 */
	public BinaryTree()
	{
	}

	public Node<T> getRoot()
	{
		return root;
	}

	/**
	 *  inserts the element and calls on add to creat the node
	 */
	public void insert(T element)
	{
		add(element);
	}
    
	/**
	 *  inorder traversal
	 * @param aRoot
	 * @param order
	 */
	public void makeInOrder(Node<T> aRoot, List<T> order)
	{
		if (aRoot != null)
		{
			makeInOrder(aRoot.left, order);
			order.add(aRoot.element);
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
			postOrder.add(aRoot.element);
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
	public void add(T element)
	{
		Node<T> newNode = new Node<T>(element);
		if (root == null)
		{
			root = newNode;
		}
		else
		{
			Node<T> tempNode = root;
			Node<T> prev = null;
			while (tempNode != null)
			{
				prev = tempNode;
				if (element.compareTo(tempNode.element) > 0)
				{
					tempNode = tempNode.right;
				}
				else
				{
					tempNode = tempNode.left;
				}
			}
			if (element.compareTo(prev.element) < 0)
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
		public T element;

		public Node(T element)
		{
			this.element = element;
		}
		public T getElement()
		{
			return element;
		}
		public String toString()
		{
			String s;

			s = String.valueOf(this.getElement());

			return s;
		}
	}
}
