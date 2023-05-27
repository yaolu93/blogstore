



public class BinaryTree<Key extends Comparable<Key>, Value>{

    private class Node{
	public Key key;
	private Value value;

	public Node left;
	public Node right;

	public Node(Key key, Value value, Node left, Node right){
	    this.key = key;
	    this.value = value;

	    this.left = left;
	    this.right = right;
	    
	}

    }

    //Recording the root Node
    private Node root;

    //Recording the size of elements.
    private int N;

    //get the size of the tree
    public int size(){
	return N;
    }


    

    //add the elements to the tree
    public void put(Key key, Value value){
	root = put(root, key, value);
	
    }


    //add the key-value pair to the special area. return the new tree.
    
    private Node put(Node x, Key key, Value value){
	if (x == null) {
	    N++;
	    return new Node(key ,value, null, null);
	}

	int cmp = key.compareTo(x.key);

	if (cmp > 0) {
	    //if the  new Node Key is bigger then the current Node key keep search
	    x.right = put(x.right, key, value);

	} else if (cmp < 0) {
	    x.left = put(x.left, key, value);
	    
	} else{
	    //new Node equal to the current Node recover
	    x.value = value;
	}

	return  x;
	
	
    }
    
    public Value get(Node x, Key key){
	if (x == null) {
	    return null;
	    
	}

	int cmp = key.compareTo(x.key);

	if (cmp > 0) {
	    return get(x.right, key);
	} else if (cmp < 0) {
	    return get(x.left, key);
	} else{
	    return x.value;
	}
    }
    
    //delete the element

    public void delete(Key key){

	root = delete(root, key);
    }

    //delete the special element and return the new tree

    public Node delete(Node x, Key key){
	if (x == null) {
	    return null;
	}

	int cmp = key.compareTo(x.key);

	if (cmp > 0) {
	    x.right = delete(x.right, key);
	} else if (cmp < 0) {
	    x.left = delete(x.left, key);
	    
	}else{

	    if (x.right == null) {
		return x.left;
		
	    }

	    if (x.left == null) {
		return x.right;
	    }

	    //if the right and left subTree exist, find the smallest node in the right subTree.
	    Node minNode = x.right;

	    while(minNode.left != null){

		minNode = minNode.left;
	    }

	    //delete the smallest Node in right subTree

	    Node node = x.right;

	    while(node.left != null){
		if (node.left.left == null) {
		    node.left = null;
		} else {
		    node = node.left;
		}
	    }

	    minNode.left = x.left;
	    minNode.right = x.right;

	    x = minNode;
	    N--;
	    
	    
	}
	return x;
    }

    
    public static void main(String[] args) {

	BinaryTree <Integer,String>bt = new BinaryTree<>();
	bt.put(4,"Ton");
	bt.put(1,"Jack");
	bt.put(3,"Coco");
	bt.put(5,"Nono");

	System.out.println(bt.size());
	
	System.out.println("Binarytree");
    }
}
