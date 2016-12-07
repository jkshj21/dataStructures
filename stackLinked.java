public class stackLinked <AnyType> {
	
	Node stackLinkedList;
	Node topOfStack; 
	
	private class Node <AnyType> { 
		Node next; 
		AnyType value; 
		
		private Node (AnyType value, Node next) { 
			this.value = value;
			this.next = next;
		}
	}
	
	public stackLinked () {
		topOfStack = null;
	}
	
	public boolean isEmpty() { 
		return (topOfStack == null);
	}
	public void push(AnyType newValue) { 
		Node newNode = new Node (newValue, null); 
		if(isEmpty()) { 
			topOfStack= newNode;
		}
		else { 
			newNode.next=topOfStack;
			topOfStack = newNode;
		}
	}
	
	public void pop() { 
		if(isEmpty()) return; 
		else{ 
			Node current = topOfStack; 
			topOfStack = current.next; 
			current.next = null; 
		}
	}
	public AnyType peek () { 
		return (AnyType) topOfStack.value;
	}
	
	public void printElements () { 
		Node current = topOfStack; 
		
		while(current != null) { 
			System.out.println(current.value); 
			current = current.next;
		}
	}

}
