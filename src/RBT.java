public class RBT extends DictionaryADT
{
	private RBNode root, NIL;
	
	public RBT()
	{
		NIL = new RBNode();
		NIL.color = "black";
		root = NIL;
	}

	private boolean LeftRotate(RBNode x)
	{
		if(x.right == NIL)
			return false;
		
		//set y.
		RBNode y = x.right;

		//Turn y's left subtree into x's right subtree.
		x.right = y.left;
		y.left.parent = x;

		//Link x's parent to y.
		y.parent = x.parent;		
		
		if(x.parent == NIL)	//if x is root
		{
			root = y;
		}		
		else
		{
			if(x == x.parent.left)
				x.parent.left = y;
			else
				x.parent.right = y;
		}

		//put x on y's left.
		y.left = x;
		x.parent = y;
		return true;
	}

	private boolean RightRotate(RBNode x)
	{
		if(x.left == NIL)
			return false;
		
		//set y.
		RBNode y = x.left;

		//Turn y's left subtree into x's right subtree.
		x.left = y.right;
		y.right.parent = x;

		//Link x's parent to y.
		y.parent = x.parent;		
		
		if(x.parent == NIL)	//if x is root
		{
			root = y;
		}		
		else
		{
			if(x == x.parent.left)
				x.parent.left = y;
			else
				x.parent.right = y;
		}

		//put x on y's left.
		y.right = x;
		x.parent = y;
		return true;
	}

	private void RBInsertFixup(RBNode z)
	{
		while(z.parent.color == "red")
		{
			if(z.parent == z.parent.parent.left)
			{
				RBNode y = z.parent.parent.right;
				if(y.color == "red")
				{
					z.parent.color = "black";
					y.color = "black";
					z.parent.parent.color = "red";
					z = z.parent.parent;
				}
				else
				{
					if(z == z.parent.right)
					{
						z = z.parent;
						LeftRotate(z);
					}
					z.parent.color = "black";
					z.parent.parent.color = "red";
					RightRotate(z.parent.parent);
				}
			}
			else
			{
				RBNode y = z.parent.parent.left;
				if(y.color == "red")
				{
					z.parent.color = "black";
					y.color = "black";
					z.parent.parent.color = "red";
					z = z.parent.parent;
				}
				else
				{
					if(z == z.parent.left)
					{
						z = z.parent;
						RightRotate(z);
					}
					z.parent.color = "black";
					z.parent.parent.color = "red";
					LeftRotate(z.parent.parent);
				}
			}
		}
		root.color = "black";		//HIGHLY DOUBTFUL - root has not been updated after rotations
	}

	public void Insert(int val)
	{
		RBNode z = new RBNode();
		z.value = val;		

		RBNode y = NIL;
		RBNode x = root;
		while(x != NIL)
		{
			y = x;	
			if(z.value < x.value)
				x = x.left;
			else
				x = x.right;
		}

		z.parent = y;
		if(y == NIL)	//if tree is empty
		{
			root = z;
		}
		else
		{
			if(z.value < y.value)
				y.left = z;
			else
				y.right = z;
		}

		z.left = NIL;
		z.right = NIL;
		z.color = "red";
		RBInsertFixup(z);
	}
	
	private RBNode min(RBNode x)
	{
		while(x.left != NIL)
			x = x.left;
		return x;	
	}

	private RBNode successor(RBNode x)
	{
		RBNode y = NIL;
		if(x.right != NIL)
			return  min(x.right);

		y=x.parent;
		while ((y != NIL ) && (x == y.right))
		{	
			x = y;
			y = y.parent;
		}
    		return y;

	}

	private RBNode Search(RBNode x, int val)
	{
		if(x == NIL)
			return NIL;
		if(x.value == val)
			return x;
		else if(x.value < val)
			return Search(x.right, val);
		else
			return Search(x.left, val);
	}

	private void RBDeleteFixup(RBNode x)
	{
		while((x != root) && (x.color == "black"))
		{
			//if x is left child
			if(x == x.parent.left)
			{
				RBNode w = x.parent.right;
				if(w.color == "red")
				{
					w.color = "black";
					x.parent.color = "red";
					LeftRotate(x.parent);
					w = x.parent.right;
				}
				if((w.left.color == "black") && (w.right.color == "black"))
				{
					w.color = "red";
					x = x.parent;
				}
				else
				{
					if(w.right.color == "black")
					{
						w.left.color = "black";
						w.color = "red";
						RightRotate(w);
						w = x.parent.right;
					}
					w.color = x.parent.color;
					x.parent.color = "black";
					w.right.color = "black";
					LeftRotate(x.parent);
					x = root;
				}				
			}
			//if x is right child, exchange "left" and "right"
			else
			{
				RBNode w = x.parent.left;
				if(w.color == "red")
				{
					w.color = "black";
					x.parent.color = "red";
					RightRotate(x.parent);
					w = x.parent.left;
				}
				if((w.right.color == "black") && (w.left.color == "black"))
				{
					w.color = "red";
					x = x.parent;
				}
				else
				{
					if(w.left.color == "black")
					{
						w.right.color = "black";
						w.color = "red";
						LeftRotate(w);
						w = x.parent.left;
					}
					w.color = x.parent.color;
					x.parent.color = "black";
					w.left.color = "black";
					RightRotate(x.parent);
					x = root;
				}				
			}
		}
		x.color = "black";
	}

	public boolean Delete(int val)
	{
		RBNode x = NIL, y = NIL, z = NIL;
		if (root != NIL)
			z = Search(root, val);
		if(z != NIL)
		{
			if ((z.left == NIL) || (z.right == NIL))
				y = z;
			else  
				y = successor(z);
			if(y.left != NIL)
				x = y.left;
			else
				x = y.right;

			x.parent = y.parent;

			if(y.parent == NIL)
				root = x;
			else 
			{
				if(y == y.parent.left)
					y.parent.left = x;
				else
					y.parent.right = x;
			}
			if(y != z)
			{
				z.value = y.value;
				y.left = z.left;
				y.right = z.right;
				y.parent = z.parent;
			}

			if(y.color == "black")
				RBDeleteFixup(x);
			return true;
		}
		else 
			return false;
	}

	
	private void inorder(RBNode x)
	{
		if(x == NIL)
			return;
		inorder(x.left);
	     	System.out.print(x.value + " ");
	     	inorder(x.right);
	}

	public void DisplayADT()
	{
		if(root != NIL)
			inorder(root);
		else
			System.out.print("The Tree is empty");
	
	}

	public boolean Search(int val)
	{
		RBNode x = NIL;
		if(root != NIL)
			x = Search(root, val);
		if(x == NIL)
			return false;
		else 
			return true;
	}
	
	public void ClearADT()
	{
		this.root = NIL;
	}

}
	

class RBNode
{
	int value;
	String color;
	RBNode left;
	RBNode right;
	RBNode parent;
	
	public RBNode()
	{
		//color = "red";
		//this.left=null;
		//this.right=null;
		//this.parent=null;
	}
}
