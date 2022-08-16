/*
Name and Surname: Tertius de Jongh
Student Number: u19349302
*/

public class ThreadedAVLNode<T extends Comparable<? super T>>
{
   /*
   TODO: You must implement a node class which would be appropriate to use with your trees.
   
   You may add methods and data fields. You may NOT alter the given class name or data fields.
   */
   
   protected T data;                      // stored data
   protected int balanceFactor;           // balance factor (follow the definition in the textbook and slides exactly)
   protected ThreadedAVLNode<T> left;     // right child
   protected ThreadedAVLNode<T> right;    // left child
   protected boolean hasThread;           // flag indicating whether the right pointer is a thread
	
	//adding my stuff

	public ThreadedAVLNode(T el){
		data = el;
		right = null;
		left = null;
		hasThread = false;
		balanceFactor = 0;
	}
	
	public ThreadedAVLNode(T el, ThreadedAVLNode<T> l, ThreadedAVLNode<T> r, int balanceFactor){
		data = el;
		this.balanceFactor = balanceFactor;
		left = l;
		right = r;
		hasThread = false;
	}
	
	public void setBalanceFactor(int b){
		balanceFactor = b;
	}
	
	public int getBalanceFactor(){
		return balanceFactor;
	}
	
	public boolean getHasThread(){
		return hasThread;
	}
	
	public void setHasThread(boolean val){
		hasThread = val;
	}
	
	public T getData(){
		return data;
	}
	
	public void setData(T element){
		data = element;
	}
	
	public void printTree(){
		if (right != null && hasThread == false) {
		    right.printTree(true, "");
		}
		printNodeValue();
		if (left != null) {
		    left.printTree(false, "");
		}
	}
	
	 private void printNodeValue(){
		if (data == null) {
		    System.out.print("<null>");
		} else {
		    System.out.print(data.toString());
		}
		System.out.print('\n');
	}
	
	private void printTree(boolean isRight, String indent){
		if (right != null && hasThread == false) {
		    right.printTree(true, indent + (isRight ? "        " : " |      "));
		}
		System.out.print(indent);
		if (isRight) {
		    System.out.print(" /");
		} else {
		    System.out.print(" \\");
		}
		System.out.print("----- ");
		printNodeValue();
		if (left != null) {
		    left.printTree(false, indent + (isRight ? " |      " : "        "));
		}
	}

}
