import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


class Bst
{
 Node root;
	Bst()
	{
		root=null;
	}
	public static void main(String args[])
	{
		try
		{
			Bst obj=new Bst();
			String fileName = args[0];
			FileReader fileReader = new FileReader(fileName);
	       		BufferedReader bufferedReader = new BufferedReader(fileReader);
	       		String sInputList = bufferedReader.readLine();
	        		bufferedReader.close();
			String[] splitString = new String[sInputList.length()];
			int len = 0;
			for(int i=0; i < sInputList.length(); i++)
			{
				//Checking whether the character at the current position is a space or not
				if(sInputList.charAt(i) != ' ')
				{
					//Extracting each element from the string and storing them in a string array
					splitString[len] = sInputList.substring(i, sInputList.indexOf(' ', i) == -1 ? sInputList.length() : sInputList.indexOf(' ', i));
					//Updating the value of i
					i = sInputList.indexOf(' ', i) == -1 ? sInputList.length() : sInputList.indexOf(' ', i);
					//Incrementing len
					len++;
				}
			}
			int [] inputArray=new int[len];
			for(int i = 0; i < len; i++)
			{
				inputArray[i] = Integer.parseInt(splitString[i]);
			}
			for(int i=0;i<len;i++)
			{
				obj.insert(inputArray[i]);
			}
			obj.display();
			obj.delete(10);
			obj.display();
		}
		catch(Exception e)
		{
			//Printing the Stack Trace if an exception has occurred
			e.printStackTrace();
		}
	}


	void insert(int a)
	{
		Node x=null,y=null,z;
		z=new Node();
		z.value=a;
		if(root==null)
		 root=z;
                         	else{
			x=root;
	   		while(x!=null){
	   			y=x;
				if(a==x.value) {
					System.out.println(x.value+" already exists");
					return;
					}
				else if(a<x.value)
				x=x.left;
				else
				x=x.right;
			}
	   		 z.parent=y;
			if(y.value>z.value)
	  		y.left=z;
	  		else y.right=z;
		  }
		

	    }
	
	boolean search(int a)
                 {
		Node x=null;
		if(root!=null)
                   	x=search(root,a);
		if(x==null)
		return false;
		else 
		return true;
	}
	
	Node search(Node x,int a)
	{
                 	 if(x.value==a)
			return x;
		else if(x.value<a)
                   		  return  search(x.right,a);
		else
			return search(x.left,a);
	}

	boolean delete(int a)
	{
		Node x=null,y=null,z=null;
		if (root!=null)
		z=search(root,a);
		if(z!=null)
         	  	{
			if ((z.left==null) || (z.right==null))
					y=z;
				else  
					y= successor(z);
				if(y.left!=null)
					x=y.left;
				else
					x=y.right;
				if(x!=null)
					x.parent=y.parent;
				if(y.parent==null)
					root=x;
				else if(y==y.parent.left)
					y.parent.left=x;
				else
					y.parent.right=x;
				if(y!=z){
					z.value=y.value;
					y.left=z.left;
					y.right=z.right;
					y.parent=z.parent;
					}
				return true;
			}
		else return false;
	}
	
	Node min(Node x)
		{
			while(x.left!=null)
				x=x.left;
			return x;	
		}

	Node successor(Node x)
		{
			Node y=null;
			 if(x.right!=null)
                                		 return  min(x.right);
                                	else
				y=x.parent;
 			while ((y!=null )&&(x==y.right))
				x=y;
			y=y.parent;
                                	return y;
		}

	void display()
	{
		if(root!=null)
		inorder(root);
	
	}

	void inorder(Node x)
	{
	   if(x==null)
		return;
  	                inorder(x.left);
        	     	System.out.println(x.value);
		 inorder(x.right);
	}
	
	void clearAdt()
	{
		this.root=null;
	}
}
	

class Node
{
	int value;
	Node left;
	Node right;
	Node parent;
	            Node()
 		 {
			this.left=null;
			this.right=null;
			this.parent=null;
 		 }
}
