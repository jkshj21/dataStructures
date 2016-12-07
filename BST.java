public class BST<AnyType extends Comparable<? super AnyType>> {

	private class Node<AnyType> implements Comparable<Node> {
		 public AnyType data;
		    public AnyType getData() {
		        return data;
		    }

		    public void setData(AnyType data) {
		        this.data = data;
		    }

		    public Node getLeftChild() {
		        return leftChild;
		    }

		    public void setLeftChild(Node leftChild) {
		        this.leftChild = leftChild;
		    }

		    public Node getRightChild() {
		        return rightChild;
		    }

		    public void setRightChild(Node rightChild) {
		        this.rightChild = rightChild;
		    }

		    public Node leftChild;
		    public Node rightChild;

		    public Node(AnyType data,Node leftChild,Node rightChild)
		    {
		        this.data=data;
		        this.leftChild=leftChild;
		        this.rightChild=rightChild;

		    }

		    @Override
		    public int compareTo(Node o) {
		        if((int)o.getData() > (int)this.data)
		        return -1;

		        if((int)o.getData() < (int)this.data)
		            return 1;

		        return 0;
		    }
		    
		    
	}
	private Node root=null;

    public Node getRoot() {
        return root;
    }
    public void makeEmpty() {root = null;}

    public void insertData(AnyType data)
    {
        Node node=new Node(data,null,null);
        insert(node,this.root);

    }

    private Node<AnyType> insert(Comparable<Node> node,Node root1)
    {
            if(root1==null)
            {

                root1=new Node(((Node)node).getData(),null,null);
                if(this.root==null)
                {
                    this.root=root1;
                }
            }
            else if(node.compareTo(root1) <0)
            {
                root1.setLeftChild(insert(node,root1.getLeftChild()));
            }
            else if(node.compareTo(root1) >0)
            {
                root1.setRightChild(insert(node,root1.getRightChild()));
            }

     return root1;  
    }

    
    public void inOrderTraversal(Node root){
		if(root!=null){
			inOrderTraversal(root.getLeftChild());
			System.out.print(" " + root.getData());
			inOrderTraversal(root.getRightChild());
		}
	}
    public void postTraversal(Node root){
		if(root!=null){
			postTraversal(root.getLeftChild());
			postTraversal(root.getRightChild());
			System.out.print(" " + root.getData());
		}
	}
    public void preOrderTraversal(Node root){
		if(root!=null){
			System.out.print(" " + root.getData());
			preOrderTraversal(root.getLeftChild());
			preOrderTraversal(root.getRightChild());
		}
	}
    public int printLeafCount(Node<AnyType> t)
    {	
          if(t == null)       
            return 0;
          
           if(t.getLeftChild() == null && t.getRightChild()==null)      
              return 1;
           else { 
        	   return printLeafCount(t.getLeftChild()) + printLeafCount(t.getRightChild());
           }    
    }
    public void printLeafNodes(Node<AnyType> t)
    {
          if(t == null)       
            return;
           if(t.getLeftChild() == null && t.getRightChild()==null)      
              System.out.println(t.getData()); 
           printLeafNodes(t.getLeftChild()); 
           printLeafNodes(t.getRightChild());      
    }
    public boolean contain( AnyType x )
    { return contain( x, root ); }
    
    private boolean contain (AnyType x, Node<AnyType> t) { 
		if(t==null) return false; 
		int compareResult=x.compareTo(t.getData()); 
		
		if(compareResult <0 ) { 
			return contain (x,t.getLeftChild()); 
		}
		else if (compareResult >0) { 
			return contain (x, t.getRightChild()); 
		}
		else return true; 
	}
	
    private Node<AnyType> findMin (Node<AnyType> t) { 
    	if(t==null) return null; 
    	else if (t.getLeftChild() == null) { 
    		return t; 
    	}
    	return findMin(t.getLeftChild()); 
    }
    private Node<AnyType> findMax (Node<AnyType> t) { 
    	if(t==null) return null; 
    	else if (t.getRightChild() == null) { 
    		return t; 
    	}
    	return findMin(t.getRightChild()); 
    }
    
    public void Remove(AnyType x) { 
    	root = Remove(x,root);
    }
    private Node<AnyType> Remove (AnyType x, Node<AnyType> t) { 
    	
    	if(t==null) return t; 
    	int compareResult = x.compareTo(t.getData()); 
    	
    	if(compareResult < 0) { 
    		t.leftChild = Remove(x, t.getLeftChild()); 
    	}
    	else if(compareResult > 0) { 
    		t.rightChild= Remove(x,t.getRightChild()); 
    	}
    	
    	else if (t.getLeftChild() !=null && t.getRightChild() != null) { 
    		t.data=findMin(t.getRightChild()).getData();
    		t.rightChild= Remove(t.getData(), t.getRightChild()); 
    	}
    	else t= (t.getLeftChild()!=null) ? t.getLeftChild():t.getRightChild(); 
    	return t;
    }
    
}
