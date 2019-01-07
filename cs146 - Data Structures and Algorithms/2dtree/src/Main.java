import java.util.ArrayList;
import java.util.List;

public class Main 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Rect r = new Rect();
		r.drawRect();
		
		KDTree<Integer> kd = new KDTree<Integer>();
		kd.insert(50, 45);
		kd.insert(60, 35);
		kd.insert(30, 75);
		kd.insert(40, 20);
		kd.insert(10, 80);
		kd.insert(80, 15); //right now this node wasn't printed for some reason
		kd.insert(100, 0);
		
		// in order listing from the binary tree, only prints out the X coordinate
		List<Integer> inOrder = new ArrayList<>();
		   kd.makeInOrder(kd.getRoot(), inOrder);
		   kd.print(inOrder);
		
		
		
	}
}