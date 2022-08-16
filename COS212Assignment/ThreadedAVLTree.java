/*
Name and Surname: Tertius de Jongh
Student Number: u19349302
*/
//@SuppressWarnings("unchecked")
public class ThreadedAVLTree<T extends Comparable<? super T>>
{
   /*
   TODO: You must complete each of the methods in this class to create your own threaded AVL tree.
   Note that the AVL tree is single-threaded, as described in the textbook and slides.
   
   You may add any additional methods or data fields that you might need to accomplish your task.
   You may NOT alter the given class name, data fields, or method signatures.
   */
   
   private ThreadedAVLNode<T> root;       // the root node of the tree
   
   public ThreadedAVLTree()
   {
	root = null;
   }
   
   public ThreadedAVLTree(ThreadedAVLTree<T> other)
   {
	ThreadedAVLTree<T> cloneOther = other.clone();
	root = cloneOther.getRoot();
   }
   
   public ThreadedAVLTree<T> clone()
   {
	if(root != null){
		ThreadedAVLTree<T> newTree = new ThreadedAVLTree<T>();
		newTree.setRoot(recClone(root));//new over here
		ThreadingSince2020(newTree.getRoot());
		return newTree;
	}
	else return null;
   }
   
   public ThreadedAVLNode<T> getRoot()
   {
      return root;
   }
   
   public int getNumberOfNodes()//count nodes in tree curently
   {
	if(root == null){
		return 0;
	}
	else{
		return recCountNodes(root);
	}
   }
   
   public int getHeight()
   {
     
      if(root == null){
		return 0;
	}
	else if(root.right == null && root.left == null){
		return 1;
	}
	else{
		return heightRec(root);
	}
   }
   
   public boolean insert(T element)
   {
	
	if(contains(element) != true){
		root = recInsert(root, element);
		ThreadingSince2020(root);
		return true;
	}
	
      return false;
   }
   
   public boolean delete(T element)
   {
      if(root != null){
		if(contains(element) == true){
			if(root.right == null && root.left == null){
				root = null;
			}
			else{
				root = deleteNode(root, element);
				ThreadingSince2020(root);
			}
			return true;
		}
	}
      return false;
   }
   
   public boolean contains(T element)
   {
      
	ThreadedAVLNode<T> curr = root;
      if(root != null){
		if((root.getData()).equals(element)){
			return true;
		}
		while(curr != null){//here hasThread variables should do the trick
			if(element.compareTo(curr.getData()) < 0){
				curr = curr.left;
			}
			else if(element.compareTo(curr.getData()) > 0){//here hasThread variables should do the trick
				if(curr.getHasThread() == true){
					return false;
				}
				curr = curr.right;
			}
			else if(element.equals(curr.getData())){
				return true;
			}
		}
		
	}
      return false;
   }
   
   public String inorder()
   {
      /*
      This method must return a string representation of the elements in the tree visited during an
      inorder traversal. This method must not be recursive. Instead, the threads must be utilised
      to perform a depth-first inorder traversal.
      
      Note that there are no spaces in the string, and the elements are comma-separated. Note that
      no comma appears at the end of the string.
      
      If the tree looks as follows:
      
          C
         / \
        B   E
       /   / \
      A   D   F
      
      The following string must be returned:
      
      A,B,C,D,E,F
      */
	   ThreadedAVLNode<T> curr = root;
      if(root != null){
		if(root.right == null && root.left == null){
			String str = root.getData().toString();
			return str;
		}
		else{
			String str;
			String str2;
			while(curr.left != null){
				curr = curr.left;
			}
			str = curr.getData().toString()+ ",";
			while(curr != null){
				if(curr.getHasThread() == true){
					curr = curr.right;
					if(curr != null)
						str = str + curr.getData().toString() + ",";
				}
				else{
					curr = curr.right;
					if(curr != null){
						while(curr.left != null){
							curr = curr.left;
						}
						if(curr.left == null && curr.right == null){
							str = str + curr.getData().toString();
						}
						else str = str + curr.getData().toString()+ ",";
					}
				}
			}
			return str;
		}
	}
      return "";
   }
   
   public String inorderDetailed()
   {
      /*
      This method must return a string representation of the elements in the tree visited during an
      inorder traversal. This method must not be recursive. Instead, the threads must be utilised
      to perform a depth-first inorder traversal.
      
      Note that there are no spaces in the string, and the elements are comma-separated.
      Additionally, whenever a thread is followed during the traversal, a pipe symbol should be
      printed instead of a comma. Note that no comma or pipe symbol appears at the end of the
      string. Also note that if multiple threads are followed directly after one another, multiple
      pipe symbols will be printed next to each other.
      
      If the tree looks as follows:
      
          C
         / \
        B   E
       /   / \
      A   D   F
      
      In this tree, there is a thread linking the right branch of node A to node B, a thread
      linking the right branch of node B to node C, and a thread linking the right branch of node D
      to node E. The following string must therefore be returned:
      
      A|B|C,D|E,F
      */
      ThreadedAVLNode<T> curr = root;
      if(root != null){
		if(root.right == null && root.left == null){
			String str = root.getData().toString();
			return str;
		}
		else{
			String str;
			String str2;
			while(curr.left != null){
				curr = curr.left;
			}
			str = curr.getData().toString();
			while(curr != null){
				if(curr.getHasThread() == true){
					curr = curr.right;
					if(curr != null)
						str = str + "|" +  curr.getData().toString();
				}
				else{
					curr = curr.right;
					if(curr != null){
						while(curr.left != null){
							curr = curr.left;
						}
						str = str + ","+ curr.getData().toString();
					}
				}
			}
			return str;
		}
	}
      return "";
   }
   
   public String preorder()
   {
      /*
      This method must return a string representation of the elements in the tree visited during a
      preorder traversal. This method must not be recursive. Instead, the threads must be utilised
      to perform a depth-first preorder traversal. See the last paragraph on page 240 of the
      textbook for more detail on this procedure.
      
      Note that there are no spaces in the string, and the elements are comma-separated. Note that
      no comma appears at the end of the string.
      
      If the tree looks as follows:
      
          C
         / \
        B   E
       /   / \
      A   D   F
      
      The following string must be returned:
      
      C,B,A,E,D,F
      */
      ThreadedAVLNode<T> curr = root;
      if(root != null){
		if(root.right == null && root.left == null){
			String str =  root.getData().toString();
			return str;
		}
		else{
			String str =  root.getData().toString();
			while(curr.left != null){
				curr = curr.left;
				str = str + "," +  curr.getData().toString();
			}
			while(curr != null){
				if(curr.getHasThread() == true){
					curr = curr.right;
				}
				else{
					curr = curr.right;
					if(curr != null){
						str = str + "," + curr.getData().toString();
						while(curr.left != null){
							curr = curr.left;
							str = str + "," + curr.getData().toString();
						}
					}
				}
			}
			
			return str;
		}
	}
      return "";
   }
   
   public String preorderDetailed()
   {
      /*
      This method must return a string representation of the elements in the tree visited during a
      preorder traversal. This method must not be recursive. Instead, the threads must be utilised
      to perform a depth-first preorder traversal. See the last paragraph on page 240 of the
      textbook for more detail on this procedure.
      
      Note that there are no spaces in the string, and the elements are comma-separated.
      Additionally, whenever a thread is followed during the traversal, a pipe symbol should be
      printed instead of a comma. Note that no comma or pipe symbol appears at the end of the
      string. Also note that if multiple threads are followed directly after one another, multiple
      pipe symbols will be printed next to each other.
      
      If the tree looks as follows:
      
          C
         / \
        B   E
       /   / \
      A   D   F
      
      In this tree, there is a thread linking the right branch of node A to node B, a thread
      linking the right branch of node B to node C, and a thread linking the right branch of node D
      to node E. The following string must therefore be returned:
      
      C,B,A||E,D|F
      
      Note that two pipe symbols are printed between A and E, because the thread linking the right
      child of node A to node B is followed, B is not printed because it has already been visited,
      and the thread linking the right child of node B to node C is followed.
      */
       ThreadedAVLNode<T> curr = root;
      if(root != null){
		if(root.right == null && root.left == null){
			String str = root.getData().toString();
			return str;
		}
		else{
			String str = root.getData().toString();
			while(curr.left != null){
				curr = curr.left;
				str = str + "," + curr.getData().toString();
			}
			while(curr != null){
				if(curr.getHasThread() == true){
					curr = curr.right;
					str = str + "|";
				}
				else{
					if(curr.left == null && curr.right != null){
						str = str + ",";
					}
					curr = curr.right;
					if(curr != null){
						if(curr.getHasThread() == false && (curr.right != null && curr.left == null)){
							str = str + curr.getData().toString();
						}
						else	str = str + curr.getData().toString();
						while(curr.left != null){
							curr = curr.left;
							str = str + "," + curr.getData().toString();
						}
					}
				}
			}
			
			return str;
		}
	}
      return "";
   }
   
   //helper functions
   
   public ThreadedAVLNode<T> recClone(ThreadedAVLNode<T> root){
		if(root == null){
			return null;
		}
		
		ThreadedAVLNode<T> newNode = new ThreadedAVLNode<T>(root.getData());
		newNode.setBalanceFactor(root.getBalanceFactor());
		newNode.left = recClone(root.left);
		newNode.setHasThread(root.getHasThread());
		if(root.getHasThread() != true){
			newNode.right = recClone(root.right);
		}
		return newNode;
	}
	
	public int recCountNodes(ThreadedAVLNode<T> root){
		if(root ==  null){
			return 0;
		}
		else {
			if(root.getHasThread() == false){
				return 1 + recCountNodes(root.left) + recCountNodes(root.right);
			}
			else{
				return 1 + recCountNodes(root.left);
			}
		}
	}
	
	public int heightRec(ThreadedAVLNode<T> root){
		if(root == null){
			return 0;
		}
		else{
			int tL = heightRec(root.left);
			int tR = 0;
			if(root.getHasThread() == false){
				tR = heightRec(root.right);
			}
			if(tL > tR)
				return tL+1;
			else
				return tR+1;
		}
	}
	//recursive threads function, not
	public void disableRight(ThreadedAVLNode<T> root){
		if(root != null){
			ThreadedAVLNode<T> curr = root.right;
			if(curr != null){
				while(curr.right != null && curr.getHasThread() ==false){
					curr = curr.right;
				}
				curr.right = null;
				curr.setHasThread(false);
			}
		}
	}
	
	public void OnThreads(ThreadedAVLNode<T> node){
		if(node != null){
			ThreadedAVLNode<T> x = node.left;
			if(x != null){
				if(x.right == null){
					x.setHasThread(true);
					x.right = node;
				}
				else if(x.right != null){
					while(x.right != null && x.getHasThread() == false){
						x = x.right;
						//System.out.println(x.getData());
					}
					x.setHasThread(true);
					x.right = node;
				}
			}
		}
	}
	
	public void ThreadingSince2020(ThreadedAVLNode<T> node){
		if(node != null){
			OnThreads(node);
			ThreadingSince2020(node.left);
			if(node.getHasThread() == false){
				ThreadingSince2020(node.right);
			}
		}
	}
	
	//helper functions for the rotation
	//rotation functions
	public ThreadedAVLNode<T> rotateRight(ThreadedAVLNode<T> y){
		ThreadedAVLNode<T> x = y.left;
		
		if(x.getHasThread() == true){
			x.setHasThread(false);
			x.right = null;
		}
		
		ThreadedAVLNode<T> z = x.right;
		
		x.right = y;
		y.left = z;
		
		int balance = 0;
		
		
		balance = heightRec(x.right) - heightRec(x.left);
		x.setBalanceFactor(balance);
		balance = heightRec(y.right) - heightRec(y.left);
		y.setBalanceFactor(balance);
		
		return x;
	}
	
	public ThreadedAVLNode<T> rotateLeft(ThreadedAVLNode<T> x){
		ThreadedAVLNode<T> y = x.right;
		ThreadedAVLNode<T> z = y.left;
		
		y.left = x;
		x.right = z;
		
		int balance = 0;
		
		balance = heightRec(x.right) - heightRec(x.left);
		x.setBalanceFactor(balance);
		balance = heightRec(y.right) - heightRec(y.left);
		y.setBalanceFactor(balance);
		
		return y;
	}
	
	//insert function
	public ThreadedAVLNode<T> recInsert(ThreadedAVLNode<T> node, T data){
		if(node == null){
			ThreadedAVLNode<T> newNode = new ThreadedAVLNode<T>(data);
			return newNode;
		}
		else if(data.compareTo(node.getData()) < 0){
			node.left = recInsert(node.left, data);
		}
		else if(data.compareTo(node.getData()) > 0){
			if(node.getHasThread() == true){
				node.setHasThread(false);
				node.right = null;
			}
			node.right = recInsert(node.right, data);
		}
		int balance = 0;
		if(node.getHasThread() == true){
			node.setHasThread(false);
			node.right = null;
			balance = 0 - heightRec(node.left);
		}
		else balance = heightRec(node.right) - heightRec(node.left);
		// left left case
		if(balance < -1 && node.left != null && data.compareTo(node.left.getData()) < 0){
			if(node.getHasThread() == true){
				node.setHasThread(false);
			}
			return rotateRight(node);
		}
		//right right case
		if(balance > 1 && node.right != null && data.compareTo(node.right.getData()) > 0){
			if(node.getHasThread() == true){
				node.setHasThread(false);
			}
			return rotateLeft(node);
		}//Left Right Case
		if(balance < -1 && node.left != null && data.compareTo(node.left.getData()) > 0){
			if(node.getHasThread() == true){
				node.setHasThread(false);
			}
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}//change under here < -1 Right left case
		if(balance > 1 && node.right != null && data.compareTo(node.right.getData()) < 0){
			if(node.getHasThread() == true){
				node.setHasThread(false);
			}
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		
		node.setBalanceFactor(balance);
		return node;
	}
	
	//helper for delete. looks for right-most node in left subtree
	public ThreadedAVLNode<T> maxVal(ThreadedAVLNode<T> root){
		ThreadedAVLNode<T> curr = root.left;
		ThreadedAVLNode<T> prev = null;
		if(curr != null){
			while(curr.right != null && curr.getHasThread() == false ){
				prev = curr;
				curr = curr.right;
			}
			return curr;
		}
		return null;
	}
	
	//delete by copy function recursively
	public ThreadedAVLNode<T> deleteNode(ThreadedAVLNode<T> root, T data){
		if(root == null){
			return root;
		}
		if(root.getHasThread() == true){
			root.setHasThread(false);
			root.right = null;
		}
		if(data.compareTo(root.getData()) < 0){
			root.left = deleteNode(root.left, data);
		}
		else if(data.compareTo(root.getData()) > 0){
			root.right = deleteNode(root.right, data);
		}
		else{
			if(root.left == null || root.right == null){
				ThreadedAVLNode<T> temp = null;
				if(temp == root.left){
					temp = root.right;
				}
				else{
					temp = root.left;
				}
				
				if(temp == null){
					temp = root;
					root = null;
				}
				else {
					root = temp;
				}
				
			}
			else {
				ThreadedAVLNode<T> temp = maxVal(root);
				root.setData(temp.getData());
				root.left =deleteNode(root.left, temp.getData());
			}
		}
		if(root == null){
			return root;
		}
		if(root.getHasThread() == true){
			root.setHasThread(false);
			root.right = null;
		}
		int balance = heightRec(root.right) - heightRec(root.left);
		//right right case
		if(balance > 1 && root.right != null && root.right.getBalanceFactor() >= 0){//maybe just positive one. if its zero then its not out of balance
			if(root.getHasThread() == true){
				root.setHasThread(false);
			}
			return rotateLeft(root);
		}
		//right left case
		if(balance > 1 && root.right != null && root.right.getBalanceFactor() < 0){
			if(root.getHasThread() == true){
				root.setHasThread(false);
			}
			root.right = rotateRight(root.right);
			return rotateLeft(root);
		}
		//left left case
		if(balance < -1 && root.left != null && root.left.getBalanceFactor() <= 0){
			if(root.getHasThread() == true){
				root.setHasThread(false);
			}
			return rotateRight(root);
		}
		//Left Right Case
		if(balance < -1 && root.left != null && root.left.getBalanceFactor() > 0){
			if(root.getHasThread() == true){
				root.setHasThread(false);
			}
			root.left = rotateLeft(root.left);
			return rotateRight(root);
		}
		
		root.setBalanceFactor(balance);
		return root;
	}
	
	public void setRoot(ThreadedAVLNode<T> node){
		root = node;
	}

public void print()
    {
        System.out.println("");
        if(root == null)
        {
            System.out.println("Empty Tree");
        }
        else
	{
            root.printTree();
        }
        System.out.println("");
    }

}
