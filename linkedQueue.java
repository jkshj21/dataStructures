public class linkedQueue <AnyType> {
	
	private class Node<AnyType> { 
		Node next; 
		AnyType element;
		
		private Node (AnyType element, Node<AnyType> next) { 
			this.element = element;
			next=next;
		}
	}
	
	private Node<AnyType> front; 
	private Node <AnyType>rear;
	
	int size;
	public linkedQueue () { 
		size=0;
	}
	public boolean isEmpty() { 
		return (size==0);
	}
	
	public void enqueue (AnyType newValue) { 
		Node newNode = new Node (newValue, null);
		if(isEmpty()) {
			front = newNode;
		}
		else { 
			rear.next = newNode; 
		}
		rear=newNode;
		size++;
	}
	public void dequeue () { 
		if(isEmpty()) return;
		else { 
			front = front.next;
			size--;
			if(isEmpty()) rear = null;
		}
	}
	
	public void printElement () { 
		Node current = front; 
		while(current!=null){ 
			System.out.println(current.element);
			current=current.next;
		}
	}
}
