import static org.junit.Assert.*;

import org.junit.Test;


public class AnimeTreeTest {
	
	Anime anime1 = new Anime("Tokyo Ghoul", "Dark Fantasy");
	Anime anime2 = new Anime("Parasyte the Maxim", "Dark Fantasy");
	Anime anime3 = new Anime("Naruto", "Adventure");
	Anime anime4 = new Anime("Death Note", "Crime Fantasy");
	Anime anime5 = new Anime("Fullmetal Alchemist: Brotherhood", " Fantasy Action/Adventure");
	Anime anime6 = new Anime("Akame Ga Kill!", "Action");
	Anime anime7 = new Anime("Another", "Mystery/ Horror");
	Anime anime8 = new Anime("Yu Yu Hakusho", "Action");
	Anime anime9 = new Anime("Attack on Titan", "Dark Fantasy");
	Anime anime10 = new Anime("Yona", "Adventure");
	
	MyTree<Anime> animeTree = new MyTree<Anime>();
	
	/**
	 * Select a root for the tree
	 */
	@Test
	public void testSettingRoot() {
		animeTree.root.data = anime3;
		assert(animeTree.root.data == anime3);
	}
	
	/**
	 * Test adding anime shows to the tree
	 */
	@Test 
	public void testAddToTree(){
		animeTree.root.data = anime3;
		animeTree.addToTree(animeTree.root, anime1);
		animeTree.addToTree(animeTree.root, anime2);
		animeTree.addToTree(animeTree.root, anime4);
		animeTree.addToTree(animeTree.root, anime5);
		animeTree.addToTree(animeTree.root, anime6);
		animeTree.addToTree(animeTree.root, anime7);
		animeTree.addToTree(animeTree.root, anime8);
		animeTree.addToTree(animeTree.root, anime9);
		animeTree.addToTree(animeTree.root, anime10);
	}
	
	/**
	 * Test depthfirst search
	 */
	@Test
	public void testDepthFirstSearch(){
		animeTree.root.data = anime3;
		MyTreeNode<Anime> foundNode = animeTree.depthFirstSearch(anime5, animeTree.root);
		assert(foundNode.toString().equals(anime5.toString()));
	}

}
