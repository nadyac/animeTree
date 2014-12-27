/**
* Anime.java
*
*
*/

public class Anime{
	
	String title;
	String genre;
	
	public Anime(String t, String g){
		title = t;
		genre = g;
	}
	
	public String toString(){
		
		String str = "";
		
		if (this != null){
			str += title;
		}		
		return str;
	}
}