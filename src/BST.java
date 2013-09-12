//BST Oprations
public class BST extends DictionaryADT
{
	private Node root;
	
	public BST()
	{
		root = null;
	}

	public void Insert(int val)  //Insertion In BST
	{
		Node x = null, y  = null, z;
		z = new Node();
		z.value = val;
		if(root == null)
			root = z;
		else
		{
			x=root;
	   		while(x != null)
	   		{
	   			y=x;
				if(val == x.value) 
				{
					System.out.println(x.value + " already exists");
					return;
				}
				else if(val < x.value)
					x=x.left;
				else
					x=x.right;
			}
	   		z.parent=y;
			if(y.value > z.value)
				y.left = z;
	  		else
	  			y.right = z;
		}
	}
	
	public boolean Search(int val)  //Search in BST
	{
		Node x = null;
		if(root != null)
			x = Search(root,val);
		if(x == null)
			return false;
		else 
			return true;
	}
	
	private Node Search(Node x,int val)
	{
		if(x == null)
			return null;
		if(x.value == val)
			return x;
		else if(x.value < val)
			return Search(x.right, val);
		else
			return Search(x.left, val);
	}

	public boolean Delete(int val)   //Delete Node From BST
	{
		Node x = null, y = null, z = null;
		if (root != null)
			z = Search(root, val);
		if(z != null)
		{
			if ((z.left == null) || (z.right == null))
				y = z;
			else  
				y = successor(z);
			if(y.left != null)
				x = y.left;
			else
				x = y.right;
			if(x != null)
				x.parent = y.parent;
			if(y.parent == null)
				root = x;
			else if(y == y.parent.left)
				y.parent.left = x;
			else
				y.parent.right = x;
			if(y != z)
			{
				z.value = y.value;
				y.left = z.left;
				y.right = z.right;
				y.parent = z.parent;
			}
			return true;
		}
		else 
			return false;
	}
	
	private Node min(Node x)   //Finds Minimum Node in BST
	{
		while(x.left!=null)
			x=x.left;
		return x;	
	}

	private Node successor(Node x)   //Finds Successor of given node
	{
		Node y = null;
		if(x.right != null)
			return  min(x.right);
		y=x.parent;
		while ((y != null ) && (x == y.right))
		{	x=y;
			y=y.parent;
		}
    	return y;
	}

	public void DisplayADT()    //Display All Elements of BST
	{
		if(root != null)
			inorder(root);
		else
			System.out.print("The Tree is empty");
	
	}

	private void inorder(Node x)  //To Find Inorder Traversal Order of BST
	{
	   if(x == null)
		   return;
        inorder(x.left);
     	System.out.print(x.value + " ");
     	inorder(x.right);
	}
	
	public void ClearADT()
	{
		this.root = null;
	}
}
	

class Node                   //Class Node to create an Object for bST node
{
	int value;
	Node left;
	Node right;
	Node parent;
	
	public Node()
	{
		this.left=null;
		this.right=null;
		this.parent=null;
	}
}
