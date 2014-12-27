
public class MyTreeNode<T> implements Comparable<T>{
	
	public MyTreeNode left;
	public MyTreeNode right;
	protected T data;
	
	public MyTreeNode(){
		this(null, null, null);
	}
	
	public MyTreeNode(T datum, MyTreeNode l, MyTreeNode r){
		left = l;
		right = r;
		data = datum;
	}
	
	public int compareTo(T datum){
		
		if (this.data.toString().compareTo(datum.toString()) < 0){
			return -1;
		} else if (this.data.toString().compareTo(datum.toString()) > 0){
			return 1;
		}
		return 0;
	}
	
	/**
	 * Print string representation of the data
	 * @return String
	 */
	public String toString(){
		
		String str = "";
		if (data != null){
			str += data;
		} else if (this.data == null || this == null) {
			str += "null";
		}
		return str;
	}
	
}