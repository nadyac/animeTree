/**
* MyTree.java
*
* a tree that stores my favorite anime alphabetically
* will eventually add a small GUI so I can search through them quickly. 
*/

import java.util.*;

public class MyTree<T>{
	
	
	protected MyTreeNode<T> root;
	
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
	 * isLeaf - determines if the given node is a lead node
	 * @param n
	 * @return
	 */
	public boolean isLeaf(MyTreeNode<T> n){
		
		if (n.left == null && n.right == null){
			return true;
		}
		return false;
	}
	
	/**
	 * removeNode() - we need this method because breadthfirst and depthfirst searches
	 *  can't help us delete the current node since we can't delete the node we're currently
	 *  visiting (bc parent will keep the ref). We can only delete the children of the current node.
	 *  
	 * @param T elem
	 * @param MyTreeNode<T> node
	 */
	public void removeNode(T elem, MyTreeNode<T> node){
		
		assert (elem != null && node != null);
			
			//-------------------------------
			// Check left subtree parents. 
			//-------------------------------
			if (node.left != null && node.left.compareTo(elem) == 0){
				
				//just delete it if it's a leaf
				if (isLeaf(node.left)){
					node.left = null;
				} else {
					
					//does it have 1 child?
					if (node.right == null){
						
					}
					
					//does it have 2 children?
					if (node.right != null){
						
					}
				}
			} 
			
			//there is a left child
			if (node.left != null){
				
				if (node.compareTo(elem) > 0){
					removeNode(elem, node.left);
				}	
			}
			
			//-----------------------------
			//Check right subtree parents.
			//-----------------------------
			if (node.right != null && node.right.compareTo(elem) == 0){
				if (isLeaf(node.right)){
					//just delete if it's a leaf.
					node.right = null;
				} else {
					//does it have only 1 child?
					
					//does it have 2 children?
				}
			} 
			
			if (node.right != null){
				
				if (node.compareTo(elem) < 0){
					removeNode(elem, node.right);
				}
			}
	}
	
	/**
	 * removeParentNode()
	 * @param T elem
	 */
	public void removeThis(T elem){
		
		// do depth search for the node containing elem
		// if it is found, check if it's a leaf, if not
		// continue with the removal algorithm. 
		MyTreeNode<T> delete = depthFirstSearch(elem, root);
		System.out.println("Found the delete node : " + delete.toString());
		
		if (delete.left != null){
			System.out.println("delete node has a left child.");
		}
		
		if (delete.right != null){
			System.out.println("delete node has a right child.");
		}
		delete.left = null;
		delete.right = null;
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
	 * depthFirsSearch
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
		System.out.println();
		System.out.println("current animeTree contains:");
		System.out.println(animeTree.printTreeDepthFirst(animeTree.root) + "\n\n");
		System.out.println();
		
		//------------------------
		// Test removeFromTree
		//------------------------
		/**animeTree.removeNode(anime5, animeTree.root); //remove FMA
		animeTree.removeNode(anime7, animeTree.root); //try to remove Another, which would also remove AoT
		animeTree.removeNode(anime4, animeTree.root); //try to remove Death Note, should not work for now
		animeTree.removeNode(anime8, animeTree.root); //remove Yu Yu Hakusho 
				
		//animeTree.breadthFirstSearch(animeTree.root);
		System.out.println("current animeTree contains:");
		System.out.println(animeTree.printTreeDepthFirst(animeTree.root) + "\n\n");*/
		
		//-----------------
		//Test removeThis
		//-----------------
		animeTree.removeThis(anime4); //try to delete Death Note
		System.out.println("current animeTree contains:");
		System.out.println(animeTree.printTreeDepthFirst(animeTree.root) + "\n\n");
		
	}
}
