package binary;

import java.util.ArrayList;
import java.util.List;

public class BinaryTester
{
  public static void main(String[] args)
  {
	  BinaryTree<Integer> bTree = new BinaryTree<>();
	    bTree.insert(50);
	    bTree.insert(30);
	    bTree.insert(60);
	    bTree.insert(25);
	    bTree.insert(40);
	    bTree.insert(35);
	    bTree.insert(70);
	    bTree.insert(65);
	    
	    
	   //System.out.println(bTree.getRoot().getElement());
	    
	    System.out.println("in order");
	    List<Integer> inOrder = new ArrayList<>();
	    bTree.makeInOrder(bTree.getRoot(), inOrder);
	    bTree.print(inOrder);

	    System.out.println();
	    System.out.println("post");
	    List<Integer> postOrder = new ArrayList<>();
	    bTree.postOrderTraversal(bTree.getRoot(), postOrder);
	    bTree.print(postOrder);
	    
	    BinaryTree<String> bTree2 = new BinaryTree<>();
	    bTree2.insert("a");
	    bTree2.insert("+");
	    bTree2.insert("b");
	    
	    bTree2.insert("x");
	    bTree2.insert("*");
	    bTree2.insert("m");
	    
	    
	    System.out.println("in order");
	    List<String> inOrder2 = new ArrayList<>();
	    bTree2.makeInOrder(bTree2.getRoot(), inOrder2);
	    bTree2.print(inOrder2);

	    System.out.println();
	    System.out.println("post");
	    List<String> postOrder2 = new ArrayList<>();
	    bTree2.postOrderTraversal(bTree2.getRoot(), postOrder2);
	    bTree2.print(postOrder2);
	    
	    System.out.println(bTree.totalNodes(bTree.getRoot()));
	    
	    System.out.println(bTree.height(bTree.getRoot()));
  }
}
