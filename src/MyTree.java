/**
* MyTree.java
*
* a tree that stores my favorite anime alphabetically
* will eventually add a small GUI so I can search through them quickly. 
*/

import java.util.*;

public class MyTree<T>{
	
	
	protected MyTreeNode<T> root;
	protected static int rightLevels = 0;
	protected static int leftLevels = 0;
	protected static int items = 0;
	protected static int treeLevels = 0;
	
	/**
	 * Creates tree with empty root
	 */
	public MyTree(){
		root = new MyTreeNode<T>();
	}
	
	/**
	 * Creates a new tree using a specified root
	 * @param rt
	 */
	public MyTree(MyTreeNode<T> rt){
		root = rt;
	}
	
	/**
	 * Add new element to the tree
	 * @param elem
	 */
	public void addToTree(MyTreeNode<T> node, T elem){
		
		//----------------------------
		//ensure that root is not null
		//----------------------------
		assert(root != null);
		
		MyTreeNode<T> current = node;
		
		MyTreeNode<T> newNode = new MyTreeNode<T>(); //make a new node for the elem
		newNode.data = elem; //put the data into the newNode
		
		//---------------------------------
		//if this tree only has a root node
		//---------------------------------
		if (current.left == null && current.compareTo(elem) > 0){
				current.left = newNode;
		}
		
		if (current.right == null && current.compareTo(elem) < 0){
			current.right = newNode;
		}
		
		if (current.left != null && current.compareTo(elem) > 0) {
			//recurse through the tree.
			addToTree(current.left, elem);
		}
		
		if (current.right != null && current.compareTo(elem) < 0){
			addToTree(current.right, elem);
		}
	}
	/**
	 * testRemove()
	 */
	public void removeTest(MyTreeNode<T> node){
		
		node.left.right = null;
	}
	
	/**
	 * removeFromTree()
	 * @param T elem
	 * @param MyTreeNode<T> node
	 */
	public void removeLeafFromTree(T elem, MyTreeNode<T> node){
		
		assert (elem != null && node != null);
			
			//-------------------------------
			// Check left subtree parents. 
			//-------------------------------
			if (node.left != null && node.left.compareTo(elem) == 0){
				node.left = null;
			} 
			
			//there is a left child
			if (node.left != null){
				
				if (node.compareTo(elem) > 0){
					removeLeafFromTree(elem, node.left);
				}	
			}
			
			//-----------------------------
			//Check right subtree parents.
			//-----------------------------
			if (node.right != null && node.right.compareTo(elem) == 0){
				node.right = null;
			} 
			
			if (node.right != null){
				
				if (node.compareTo(elem) < 0){
					removeLeafFromTree(elem, node.right);
				}
			}

		
		// Case the delete Node has one child
		
		// Case the delete Node has two children
	}
	
	/**
	 * getTreeLevels
	 * 
	 */
	public int getTreeLevels(MyTreeNode<T> node){
		
		if (node.left != null || node.right != null){
			treeLevels++;
		}
		return treeLevels;
	}
	
	/**
	* breadthFirstSearch() searches the tree by level
	* the underlying queue is a linked list.
	* 
	* @param MyTreeNode rt - the root of the tree
	*/
	public void breadthFirstSearch(MyTreeNode<T> rt){
		
		Queue<MyTreeNode<T>> q = new LinkedList<MyTreeNode<T>>();
		MyTreeNode<T> parent = rt;
		q.add(parent);
		
		while (q.size() != 0){
			
			if (parent.left != null){
				//----------------------------
				//add left child to the queue
				//----------------------------
				q.add(parent.left);
			}
			
			if (parent.right != null){
				//----------------------------
				//add right child to the queue
				//----------------------------
				q.add(parent.right);
			}
			
			//--------------------------
			//dequeue the current parent
			//--------------------------
			q.remove();
			parent = q.peek();
		}
	}
	
	/**
	 * depthFirsSearch()
	 * @param node
	 * @param current
	 * @return current node
	 */
	public MyTreeNode<T> depthFirstSearch(T datum, MyTreeNode<T> current){
		
		if(datum == null || current == null){
			return null;
		}
		
		//-----------------------------------------
		// If the node we want is the current root
		//-----------------------------------------
		if (current.compareTo(datum) == 0){
			return current;
		} else {
			
			//-----------------------------------------------------------------
			// If datum is less than the current node, search the left subtree
			//-----------------------------------------------------------------
			if (current.left != null && current.compareTo(datum) > 0){
				return depthFirstSearch(datum, current.left);
			}
			
			//-----------------------------------------------------------------
			// If datum is grater than the current node, search the right subtree
			//-----------------------------------------------------------------
			if (current.right != null && current.compareTo(datum) < 0){
				return depthFirstSearch(datum, current.right);
			}
		}
		
		return null;
	}
	
	/**
	 * printTreeBreadthFirst prints a string representation of the tree
	 * @param MyTreeNode<T> 
	 * @return StringBuilder - a string representation of the tree
	 */
	public StringBuilder printTreeBreadthFirst(MyTreeNode<T> rt){
		
		Queue<MyTreeNode<T>> q = new LinkedList<MyTreeNode<T>>();
		MyTreeNode<T> current = rt;
		q.add(current);
		
		StringBuilder str = new StringBuilder();
		
		while (q.size() != 0){
			
			if (current.left != null){
				q.add(current.left);
			}
			
			if (current.right != null){
				q.add(current.right);
			}
			
			//---------------------
			// Dequeue current node
			//---------------------
			q.remove();
			current = q.peek();
		}
		
		return str;
	}

	/**
	 * printTreeDepthFirst() - Returns StringBuilder representation of the tree
	 * @param node
	 * @return
	 */
	public StringBuilder printTreeDepthFirst(MyTreeNode<T> node){
		
		if(node == null)
			return new StringBuilder("");
		
		StringBuilder str = new StringBuilder();
		
		str.append("\t" + node);

		if (node.left != null){
			str.append(printTreeDepthFirst(node.left));
		} 
		
		if (node.right != null){
			str.append(printTreeDepthFirst(node.right));
		} 
		return str;
	}
	
	public static void main(String[] argvs){
		
		Anime anime1 = new Anime("Tokyo Ghoul", "Dark Fantasy");
		Anime anime2 = new Anime("Parasyte the Maxim", "Dark Fantasy");
		Anime anime3 = new Anime("Naruto", "Adventure");
		Anime anime4 = new Anime("Death Note", "Crime Fantasy");
		Anime anime5 = new Anime("Fullmetal Alchemist: Brotherhood", " Fantasy Action/Adventure");
		Anime anime6 = new Anime("Akame Ga Kill!", "Action");
		Anime anime7 = new Anime("Another", "Mystery/ Horror");
		Anime anime8 = new Anime("Yu Yu Hakusho", "Action");
		Anime anime9 = new Anime("Attack on Titan", "Dark Fantasy");
		
		MyTree<Anime> animeTree = new MyTree<Anime>();
		animeTree.root.data = anime3;
		
		animeTree.addToTree(animeTree.root, anime1);
		animeTree.addToTree(animeTree.root, anime2);
		animeTree.addToTree(animeTree.root, anime4);
		animeTree.addToTree(animeTree.root, anime5);
		animeTree.addToTree(animeTree.root, anime6);
		animeTree.addToTree(animeTree.root, anime7);
		animeTree.addToTree(animeTree.root, anime8);
		animeTree.addToTree(animeTree.root, anime9);
		
		//------------------------
		// Test depthFirstSearch
		//------------------------
		MyTreeNode<Anime> foundNode = animeTree.depthFirstSearch(anime5, animeTree.root);
		if (foundNode != null){
			System.out.println("The show " + foundNode.toString() + " is in the tree.");
		} else {
			System.out.println("The show " + anime5.toString() + " is not in the tree.");
		}

		foundNode = animeTree.depthFirstSearch(anime8, animeTree.root);
		if (foundNode != null){
			System.out.println("The show " + foundNode.toString() + " is in the tree.");
		} else {
			System.out.println("The show " + anime8.toString() + " is not in the tree.");
		}
		
		foundNode = animeTree.depthFirstSearch(anime6, animeTree.root);
		if (foundNode != null){
			System.out.println("The show " + foundNode.toString() + " is in the tree.");
		} else {
			System.out.println("The show " + anime6.toString() + " is not in the tree");
		}
		
		Anime anime10 = new Anime("Aldnoah Zero", "Futuristic, Meccha");
		
		foundNode = animeTree.depthFirstSearch(anime10, animeTree.root);
		if (foundNode != null){
			System.out.println("The show " + foundNode.toString() + " is in the tree.");
		} else {
			System.out.println("The show " + anime10.toString() + " is NOT in the tree.");
		}
		
		//------------------------
		// Test removeFromTree
		//------------------------
		animeTree.removeLeafFromTree(anime5, animeTree.root);
		//animeTree.removeTest(animeTree.root);
		
		foundNode = animeTree.depthFirstSearch(anime5, animeTree.root);
		if (foundNode != null){
			System.out.println("The show " + foundNode.toString() + " is in the tree. BUT IT SHOULD NO LONGER BE IN THE TREE BECAUSE IT WAS SUPPOSED TO BE DELETED *cries*.");
		} else {
			System.out.println("The show " + anime5.toString() + " is not in the tree.");
		}
		
		//animeTree.breadthFirstSearch(animeTree.root);
		System.out.println(animeTree.printTreeDepthFirst(animeTree.root) + "\n\n");
	}
}