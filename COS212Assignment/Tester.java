/*
Name and Surname: Tertius de Jongh
Student Number: u19349302
*/

public class Tester 
{
	public static void insert(ThreadedAVLTree<String> tree, String[] list){
		for(int i = 0; i < list.length; i++){
			System.out.println("Inserting: " + list[i]);
			System.out.println(tree.insert(list[i]));
		}
	}
	public static void delete(ThreadedAVLTree<String> tree, String[] list){
		for(int i = 0; i < list.length; i++){
			System.out.println("Deleting: " + list[i]);
			System.out.println(tree.delete(list[i]));
		}
	}
	
	public static void containElm(ThreadedAVLTree<String> tree, String[] list){
		for(int i = 0; i < list.length; i++){
			System.out.println("Does Tree contain " + list[i]);
			System.out.println(tree.contains(list[i]));
		}
	}
	
	public static void delprint(ThreadedAVLTree<String> tree, String[] list){
		for(int i = 0; i < list.length; i++){
			System.out.println("Deleting: " + list[i]);
			System.out.println(tree.delete(list[i]));
			System.out.println(tree.preorderDetailed());
			tree.print();
		}
	}
   public static void main(String[] args) throws Exception
   {
      /*
      TODO: Write code to thoroughly test your implementation here.
      
      Note that this file will be overwritten for marking purposes.
      */
	   /*
	   my constructors may differ from yours... just add yours here when I create new trees
	   or nodes but the rest should work
	   I ADVISE YOU TO TEST THIS MAIN WITHOUT THE THREADS FIRST UNTILL LINE 82
	   TEST YOUR CODE WITHOUT THE TREADS FIRST IN GENERAL. IT MAKES IT EASIER 
	   Also add the print functions i sent with the tester. it works. just modify them to work for the threads by adding extra checks... I will leave that for you to solve. 
	   */
		String[] list = new String[]{"A","F","D","C","B","E","G","H","I"};
		ThreadedAVLNode<String> Node = new ThreadedAVLNode<String>("A");// this contructor only takes in T data. create one to makes yours to work or add your constructor here
		ThreadedAVLTree<String> tree1 = new ThreadedAVLTree<String>();
		insert(tree1, list);
		Node = tree1.getRoot();
		System.out.println("Root of tree1: " + Node.getData());
		System.out.println("Print tree One:");
		tree1.print();
		System.out.println("Getting Height of tree1: " + tree1.getHeight());
		System.out.println("Getting number nodes of tree1: " + tree1.getNumberOfNodes());
		System.out.println("Now to test Left Right case:");
		System.out.println("New Tree content:");
		String[] list2 = new String[]{"F","G","E","C","D"};
		ThreadedAVLTree<String> tree2 = new ThreadedAVLTree<String>();
		insert(tree2, list2);
		Node = tree2.getRoot();
		System.out.println("Root of tree2: " + Node.getData());
		System.out.println("Getting Height of tree2: " + tree2.getHeight());
		System.out.println("Getting number nodes of tree2: " + tree2.getNumberOfNodes());
		System.out.println("Deleting two specific nodes");
		String[] listDel = new String[]{"D","A"};
		delete(tree1, listDel);
		System.out.println("Checking if list contains Items");
		String[] listCon = new String[]{"F","E","G","H","I","D","C"};
		containElm(tree1, listCon);
		System.out.println("Print tree One:");
		tree1.print();
		System.out.println("Getting Height of tree1: " + tree1.getHeight());
		System.out.println("Getting number nodes of tree1: " + tree1.getNumberOfNodes());
		System.out.println("Print tree Two:");
		tree2.print();
		System.out.println("Getting Height of tree2: " + tree2.getHeight());
		System.out.println("Getting number nodes of tree2: " + tree2.getNumberOfNodes());
		System.out.println("Testing clone and copy constructor: ");
		ThreadedAVLTree<String> tree3 = new ThreadedAVLTree<String>(tree1);
		ThreadedAVLTree<String> tree4 = new ThreadedAVLTree<String>();
		tree4 = tree2;
		System.out.println("Tree3 should look like tree1 after delete: ");
		tree3.print();
		System.out.println("Tree4 should look like tree2: ");
		tree4.print();
		System.out.println("/////////////////////////////////////////////////////////////////////////");
		System.out.println("Testing inorder ad preorder tests for tree3, compare it to preorder and inorder fr tree 1: ");
		System.out.println("This tests if you correctly clone the tree with threads");
		System.out.println("Testing inorder: ");
		System.out.println(tree3.inorder());
		System.out.println("Testing inorderDetailed: ");
		System.out.println(tree3.inorderDetailed());
		System.out.println("Testing preorder: ");
		System.out.println(tree3.preorder());
		System.out.println("Testing preorderDetailed: ");
		System.out.println(tree3.preorderDetailed());
		System.out.println("/////////////////////////////////////////////////////////////////////////");
		System.out.println("Testing inorder ad preorder tests for tree1...: ");
		System.out.println("Testing inorder: ");
		System.out.println(tree1.inorder());
		System.out.println("Testing inorderDetailed: ");
		System.out.println(tree1.inorderDetailed());
		System.out.println("Testing preorder: ");
		System.out.println(tree1.preorder());
		System.out.println("Testing preorderDetailed: ");
		System.out.println(tree1.preorderDetailed());
		System.out.println("Testing inorder ad preoerder tests for tree2: ");
		System.out.println("Testing inorder: ");
		System.out.println(tree2.inorder());
		System.out.println("Testing inorderDetailed: ");
		System.out.println(tree2.inorderDetailed());
		System.out.println("Testing preorder: ");
		System.out.println(tree2.preorder());
		System.out.println("Testing preorderDetailed: ");
		System.out.println(tree2.preorderDetailed());
		System.out.println("Print tree One:");
		tree1.print();
		System.out.println("Print tree Two:");
		tree2.print();
		System.out.println("Testing weird cases: ");
		ThreadedAVLTree<String> tree5 = new ThreadedAVLTree<String>();
		ThreadedAVLTree<String> tree6 = new ThreadedAVLTree<String>();
		String[] weird = new String[]{"C","B"};
		insert(tree5, weird);
		System.out.println("Printing weird case of preorder: ");
		System.out.println(tree5.preorderDetailed());
		tree5.print();
		String[] weird2 = new String[]{"F","B","G","A"};
		insert(tree6, weird2);
		System.out.println("Printing weird case of preorder: ");
		System.out.println(tree6.preorderDetailed());
		tree6.print();
		
		System.out.println("Ok. Testing deletes on non existing stuff and stuff: ");
		
		String[] delS = new String[]{"P","L","S","G","V","M","T","M","E","Z"};
		delete(tree1, delS);
		System.out.println("Printing tree1. G and E should be missing: ");
		tree1.print();
		insert(tree2, delS);
		System.out.println("Printing tree2 with added delS array: ");
		tree2.print();
		System.out.println("Testing inorder ad preoerder tests for tree2: ");
		System.out.println("Testing inorder: ");
		System.out.println(tree2.inorder());
		System.out.println("Testing inorderDetailed: ");
		System.out.println(tree2.inorderDetailed());
		System.out.println("Testing preorder: ");
		System.out.println(tree2.preorder());
		System.out.println("Testing preorderDetailed: ");
		System.out.println(tree2.preorderDetailed());
		System.out.println("Deleting delS from tree2 and print: ");
		delete(tree2, delS);
		System.out.println("Printing tree2 so it looks small print: ");
		tree2.print();
		System.out.println("Testing preorderDetailed: ");
		System.out.println(tree2.preorderDetailed());
		System.out.println("/////////////////////////////////////////////////////////////////////////");
		System.out.println("Horrible test for detailed problem :(");
		System.out.println("/////////////////////////////////////////////////////////////////////////");
		ThreadedAVLTree<String> BoB = new ThreadedAVLTree<String>();
		String[] bobList = new String[]{"A","F","D","C","B","E","G","H","I", "Z", "K"};
		insert(BoB, bobList);
		System.out.println("/////////////////////////////////////////////////////////////////////////");
		delprint(BoB, bobList);
		//*/
	   }
}

/*
Inserting: A
true
Inserting: F
true
Inserting: D
true
Inserting: C
true
Inserting: B
true
Inserting: E
true
Inserting: G
true
Inserting: H
true
Inserting: I
true
Root of tree1: D
Print tree One:

                 /----- I
         /----- H
         |       \----- G
 /----- F
 |       \----- E
D
 |       /----- C
 \----- B
         \----- A

Getting Height of tree1: 4
Getting number nodes of tree1: 9
Now to test Left Right case:
New Tree content:
Inserting: F
true
Inserting: G
true
Inserting: E
true
Inserting: C
true
Inserting: D
true
Root of tree2: F
Getting Height of tree2: 3
Getting number nodes of tree2: 5
Deleting two specific nodes
Deleting: D
true
Deleting: A
true
Checking if list contains Items
Does Tree contain F
true
Does Tree contain E
true
Does Tree contain G
true
Does Tree contain H
true
Does Tree contain I
true
Does Tree contain D
false
Does Tree contain C
true
Print tree One:

         /----- I
 /----- H
 |       \----- G
F
 |       /----- E
 \----- C
         \----- B

Getting Height of tree1: 3
Getting number nodes of tree1: 7
Print tree Two:

 /----- G
F
 |       /----- E
 \----- D
         \----- C

Getting Height of tree2: 3
Getting number nodes of tree2: 5
Testing clone and copy constructor: 
Tree3 should look like tree1 after delete: 

         /----- I
 /----- H
 |       \----- G
F
 |       /----- E
 \----- C
         \----- B

Tree4 should look like tree2: 

 /----- G
F
 |       /----- E
 \----- D
         \----- C

/////////////////////////////////////////////////////////////////////////
Testing inorder ad preorder tests for tree3, compare it to preorder and inorder fr tree 1: 
This tests if you correctly clone the tree with threads
Testing inorder: 
B,C,E,F,G,H,I
Testing inorderDetailed: 
B|C,E|F,G|H,I
Testing preorder: 
F,C,B,E,H,G,I
Testing preorderDetailed: 
F,C,B|E|H,G|I
/////////////////////////////////////////////////////////////////////////
Testing inorder ad preorder tests for tree1...: 
Testing inorder: 
B,C,E,F,G,H,I
Testing inorderDetailed: 
B|C,E|F,G|H,I
Testing preorder: 
F,C,B,E,H,G,I
Testing preorderDetailed: 
F,C,B|E|H,G|I
Testing inorder ad preoerder tests for tree2: 
Testing inorder: 
C,D,E,F,G
Testing inorderDetailed: 
C|D,E|F,G
Testing preorder: 
F,D,C,E,G
Testing preorderDetailed: 
F,D,C|E|G
Print tree One:

         /----- I
 /----- H
 |       \----- G
F
 |       /----- E
 \----- C
         \----- B

Print tree Two:

 /----- G
F
 |       /----- E
 \----- D
         \----- C

Testing weird cases: 
Inserting: C
true
Inserting: B
true
Printing weird case of preorder: 
C,B|

C
 \----- B

Inserting: F
true
Inserting: B
true
Inserting: G
true
Inserting: A
true
Printing weird case of preorder: 
F,B,A||G

 /----- G
F
 \----- B
         \----- A

Ok. Testing deletes on non existing stuff and stuff: 
Deleting: P
false
Deleting: L
false
Deleting: S
false
Deleting: G
true
Deleting: V
false
Deleting: M
false
Deleting: T
false
Deleting: M
false
Deleting: E
true
Deleting: Z
false
Printing tree1. G and E should be missing: 

         /----- I
 /----- H
F
 \----- C
         \----- B

Inserting: P
true
Inserting: L
true
Inserting: S
true
Inserting: G
false
Inserting: V
true
Inserting: M
true
Inserting: T
true
Inserting: M
false
Inserting: E
false
Inserting: Z
true
Printing tree2 with added delS array: 

                 /----- Z
         /----- V
 /----- T
 |       \----- S
P
 |               /----- M
 |       /----- L
 |       |       \----- G
 \----- F
         |       /----- E
         \----- D
                 \----- C

Testing inorder ad preoerder tests for tree2: 
Testing inorder: 
C,D,E,F,G,L,M,P,S,T,V,Z
Testing inorderDetailed: 
C|D,E|F,G|L,M|P,S|T,V,Z
Testing preorder: 
P,F,D,C,E,L,G,M,T,S,V,Z
Testing preorderDetailed: 
P,F,D,C|E|L,G|M|T,S|V,Z
Deleting delS from tree2 and print: 
Deleting: P
true
Deleting: L
true
Deleting: S
true
Deleting: G
true
Deleting: V
true
Deleting: M
true
Deleting: T
true
Deleting: M
false
Deleting: E
true
Deleting: Z
true
Printing tree2 so it looks small print: 

 /----- F
D
 \----- C

Testing preorderDetailed: 
D,C|F
/////////////////////////////////////////////////////////////////////////
Horrible test for detailed problem :(
/////////////////////////////////////////////////////////////////////////
Inserting: A
true
Inserting: F
true
Inserting: D
true
Inserting: C
true
Inserting: B
true
Inserting: E
true
Inserting: G
true
Inserting: H
true
Inserting: I
true
Inserting: Z
true
Inserting: K
true
/////////////////////////////////////////////////////////////////////////
Deleting: A
true
D,B,C|H,F,E|G|K,I|Z

                 /----- Z
         /----- K
         |       \----- I
 /----- H
 |       |       /----- G
 |       \----- F
 |               \----- E
D
 |       /----- C
 \----- B

Deleting: F
true
D,B,C|H,E,G|K,I|Z

                 /----- Z
         /----- K
         |       \----- I
 /----- H
 |       |       /----- G
 |       \----- E
D
 |       /----- C
 \----- B

Deleting: D
true
H,C,B|E,G|K,I|Z

         /----- Z
 /----- K
 |       \----- I
H
 |               /----- G
 |       /----- E
 \----- C
         \----- B

Deleting: C
true
H,E,B|G|K,I|Z

         /----- Z
 /----- K
 |       \----- I
H
 |       /----- G
 \----- E
         \----- B

Deleting: B
true
H,E,G|K,I|Z

         /----- Z
 /----- K
 |       \----- I
H
 |       /----- G
 \----- E

Deleting: E
true
H,G|K,I|Z

         /----- Z
 /----- K
 |       \----- I
H
 \----- G

Deleting: G
true
K,H,I|Z

 /----- Z
K
 |       /----- I
 \----- H

Deleting: H
true
K,I|Z

 /----- Z
K
 \----- I

Deleting: I
true
K,Z

 /----- Z
K

Deleting: Z
true
K

K

Deleting: K
true


Empty Tree


*/
