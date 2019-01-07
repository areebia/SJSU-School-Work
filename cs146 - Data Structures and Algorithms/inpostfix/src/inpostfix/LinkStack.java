package inpostfix;

public class LinkStack<T>
{
	private Node<T> top; // top of stack

	// determines emptiness of stack
	public boolean isEmpty()
	{
		return top == null;
	}

	// get size of stack
	public T peek()
	{
		return top.in;
	}

	// put info into stack
	public void push(T infix)
	{
		Node<T> oldfirst = top;
		top = new Node<T>();
		top.in = infix;
		top.next = oldfirst;
		
	}

	// get most recent info from stack
	public T pop()
	{
		if (isEmpty())
			return null;
		T item = top.in; // save character from infix for the return
		top = top.next; // delete first character in stack
		return item;
	}
	
	// inner class node
	private class Node<T>
	{
		private T in;
		private Node<T> next;
	}

}
